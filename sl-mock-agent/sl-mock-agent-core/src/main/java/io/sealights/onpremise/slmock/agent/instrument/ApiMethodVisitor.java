package io.sealights.onpremise.slmock.agent.instrument;

import java.util.Arrays;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.Opcodes;
//import org.slf4j.Logger;

//import io.sealights.onpremise.agents.apianalytics.footprints.APAFootprintsCollector;
//import io.sealights.onpremise.agents.commons.instrument.utils.AsmConstants;
//import io.sealights.onpremise.agents.commons.instrument.utils.MethodNamingHelper;

/**
 * Implements instrumentation od classes with approperiate annotation
 * @author ala schneider   Apr 23, 2018
 *
 */
public class ApiMethodVisitor extends AdviceAdapter {


	private String className;
	private String methodName;
	private int access;
	private String desc;
	private String[] exceptions;
	private boolean hasApiRoutingAnnotation = false;

	public ApiMethodVisitor(
			String className,
			final MethodVisitor mv,
			final int access,
			final String name,
			final String desc,
			final String[] exceptions) {
		super(Opcodes.ASM7, mv, access, name, desc);
		this.className = className;
		this.methodName = name;
		this.access = access;
		this.desc = desc;
		this.exceptions = exceptions;
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		AnnotationVisitor av = super.visitAnnotation(desc, visible);
		if (visible && EndpointAnnotationDictionary.isSupportedAnnotation(desc)) {
			System.out.println(String.format("Class %s, method '%s' has an API routing annotation: desc='%s'", className, methodName, desc));
			hasApiRoutingAnnotation = true;
		}
		return av;
	}


	@Override
	protected void onMethodEnter() {
		super.onMethodEnter();
		if (!hasApiRoutingAnnotation) {
			return;
		}

		System.out.println(String.format("Instrumenting class '%s', method '%s'", className, methodName));
		try {
			String methodId = generateMethodId();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitLdcInsn(methodId);
			mv.visitMethodInsn(INVOKESTATIC, Type.getInternalName(ApiRepository.class), "exploreApiCall", "(Ljava/lang/Object;Ljava/lang/String;)V", false);
		} catch (Exception e) {
			System.out.println(String.format("Failed to instrument class '{}', method '{}'. Error:", className, methodName, e));
		}
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		// 2 items were added to the stack
		super.visitMaxs(maxStack + 2, maxLocals);
	}

	private String generateMethodId() {
		String fullMethodName = className + "." + methodName;
		String methodDesc = MethodNamingHelper.getElementId(access, fullMethodName, desc);
		String methodId = MethodNamingHelper.createMethodUniqueId(access, methodDesc, desc, Arrays.toString(exceptions));
//		LOG.trace("Generated methodId:'{}' for className:'{}', method:'{}', method desc:'{}', exceptions:'{}",
//				methodId, className, methodName, desc, exceptions);
		return methodId;
	}

}
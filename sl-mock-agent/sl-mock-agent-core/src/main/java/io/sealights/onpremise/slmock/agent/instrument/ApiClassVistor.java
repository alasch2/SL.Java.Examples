package io.sealights.onpremise.slmock.agent.instrument;


import static org.objectweb.asm.Opcodes.ACC_INTERFACE;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * Prepares a class metadat for potential instrumentation by @APAMethodVisitor
 * discovery of classes, which expose REST API
 * If a class exposes a REST API, a class API routes info are put into
 * the global API map, so that each API has a unique id
 *  
 * @author ala schneider   Apr 21, 2018
 *
 */
public class ApiClassVistor extends ClassVisitor {
	
	private String normalizedClassName;
    private boolean isInterface;

	public ApiClassVistor(ClassVisitor cv) {
		super(Opcodes.ASM7, cv);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		super.visit(version, access, name, signature, superName, interfaces);
		normalizedClassName = name; //ClassVisitorHelper.slashToDot(name);
    	isInterface = (access & ACC_INTERFACE) != 0;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (isInterface){// || ClassVisitorHelper.skipMethodInstrumentation(normalizedClassName, access, name)) {
            return mv;
        }

        if (mv != null) {
        	return new ApiMethodVisitor(normalizedClassName, mv, access, name, desc, exceptions);
        }
        return mv;
	}

}
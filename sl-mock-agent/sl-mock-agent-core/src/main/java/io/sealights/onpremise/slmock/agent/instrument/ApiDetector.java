package io.sealights.onpremise.slmock.agent.instrument;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

public class ApiDetector {


	public static class ApiDetectClassVisitor extends ClassVisitor {

		public ApiDetectClassVisitor(ClassVisitor cv) {
			super(Opcodes.ASM7, cv);
		}

	}
	
	public static class ApiDetectMethodVisitor extends AdviceAdapter {

		protected ApiDetectMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name,
				String descriptor) {
			super(api, methodVisitor, access, name, descriptor);
		}
		
	}
}

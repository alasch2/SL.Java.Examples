package io.sl.ex.stubagent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * Stub agent
 *
 */
public class Agent {
	
    public static void main( String[] args ) {
        System.out.println("Started main of " + Agent.class.getName());
    }
    
    public static void premain(String agentArgs, Instrumentation instrumentationInstance) {
        System.out.println("Started premain of " + Agent.class.getName());
        StubTransformer transformer = new StubTransformer();
        instrumentationInstance.addTransformer(transformer);
    }    
}

class StubTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		return null;
	}
	
}
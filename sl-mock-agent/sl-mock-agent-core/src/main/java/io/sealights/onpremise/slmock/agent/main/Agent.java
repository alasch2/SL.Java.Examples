package io.sealights.onpremise.slmock.agent.main;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.lang.instrument.ClassDefinition;

import io.sealights.onpremise.slmock.agent.dto.MethodInfoDTO;
import io.sealights.onpremise.slmock.agent.logic.*;
import java.util.*;
import io.sealights.onpremise.slmock.agent.instrument.*;
/**
 * Stub agent
 *
 */
public class Agent {



    private static volatile Instrumentation instrumentation = null;

    public static void main( String[] args ) {
        System.out.println("Started main of " + Agent.class.getName());
    }
    
    public static void premain(String agentArgs, Instrumentation instrumentationInstance) {
        instrumentation = instrumentationInstance;
        System.out.println("Started premain of " + Agent.class.getName());
        StubTransformer transformer = new StubTransformer();
        ApiTransformer apiTransformer = new  ApiTransformer();
        instrumentationInstance.addTransformer(transformer);
        instrumentationInstance.addTransformer(apiTransformer);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new FileDetectionTask(), 1000, 1000);
    }

    public static void redfineClasses(ClassDefinition... definitions){
        try{
            System.out.println("rc - 1");
            instrumentation.redefineClasses(definitions);
            System.out.println("rc - 2");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("rc - 3");
            System.out.println(e.toString());
        }
        catch (Exception e){
            System.out.println("rc - 4");
            System.out.println(e.toString());
        }


    }

}

class StubTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//
//	    if (className.startsWith("io") && className.toLowerCase().contains("controller"))
//            System.out.println("#### Handling 'io' class:" + className + ", redfined: " + (classBeingRedefined != null));
////        if (className.startsWith("io"))
////            System.out.println("#### Handling 'io' class: " + className + ", redfined: " + classBeingRedefined != null);
//
////        if (className.startsWith("io") && className.toLowerCase().contains("controller")){
////            System.out.println("----> Creating a timer to retransform");
////            TimerTask task = new TimerTask() {
////                public void run() {
////
////
////                }
////            };
////            Timer timer = new Timer("Timer");
////
////            long delay = 10000L;
////            timer.schedule(task, delay);
////
////        }
        return null;
	}




}
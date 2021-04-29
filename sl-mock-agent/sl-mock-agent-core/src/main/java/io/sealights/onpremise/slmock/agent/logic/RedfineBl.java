package io.sealights.onpremise.slmock.agent.logic;
import io.sealights.onpremise.slmock.agent.main.Agent;
import java.lang.instrument.ClassDefinition;
import javassist.*;

public class RedfineBl {
        public RedfineBl() {
        }

        public void redfineClass(String className, String methodName){
            try{
                // find a reference to the class and method you wish to inject
                System.out.println("doMagic - 1");
                //String className = "io.demo.app.MainApp";
                ClassPool classPool = ClassPool.getDefault();
                CtClass ctClass = classPool.get(className);
                ctClass.stopPruning(true);

                System.out.println("doMagic - 2");

                // javaassist freezes methods if their bytecode is saved
                // defrost so we can still make changes.
                if (ctClass.isFrozen()) {
                    ctClass.defrost();
                }

                CtMethod method = ctClass.getDeclaredMethod(methodName);

                System.out.println("doMagic - 3");

                method.insertBefore("{ System.out.println(\"Wheeeeee!\"); }");
                byte[] bytecode = ctClass.toBytecode();

                System.out.println("doMagic - 4");

//                ClassDefinition definition = new ClassDefinition(Class.forName(className), bytecode);
//                Agent.retransformClasses(definition.getDefinitionClass());

                ClassDefinition definition = new ClassDefinition(Class.forName(className), bytecode);
                Agent.redfineClasses(definition);

                System.out.println("doMagic - 5");
            }catch (Throwable e){
                System.out.println("Failed to do magic.");
                System.out.println(e.toString());
            }

        }


}

package io.sealights.onpremise.slmock.agent.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * Implements basic class transformer for using in APA-dedicated agent
 * Sends classes, which match the filters, for potential instrumentation by @APAClassVistor
 * The class is not needed for test-listener agent, which already has a CodeCoverageWeaver
 * 
 * @author ala schneider   Apr 23, 2018
 *
 */
public class ApiTransformer implements ClassFileTransformer {
	
	public ApiTransformer() {
	}

	@Override
	public byte[] transform(ClassLoader loader, String className, 
			Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, 
			byte[] classFileBuffer) throws IllegalClassFormatException {
		
        if (className != null ) {
        	return instrumentClass(loader, className, classBeingRedefined, protectionDomain, classFileBuffer);
        }
        
        return null;
	}
	
    private byte[] instrumentClass(
    		ClassLoader loader, 
    		String className, 
    		Class<?> classBeingRedefined, 
    		ProtectionDomain protectionDomain, 
    		byte[] classFileBuffer) throws IllegalClassFormatException {
    	
        ClassReader reader = new ClassReader(classFileBuffer);
        ClassWriter cw = new ClassWriter(reader, 0);
        ClassVisitor apaClassVisitor = new ApiDetector.ApiDetectClassVisitor(cw);
        reader.accept(apaClassVisitor, ClassReader.EXPAND_FRAMES);
        return cw.toByteArray();
    }
    
}

package io.sealights.onpremise.slmock.agent.instrument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.sealights.onpremise.slmock.agent.utils.StringUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
//import org.slf4j.Logger;

//import io.sealights.onpremise.agents.commons.instrument.types.MethodSignature;
//import io.sealights.onpremise.agents.infra.logging.LogFactory;
//import io.sealights.onpremise.agents.infra.utils.StringUtils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MethodNamingHelper {

	//private static Logger logger = LogFactory.getLogger(MethodNamingHelper.class);
	
	public static final String CTOR_INIT = "<init>";
	public static final String CTOR_CLINIT = "<clinit>";
	
//	public static String buildNameForCodeCoverage(MethodSignature methodSignature) {
//		logger.trace("##########################################################################");
//		String methodName = methodSignature.getName();
//		logger.trace("methodName before rename:" + methodName);
//
//		if (isLambdaExpression(methodSignature.getName())) {
//			methodName = renameLambdaExpression(methodSignature);
//		}
//		else {
//			String modifiers = accessToString(methodSignature.getAccess());
//			methodName = modifiers + internalNametoMethodSignature(methodName, methodSignature, true);
//			methodName = methodName.replaceAll("transient ", "varargs ");
//		}
//
//		logger.trace("methodSignature.getAccess():" + methodSignature.getAccess());
//		logger.trace("methodSignature.getClassName():" + methodSignature.getClassName());
//		logger.trace("methodSignature.getDescriptor():" + methodSignature.getDescriptor());
//		logger.trace("methodSignature.getName():" + methodSignature.getName());
//		logger.trace("methodSignature.getLineNumber():" + methodSignature.getEndLineNumber());
//		logger.trace("methodName after rename:" + methodName);
//		logger.trace("##########################################################################");
//
//		methodName = methodName.replace('$', '.');
//		return methodName;
//	}
//
//	public static String buildDisplayNameForBuildMapping(MethodSignature methodSignature) {
//        String access = accessToString(methodSignature.getAccess());
//        String descriptor = methodSignature.getDescriptor();
//        String returnType = returnTypeToString(descriptor);
//        String name = methodSignature.getName();
//        String argsType = getArgumentTypes(descriptor);
//
//		if (isLambdaExpression(methodSignature.getName())) {
//            return "lambda expression: " +returnType +" lambda("+argsType+")";
//        }
//        if(methodSignature.getClassification().isGroovyClosure()){
//        	return GroovyNamingHelper.renameClosureExpression(methodSignature, argsType);
//		}
//		if (isCtor(methodSignature.getName())) {
//			name = extractClassName(methodSignature.getClassName());
//			return access  +name+"("+argsType+")";
//		}
//		if (isStaticCtor(methodSignature.getName())){
//			name = extractClassName(methodSignature.getClassName());
//			return access +name+"("+argsType+")";
//		}
//		return access + returnType+" " +name+"("+argsType+")";
//	}
	
	public static String getElementId(int access, String name, String desc) {
		StringBuilder methodNameSb = new StringBuilder();
		
		String modifiers = accessToString(access);
		methodNameSb.append(modifiers);
		methodNameSb.append(Type.getReturnType(desc));
		methodNameSb.append(" " + name);
		methodNameSb.append(Arrays.toString(Type.getArgumentTypes(desc)));

		String fullyQualifiedName = methodNameSb.toString();
		return fullyQualifiedName;
	}

	/*
	 * Use this method to get more information about the current method (static,
	 * synchronized etc)
	 */
	public static String accessToString(int access) {

		StringBuilder sb = new StringBuilder();
		if ((access & Opcodes.ACC_PRIVATE) == Opcodes.ACC_PRIVATE) {
			sb.append("private ");
		}

		if ((access & Opcodes.ACC_PROTECTED) == Opcodes.ACC_PROTECTED) {
			sb.append("protected ");
		}

		if ((access & Opcodes.ACC_PUBLIC) == Opcodes.ACC_PUBLIC) {
			sb.append("public ");
		}

		if ((access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC) {
			sb.append("static ");
		}

		if ((access & Opcodes.ACC_SYNCHRONIZED) == Opcodes.ACC_SYNCHRONIZED) {
			sb.append("synchronized ");
		}

		if ((access & Opcodes.ACC_FINAL) == Opcodes.ACC_FINAL) {
			sb.append("final ");
		}

		if ((access & Opcodes.ACC_NATIVE) == Opcodes.ACC_NATIVE) {
			sb.append("native ");
		}

		if ((access & Opcodes.ACC_BRIDGE) == Opcodes.ACC_BRIDGE) {
			sb.append("bridge ");
		}

		if ((access & Opcodes.ACC_VARARGS) == Opcodes.ACC_VARARGS) {
			sb.append("varargs ");
		}

		if ((access & Opcodes.ACC_STRICT) == Opcodes.ACC_STRICT) {
			sb.append("strictfp ");
		}

		// if ((access & Opcodes.ACC_SYNTHETIC) == Opcodes.ACC_SYNTHETIC) {
		// sb.append("synthetic ");
		// }

		if ((access & Opcodes.ACC_ABSTRACT) == Opcodes.ACC_ABSTRACT) {
			sb.append("abstract ");
		}

		return sb.toString();
	}

//	private static String renameLambdaExpression(MethodSignature methodSignature) {
//		String escapedString = java.util.regex.Pattern.quote("$");
//
//		String originalName = methodSignature.getName();
//		String[] tokens = originalName.split(escapedString);
//
//		if (tokens.length != 3) {
//			// Unrecognized name format.
//			return originalName;
//		}
//
//		String methodContainer = tokens[1];
//		String lambdaSignature = internalNametoMethodSignature("lambda", methodSignature, false);
//
//		String modifiedName = "lambda expression (" + lambdaSignature + ") in class " + toPrintableValue(methodSignature.getClassName());
//		if ("new".equals(methodContainer) || "null".equals(methodContainer)) {
//			// Do nothing. The value should be the default modifiedName.
//		}
//		else if ("static".equals(methodContainer)) {
//			modifiedName = "static " + modifiedName;
//		}
//		else {
//			modifiedName = "lambda expression (" + lambdaSignature + ") in " + toPrintableValue(methodContainer) + " method, in class " + toPrintableValue(methodSignature.getClassName()) + "";
//		}
//		return modifiedName;
//	}


//	private static String internalNametoMethodSignature(String internalName, MethodSignature methodSignature, boolean isFullName) {
//		StringBuilder methodNameBuilder = new StringBuilder();
//
//		if (isCtorOrStaticCtor(methodSignature.getName())) {
//			methodNameBuilder.append(methodSignature.getClassName());
//		}
//		else {
//			Type returnType = Type.getReturnType(methodSignature.getDescriptor());
//			methodNameBuilder.append(returnType.getClassName());
//			methodNameBuilder.append(" ");
//
//			if (isFullName) {
//				methodNameBuilder.append(methodSignature.getClassName());
//				methodNameBuilder.append(".");
//			}
//			methodNameBuilder.append(internalName);
//		}
//
//		methodNameBuilder.append("(");
//
//		if (methodSignature.getDescriptor() != null) {
//			appendMethodParameters(methodSignature, methodNameBuilder);
//		}
//		methodNameBuilder.append(")");
//
//		return methodNameBuilder.toString();
//	}

	public static String returnTypeToString(String descriptor) {
        Type returnType = Type.getReturnType(descriptor);
        String className =returnType.getClassName();
        String type=extractClassName(className);
        return type;
    }

    public static String extractClassName(String className) {
	    String shortName;
        if(className.contains(".")){
            String[] splited = className.split("\\.");
            shortName = splited[splited.length-1];
        }
        else{
            shortName = className;
        }
        return shortName;
    }

    public static String getArgumentTypes(String descriptor) {
        List<String> argClasses = new ArrayList<>();
        for(Type argType :Type.getArgumentTypes(descriptor)){
            argClasses.add(extractClassName(argType.getClassName()));
        }
        return StringUtils.join(argClasses, ", ");
    }

//	public static void appendMethodParameters(MethodSignature methodSignature, StringBuilder methodNameBuilder) {
//		Type[] argumentTypes = Type.getArgumentTypes(methodSignature.getDescriptor());
//		final String sepeartor = ", ";
//		StringBuilder paramsBuilder = new StringBuilder();
//		for (Type t : argumentTypes) {
//			paramsBuilder.append(t.getClassName());
//			paramsBuilder.append(sepeartor);
//		}
//
//		String params = paramsBuilder.toString();
//		if (params.length() > 0) {
//			// Remove the last comma (",").
//			params = params.substring(0, params.lastIndexOf(sepeartor));
//		}
//
//		methodNameBuilder.append(params);
//	}

	public static String createMethodUniqueId(int access, String methodId, String descriptor, String exceptions) {
		return Integer.toString(access) + " | " + methodId + " | " + descriptor + " | " +exceptions;
	}

	public static boolean isLambdaExpression(String methodName) {
		return methodName.startsWith("lambda$");
	}
	
	public static boolean isCtor(String methodName) {
		return CTOR_INIT.equals(methodName);
	}
	
	public static boolean isStaticCtor(String methodName) {
		return CTOR_CLINIT.equals(methodName);
	}
	
	public static boolean isCtorOrStaticCtor(String methodName) {
		return isCtor(methodName) || isStaticCtor(methodName);
	}

	public static String removeLambdaIndex(String name){
		return name.substring(0,name.lastIndexOf("$"));
	}
	
	private static String toPrintableValue(String object) {
		if (object == null)
			return "<null>";
		return "'" + object + "'";
	}
}


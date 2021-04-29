package io.sealights.onpremise.slmock.agent.instrument.spring;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.objectweb.asm.Type;
//import org.slf4j.Logger;

import io.sealights.onpremise.slmock.agent.dto.ClassDiscoveryDTO;
import io.sealights.onpremise.slmock.agent.instrument.EndpointAnnotationDictionary;
import io.sealights.onpremise.slmock.agent.instrument.*;


/**
 * Implements Spring routes mapping discovery by  mapping annotations analysis
 * 
 * @author ala schneider   Apr 24, 2018
 *
 */
public class SpringRequestMappingAnalyzer {
	
	//private static Logger LOG = LogFactory.getLogger(SpringRequestMappingAnalyzer.class);
	
	private ClassDiscoveryDTO classDiscoveryDTO;
	private AnnotationValues classAnnotationValues;
	
	public SpringRequestMappingAnalyzer(ClassDiscoveryDTO classDiscoveryDTO) {
		this.classDiscoveryDTO = classDiscoveryDTO;
	}

	public ClassDiscoveryDTO execute() {
		System.out.println("Start Spring routing annotations discovery ...");
		classAnnotationValues = exploreAnnotations(classDiscoveryDTO.getClassToDiscover().getAnnotations(), "class");
		
		Method[] methods = classDiscoveryDTO.getClassToDiscover().getMethods();
		for (Method meth: methods) {
			AnnotationValues methodAnnotationValues = exploreAnnotations(meth.getAnnotations(), "method");
			if (methodAnnotationValues != null) {
				String fullPath = RouteBuilder.buildRoute(classAnnotationValues, methodAnnotationValues);
				String httpMethod = methodAnnotationValues.getMethod();
				if (httpMethod == null) {
					httpMethod = AnnotationValues.DEFAULT_METHOD;
				}
				String methodDesc = MethodNamingHelper.getElementId(
						meth.getModifiers(), 
						classDiscoveryDTO.getClassName() + "." + meth.getName(), 
						Type.getMethodDescriptor(meth));
				String exceptions = meth.getExceptionTypes().length>0 ? Arrays.toString(meth.getExceptionTypes()) : "null";
				String methodId = MethodNamingHelper.createMethodUniqueId(meth.getModifiers(), methodDesc, Type.getMethodDescriptor(meth), exceptions);
				classDiscoveryDTO.addRoute(methodId, meth.getName(), fullPath, httpMethod);
			}
		}
		System.out.println("Collected class discovery data: " + classDiscoveryDTO);
		return classDiscoveryDTO;
	}	
	
	protected AnnotationValues exploreAnnotations(Annotation[] annotations, String scope) {
		AnnotationValues annotationValues = null;
		for(Annotation ann : annotations) {
			//LOG.debug("Handling {} annotation: '{}'", scope, ann);
			if (ann.annotationType().getName().contains(EndpointAnnotationDictionary.REQUEST_MAPPING)) {
				annotationValues = new AnnotationValues();
				Method[] methods = ann.annotationType().getMethods();
				for (Method m : methods) {
					if (m.getName() == EndpointAnnotationDictionary.REQUEST_MAPPING_PATH) {
						String rawValues[] = (String[])getAnnotationValue(ann, m);
						if (rawValues != null && rawValues.length > 0) {
							// Actually only the first entry in the values array is used
							annotationValues.path = rawValues[0];
						}
					}
					if (m.getName() == EndpointAnnotationDictionary.REQUEST_MAPPING_METHOD) {
						Object[] rawValues = (Object[])getAnnotationValue(ann, m);
						String[] strEnumValues = new String[rawValues.length];
						for (int i=0; i < rawValues.length; i++) {
							Object rawEnum  = rawValues[i];
							strEnumValues[i] = getEnumValue(rawEnum);
						}
						if (strEnumValues.length > 0) {
							// Actually only the first entry in the values array is used
							annotationValues.method = strEnumValues[0].toUpperCase();
						}
					}
				}
			}
		}
		if (annotationValues != null ) {
			//LOG.trace("Collected {}", annotationValues);
		}
		return annotationValues;		
	}
	
	private String getEnumValue(Object enumObject) {
		try {
			Method valueMethod = enumObject.getClass().getMethod("name", null);
			return (String)valueMethod.invoke(enumObject, null);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private Object getAnnotationValue(Annotation ann, Method valueMethod) {
		try {
			return valueMethod.invoke(ann, (Object[]) null);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
package io.sl.ex.dynamicloading;

import io.sl.ex.loaders.DynamicClassLoader;
import io.sl.ex.utils.ThreadUtil;
import static io.sl.ex.utils.ReflectUtil.*;

public class ContextReloading {
	
	public static void main(String[] args) {
//		for (;;) {
//			Object context = createContext();
//			invokeHobbyService(context);
//			ThreadUtil.sleep(2000);
//		}
		execute(200);
	}
	
	public static boolean execute(int loops) {
		for (int i=0; i<loops; i++) {
			Object context = createContext();
			invokeHobbyService(context);
			ThreadUtil.sleep(2000);
		}
		return true;
	}

	private static Object createContext() {
		DynamicClassLoader dynamicLoader = new DynamicClassLoader("target/classes");
		dynamicLoader.setParent(ClassLoader.getSystemClassLoader());
		Class<?> contextClass = dynamicLoader.load("io.sl.ex.dynamicloading.ContextReloading$Context");
		Object context = newInstance(contextClass);
		invoke("init", context);
		return context;
	}

	private static void invokeHobbyService(Object context) {
		Object hobbyService = getFieldValue("hobbyService", context);
		invoke("hobby", hobbyService);
	}

	public static class Context {
		public HobbyService hobbyService = new HobbyService();
		
		public void init() {
			// Init your services here
			hobbyService.user = new User();
		}
	}
	
	public static class HobbyService {

		public User user;
		
		public void hobby() {
			User.hobby();
		}
	}
	
	public static class User {
		public static void hobby() {
//			playFootball(); // Will comment during runtime
			playBasketball(); // Will uncomment during runtime
		}
		
		// Will comment during runtime
//		public static void playFootball() {
//			System.out.println("Play Football");
//		}
		
//		Will uncomment during runtime
		public static void playBasketball() {
			System.out.println("Play Basketball");
		}
	}
}
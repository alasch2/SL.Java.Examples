package io.sl.ex.dynamicloading;

import io.sl.ex.loaders.DynamicClassLoader;
import io.sl.ex.utils.ReflectUtil;
import io.sl.ex.utils.ThreadUtil;

public class ReloadingContinuously {
	public static void main(String[] args) {
		for (;;) {
			Class<?> userClass = new DynamicClassLoader("target/classes")
				//.load("io.sl.ex.dynamicloading.ReloadingContinuously$User");
					.load("io.sl.ex.example1.StaticInt$User");
					
			ReflectUtil.invokeStatic("hobby", userClass);
			ThreadUtil.sleep(2000);
		}
	}
	
	public static class User {
		public static void hobby() {
			playFootball(); // Will comment during runtime
//			playBasketball(); // Will uncomment during runtime
		}
		
		// Will comment during runtime
		public static void playFootball() {
			System.out.println("Play Football");
		}
		
		// Will uncomment during runtime
		public static void playBasketball() {
			System.out.println("Play Basketball");
		}
	}
}
package io.sl.ex.utils;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

public class Functions {

	public static class Fs extends FsGenerated {
		
	    public static Runnable runnable(final P0 action) {
	        return new Runnable() {
	            public void run() {
	                action.e();
	            }
	        };
	    }
		
		/**
		 * Just store the object to collection
		 * @param col
		 * @return
		 */
	    public static <A> P1<A> store(final Collection<A> col) {
	        return new P1<A>() {public void e(A a) {
	            col.add(a);
	        }};
	    }
	    
		public static <A> P1<A> setter(
				final AtomicReference<A> ref) {
			return new P1<A>() {public void e(A obj) {
				ref.set(obj);
			}};
		}
		
		public static <A> void invokeAll(final Collection<P1<A>> col, A a) {
			for (P1<A> p1 : col) {
				p1.e(a);
			}
		}
	}
	
	public static class FsGenerated {

		/**
		 * Convert from a p0 to p1
		 * @return p1
		 */
		public static <A> P1<A> p1(final P0 p0) {
			return new P1<A>(){public void e(final A a) {
				p0.e();
			}};
		}
		
		/**
		 * Call to p and return fixed value
		 * @param p1 the function to call before return value
		 * @param ret the fixed value to return
		 * @return ret
		 */
		public static <A, R> F1<A, R> f1(final P1<A> p1, final R ret) {
			return new F1<A, R>(){public R e(final A a) {
				p1.e(a);
				return ret;
			}};
		}
		
		/**
		 * Call to p and return fixed value
		 * @param p2 the function to call before return value
		 * @param ret the fixed value to return
		 * @return ret
		 */
		public static <A, B, R> F2<A, B, R> f2(final P2<A, B> p2, final R ret) {
			return new F2<A, B, R>(){public R e(final A a, final B b) {
				p2.e(a, b);
				return ret;
			}
			};
		}
	}
	
	public static interface F0<T> {
	    /**
	     * Evaluate or execute the function
	     * @return Result of execution
	     */
	    T e();
	}
	
	/**
	 * Represent a function that accept one parameter and return value
	 * @param <A> The only parameter
	 * @param <T> The return value
	 */
	public static interface F1<A, T> {
	    /**
	     * Evaluate or execute the function
	     * @param obj The parameter
	     * @return Result of execution
	     */
		T e(A obj);
	}
	
	public interface F2<A, B, T>{
		T e(A a, B b);
	}
	
	public static interface P0 {
		void e();
	}
	
	/**
	 * Function that accept 1 objects and return nothing
	 * @author QuanLA
	 *
	 * @param <A>
	 */
	public static interface P1<A> {
		void e(A obj);
	}
	
	/**
	 * Function that accept 2 objects and return nothing
	 * @author QuanLA
	 *
	 * @param <A>
	 * @param <B>
	 */
	public static interface P2<A, B> {
		void e(A a, B b);
	}
	
}

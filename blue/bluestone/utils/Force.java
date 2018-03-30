package blue.bluestone.utils;

/**
 * ForceUtils is a class that allows you to force a code to run.
 * 
 * @author DrBenana
 *
 */
public class Force {
	
	/**
	 * forceRun allows you to run a code until it works. Every time that the code is failed, The catch runs.
	 * @param tryCode Runs again and again.
	 * @param catchCode Runs when the tryCode is failed.
	 * @param max The maximum fails of the code.
	 * @return The last return.
	 */
	public static <T> T forceRun(TRunnable<T> tryCode, TRunnable<T> catchCode, int max) {
		try {
			return tryCode.run();
		} catch (Exception e) {
			T a = catchCode.run();
			if (max == 0) return a;
			try {
				return forceRun(tryCode, catchCode, max-1);
			} catch (Exception ex) {
				return a;
			}
		}
	}
	
	/**
	 * forceRun allows you to run a code until it works. Every time that the code is failed, The catch runs.
	 * @param tryCode Runs again and again.
	 * @param catchCode Runs when the tryCode is failed.
	 * @return The last return.
	 */
	public static <T> T forceRun(TRunnable<T> tryCode, TRunnable<T> catchCode) {
		return forceRun(tryCode, catchCode, -1);
	}
	
	/**
	 * forceRun allows you to run a code until it works. Every time that the code is failed, The catch runs.
	 * @param tryCode Runs again and again.
	 * @param catchCode Runs when the tryCode is failed.
	 * @param max The maximum fails of the code.
	 */
	public static void forceRun(Runnable tryCode, Runnable catchCode, int max) {
		forceRun(() -> {
			tryCode.run();
			return null;
		}, () -> {
			catchCode.run();
			return null;
		}, max);
	}
	
	/**
	 * forceRun allows you to run a code until it works. Every time that the code is failed, The catch runs.
	 * @param tryCode Runs again and again.
	 * @param catchCode Runs when the tryCode is failed.
	 */
	public static void forceRun(Runnable tryCode, Runnable catchCode) {
		forceRun(tryCode, catchCode, -1);
	}
	
	/**
	 * tryCatch allows you to try to run a code, then if it fails, it runs the catch.
	 * @param tryCode The code to try to run.
	 * @param catchCode The code that runs if the tryCode is failed.
	 * @return The last return.
	 */
	public static <T> T tryCatch(TRunnable<T> tryCode, TRunnable<T> catchCode) {
		try {
			return tryCode.run();
		} catch (Exception e) {
			return catchCode.run();
		}
	}
	
	/**
	 * tryCatch allows you to try to run a code.
	 * @param tryCode The code to try to run.
	 * @return The last return.
	 */
	public static <T> T tryCatch(TRunnable<T> tryCode) {
		return tryCatch(tryCode, () -> { return null; });
	}
	
	/**
	 * tryCatch allows you to try to run a code, then if it fails, it runs the catch.
	 * @param tryCode The code to try to run.
	 * @param catchCode The code that runs if the tryCode is failed.
	 */
	public static void tryCatch(Runnable tryCode, Runnable catchCode) {
		tryCatch(() -> {
			tryCode.run();
			return null;
		}, () -> {
			catchCode.run();
			return null;
		});
	}
	
	/**
	 * tryCatch allows you to try to run a code.
	 * @param tryCode The code to try to run.
	 */
	public static void tryCatch(Runnable tryCode) {
		tryCatch(tryCode, () -> {});
	}
}

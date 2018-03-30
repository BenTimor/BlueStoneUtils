package blue.bluestone.utils;

import java.util.Timer;
import java.util.TimerTask;

public class Synchronize {

	/**
	 * Runs code in a thread.
	 * @param code
	 */
	public static void async(Runnable code) {
		new Thread(() -> code.run()).start();
	}

	/**
	 * Runs code in a thread after some miliseconds.
	 * @param code
	 * @param delay The delay you have to wait in miliseconds.
	 */
	public static void async(Runnable code, long delay) {
		async(() -> {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					code.run();
				}
			}, delay);
		});
	}
	
}

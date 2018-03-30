package blue.bluestone.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Allows you to save and load serializabled objects.
 * 
 * @author DrBenana
 *
 */
public class Serialization {
	
	/**
	 * Saves a serializabled object to a file.
	 * @param obj The object.
	 * @param file The name / path of the file.
	 */
	public static void saveObject(String file, Serializable obj) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Loads a serializabled object from a file.
	 * @param file The name / path of the file.
	 * @param catchCode A code that runs if it fails. After a fail, It tries again to load the object (only once)
	 * @return The object.
	 */
	public static Serializable loadObject(String file, TRunnable<Serializable> catchCode) {
		return Force.forceRun(() -> {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				ois.close();
				fis.close();
				return (Serializable) obj;
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}, () -> catchCode.run(), 1);
	}
	
}

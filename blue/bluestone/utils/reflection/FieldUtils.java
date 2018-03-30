package blue.bluestone.utils.reflection;

import java.lang.reflect.Field;

/**
 * <b> Cloned from BaseCore. It may do problems. </b>
 * 
 * @author DrBenana
 */
public class FieldUtils {
	
	/**
	 * @param o Object to get the field from.
	 * @param field The name of the field.
	 * @return The content of the field.
	 */
	public static Object getDeclaredField(Object o, String field) {
		return getDeclaredField(o.getClass(), o, field);
	}
	
	/**
	 * @param clazz The class to get the field from.
	 * @param o Object to get the field from.
	 * @param field The name of the field.
	 * @return The content of the field.
	 */
	public static Object getDeclaredField(Class<?> clazz, Object o, String field) {
		try {
			Field lastField = null;
			for (String s : field.split("\\.")) {
				lastField = clazz.getDeclaredField(s);
				boolean isAcssible = lastField.isAccessible();
				lastField.setAccessible(true);
				o = lastField.get(o);
				lastField.setAccessible(isAcssible);
				clazz = lastField.getType();
			}
			return o;
		} catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			throw new Error(e);
		}
	}
	
	/**
	 * @param clazz The class of the field.
	 * @param o The object to set the field to.
	 * @param fieldName The name of the field.
	 * @param fieldNewValue The new value to set to the field.
	 */
	public static void setDeclaredField(Class<?> clazz, Object o, String fieldName, Object fieldNewValue) {
		try {
			Field lastField = null;
			boolean isAcssible = true;
			for (String s : fieldName.split("\\.")) {
				if (lastField != null) {
					isAcssible = lastField.isAccessible();
					lastField.setAccessible(true);
					o = lastField.get(o);
					lastField.setAccessible(isAcssible);
				}
				lastField = clazz.getDeclaredField(s);
				clazz = o.getClass();
			}
			lastField.setAccessible(true);
			lastField.set(o, fieldNewValue);
			lastField.setAccessible(isAcssible);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new Error(e);
		}
	}
	
	/**
	 * @param o The object to set the field to.
	 * @param fieldName The name of the field.
	 * @param fieldNewValue The new value to set to the field.
	 */
	public static void setDeclaredField(Object o, String fieldName, Object fieldNewValue) {
		setDeclaredField(o.getClass(), o, fieldName, fieldNewValue);
	}
	
	/**
	 * @param clazz The class of the field.
	 * @param fieldName The name of the field.
	 * @return Does the field exist.
	 */
	public static boolean hasDeclaredField(Class<?> clazz, String fieldName) {
		try {
			Field lastField = null;
			for (String s : fieldName.split("\\.")) {
				lastField = clazz.getDeclaredField(s);
				boolean isAcssible = lastField.isAccessible();
				lastField.setAccessible(true);
				clazz = lastField.getType();
				lastField.setAccessible(isAcssible);
			}
			return true;
		} catch (SecurityException | IllegalArgumentException | NoSuchFieldException e) {
			throw new Error(e);
		}
	}
	
	
	/**
	 * @param o The object of the field.
	 * @param fieldName The name of the field.
	 * @return Does the field exist.
	 */
	public static boolean hasDeclaredField(Object o, String fieldName) {
		return hasDeclaredField(o.getClass(), fieldName);
	}
	
	
}

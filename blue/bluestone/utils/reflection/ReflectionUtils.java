package blue.bluestone.utils.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <b> Cloned from BaseCore. It may do problems. </b>
 * 
 * @author DrBenana
 */
public class ReflectionUtils {
	
	/**
	 * @param name The path of the class
	 * @return The class
	 */
	public static Class<?> getClass(String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) { return null; }
	}
	
	public static Class<?> getArrayClass(String name) {
	    return Array.newInstance(getClass(name), 0).getClass();
	}
	
	/**
	 * Calls to declared method on the class.
	 * @param clazz The class that contains the method.
	 * @param o The object to run the method from.
	 * @param method The method.
	 * @param parameters The parameters to put to method.
	 * @return The return of the method.
	 */
	public static Object invokeDeclaredMethod(Class<?> clazz, Object o, String method, Parameter... parameters) {
		try {
			Method m = clazz.getDeclaredMethod(method, Parameter.getClazzList(parameters));
			boolean isA = m.isAccessible();
			m.setAccessible(true);
			Object value = m.invoke(o, Parameter.getObjectList(parameters));
			m.setAccessible(isA);
			return value;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			throw new Error(e);
		}
	}
	
	/**
	 * Calls to declared method on the class.
	 * @param o The object to run the method from.
	 * @param method The method.
	 * @param parameters The parameters to put to method.
	 * @return The return of the method.
	 */
	public static Object invokeDeclaredMethod(Object o, String method, Parameter... parameters) {
		return invokeDeclaredMethod(o.getClass(), o, method, parameters);
	}
	
	/**
	 * Calls to declared method on the class.
	 * @param clazz The class that contains the method.
	 * @param o The object to run the method from.
	 * @param method The method.
	 * @param throwOutput Output the error?
	 * @param parameters The parameters to put to method.
	 * @return The return of the method.
	 */
	public static Object invokeDeclaredMethod(Class<?> clazz, Object o, String method, boolean throwOutput, Parameter... parameters) {
		try {
			Method m = clazz.getDeclaredMethod(method, Parameter.getClazzList(parameters));
			boolean isA = m.isAccessible();
			m.setAccessible(true);
			Object value = m.invoke(o, Parameter.getObjectList(parameters));
			m.setAccessible(isA);
			return value;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			if (throwOutput) throw new Error(e);
		}
		return null;
	}
	
	/**
	 * Calls to declared method on the class.
	 * @param o The object to run the method from.
	 * @param method The method.
	 * @param throwOutput Output the error?
	 * @param parameters The parameters to put to method.
	 * @return The return of the method.
	 */
	public static Object invokeDeclaredMethod(Object o, String method, boolean throwOutput, Parameter... parameters) {
		return invokeDeclaredMethod(o.getClass(), o, method, throwOutput, parameters);
	}
	
	/**
	 * @param c The class of the method
	 * @param name The name of the method
	 * @return Has the method
	 */
	public static boolean hasMethod(Class<?> c, String name) {
		for (Method m : c.getMethods())
			if (m.getName().equals(name)) return true;
		return false;
	}
	
	/**
	 * @param objects The objects to get the class from
	 * @return Class Array
	 */
	public static Class<?>[] toClass(Object... objects) {
		Class<?>[] classes = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) classes[i] = objects[i].getClass();
		return classes;
	}
	
	/**
	 * Example: getFullDeclaredObject(Class, Object, "Field.Field.Method(***).Field", FirstParameter, SecondParameter, ThirdParameter)
	 * @param clazz The class of the object
	 * @param o The object
	 * @param path The path (can contain dots)
	 * @param parameters The parameters of the methods
	 * @return The last object.
	 */
	public static Object getFullDeclaredObject(Class<?> clazz, Object o, String path, Parameter... parameters) {
		try {
			Field lastField = null;
			int lastArg = 0;
			for (String s : path.split("\\.")) {
				if (s.contains("(")) {					
					Parameter[] p = new Parameter[s.split("\\(")[1].split("\\*").length-1];
					for (int i = 0; i < p.length; i++) {
						p[i] = parameters[lastArg];
						lastArg++;
					}
					o = invokeDeclaredMethod(o, s.split("\\(")[0], p);
					clazz = o.getClass();
					continue;
				}
				lastField = clazz.getDeclaredField(s);
				boolean isAcssible = lastField.isAccessible();
				lastField.setAccessible(true);
				o = lastField.get(o);
				lastField.setAccessible(isAcssible);
				clazz = o.getClass();
			}
			return o;
		} catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			throw new Error(e);
		}
	}
	
	/**
	 * @param o The object
	 * @param path The path (can contain dots)
	 * @param parameters The parameters of the methods
	 * @return The last object.
	 */
	public static Object getFullDeclaredObject(Object o, String path, Parameter... parameters) {
		return getFullDeclaredObject(o.getClass(), o, path, parameters);
	}
	
	/**
	 * @param clazz The class of the constructor
	 * @param parameters The parameters of the constructor
	 * @return The object
	 */
	public static Object invokeConstructor(Class<?> clazz, Parameter... parameters) {
		try {
			return clazz.getDeclaredConstructor(Parameter.getClazzList(parameters)).newInstance(Parameter.getObjectList(parameters));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new Error(e);
		}
	}
	
}

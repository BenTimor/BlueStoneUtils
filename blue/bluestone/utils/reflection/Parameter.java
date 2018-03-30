package blue.bluestone.utils.reflection;

/**
 * <b> Cloned from BaseCore. It may do problems. </b>
 * 
 * @author DrBenana
 */
public class Parameter {
	private Class<?> clazz;
	private Object obj;
	
	/**
	 * @param clazz The class of the object.
	 * @param obj The object.
	 */
	public Parameter(Class<?> clazz, Object obj) {
		this.setClazz(clazz);
		this.setObject(obj);
	}
	
	/**
	 * Gets the class directly from the object. It doesn't always exact but it may work well.
	 * @param obj
	 */
	public Parameter(Object obj) {
		this(obj.getClass(), obj);
	}
	
	/**
	 * @return The class of the object.
	 */
	public Class<?> getClazz() {
		return clazz;
	}
	
	/**
	 * @param clazz Replace the current class by another class.
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * @return The object of the parameter.
	 */
	public Object getObject() {
		return obj;
	}
	
	/**
	 * @param obj Replace the current object by another object.
	 */
	public void setObject(Object obj) {
		this.obj = obj;
	}
	
	/**
	 * @param parameters The parameters
	 * @return Array of the classes of the parameters
	 */
	public static Class<?>[] getClazzList(Parameter... parameters) {
		Class<?>[] clazzes = new Class[parameters.length];
		for (int i = 0; i < parameters.length; i++)
			clazzes[i] = parameters[i].getClazz();
		return clazzes;
	}
	
	/**
	 * @param parameters The parameters
	 * @return Array of the objects of the parameters
	 */
	public static Object[] getObjectList(Parameter... parameters) {
		Object[] objects = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++)
			objects[i] = parameters[i].getObject();
		return objects;
	}
	
}

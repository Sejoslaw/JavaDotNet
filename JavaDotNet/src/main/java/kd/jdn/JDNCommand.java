package main.java.kd.jdn;

/**
 * JavaDotNet internal command values.
 * 
 * @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzynski</a> -> https://github.com/Sejoslaw
 */
public class JDNCommand 
{
	/**
	 * Add new reference command id.
	 * 
	 * @see {@link JavaDotNet#addReference(String)}
	 */
	public static final int ADD_NEW_REFERENCE = 1;
	/**
	 * Create new .NET Object.
	 * 
	 * @see {@link JavaDotNet#create(String)}
	 * @see {@link JavaDotNet#create(String, Object[])}
	 */
	public static final int NEW_OBJECT = 2;
	/**
	 * Return .NET Type.
	 * 
	 * @see {@link JavaDotNet#getType(String)}
	 */
	public static final int GET_TYPE = 3;
	/**
	 * Invoke method.
	 * 
	 * @see {@link JavaDotNet#invoke(String, int, String, Object[])}
	 */
	public static final int INVOKE = 4;
	/**
	 * Get the value of the field.
	 * 
	 * @see {@link JavaDotNet#getFieldValue(String, int)}
	 */
	public static final int GET_FIELD_VALUE = 5;
	/**
	 * Set new value for the field.
	 * 
	 * @see {@link JavaDotNet#setFieldValue(String, Object)}
	 */
	public static final int SET_FIELD_VALUE = 6;
	
	private JDNCommand() {}
}
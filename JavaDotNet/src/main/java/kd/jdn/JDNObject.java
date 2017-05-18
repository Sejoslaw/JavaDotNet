package main.java.kd.jdn;

/**
 * This is the lowest level wrapper of the .NET Object.
 * 
 * @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyñski</a> -> https://github.com/Sejoslaw
 */
public class JDNObject
{
	/**
	 * Unique identifier of this object.
	 * ( for future use )
	 */
	private final int _objectId;
	/**
	 * Name of the .NET Type.
	 */
	private final String _typeName;
	
	/**
	 * Answer:
	 * answer[0] - unique identifier of this new object
	 */
	public JDNObject(String[] answer)
	{
		this._objectId = Integer.valueOf(answer[0]);
		this._typeName = answer[1];
	}
	
	/**
	 * @return Returns the unique identifier of this object.
	 */
	public int getObjectId()
	{
		return this._objectId;
	}
	
	/**
	 * @return Returns the name of this .NET Type.
	 */
	public String getTypeName()
	{
		return this._typeName;
	}
	
	/**
	 * Invoke method.
	 * 
	 * @param methodName Name of the .NET method which should be run.
	 * @param parameters .NET Object constructor parameters.
	 * 
	 * @return Returns new .NET Object
	 */
	public JDNObject invoke(String methodName, Object[] parameters)
	{
		return JavaDotNet.INSTANCE.invoke(this._typeName, this._objectId, methodName, parameters);
	}
	
	/**
	 * Get value.
	 * 
	 * @param fieldName Name of the .NET Field.
	 * 
	 * @return Returns the value of the .NET Field from the given field name.
	 */
	public JDNObject get(String fieldName)
	{
		return JavaDotNet.INSTANCE.getFieldValue(fieldName, this._objectId);
	}
	
	/**
	 * Sets new value.
	 * 
	 * @param fieldName Name of the field to which new value should be set.
	 * @param newValue New value for the field.
	 */
	public void set(String fieldName, Object newValue)
	{
		JavaDotNet.INSTANCE.setFieldValue(fieldName, newValue, this._objectId);
	}
}
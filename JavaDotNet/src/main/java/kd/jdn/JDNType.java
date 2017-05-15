package main.java.kd.jdn;

/**
 * This class represents a single .NET Type.
 * 
 * @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyñski</a> -> https://github.com/Sejoslaw
 */
public class JDNType 
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
	 * Where answer is:
	 * answer[0] - unique identifier of this new object
	 * answer[1] - name of this type
	 */
	public JDNType(String[] answer)
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
	 * @return Returns new .NET Object
	 */
	public JDNObject create()
	{
		return JavaDotNet.INSTANCE.create(this._typeName);
	}
	
	/**
	 * @param parameters New .NET Object constructor parameters.
	 * 
	 * @return Returns new .NET Object
	 */
	public JDNObject create(Object[] parameters)
	{
		return JavaDotNet.INSTANCE.create(this._typeName, parameters);
	}
	
	/**
	 * Invoke static method.
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
	 * Get static value.
	 * 
	 * @param staticFieldName Name of the static .NET Field.
	 * 
	 * @return Returns the value of the .NET Field from the given field name.
	 */
	public JDNObject get(String staticFieldName)
	{
		return JavaDotNet.INSTANCE.getFieldValue(staticFieldName, this._objectId);
	}
	
	/**
	 * Sets new static value.
	 * 
	 * @param staticFieldName Name of the static field to which new value should be set.
	 * @param newValue New value for the field.
	 */
	public void set(String staticFieldName, Object newValue)
	{
		JavaDotNet.INSTANCE.setFieldValue(staticFieldName, newValue, this._objectId);
	}
}
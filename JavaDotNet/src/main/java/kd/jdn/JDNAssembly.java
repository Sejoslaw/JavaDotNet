package main.java.kd.jdn;

/**
 * This class represents a single .NET Assembly file.
 * 
 * @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyñski</a> -> https://github.com/Sejoslaw
 */
public class JDNAssembly 
{
	/**
	 * Unique identifier of this object.
	 * ( for future use )
	 */
	private final int _objectId;
	/**
	 * Path or name of the loaded .NET Assembly.
	 */
	private final String _assemblyNameOrPath;
	
	/**
	 * Where answer is:
	 * answer[0] - unique identifier of this new object
	 * answer[1] - name of loaded assembly
	 */
	public JDNAssembly(String[] answer)
	{
		this._objectId = Integer.valueOf(answer[0]);
		this._assemblyNameOrPath = answer[1];
	}
	
	/**
	 * @return Returns the unique identifier of this object.
	 */
	public int getObjectId()
	{
		return this._objectId;
	}
	
	/**
	 * @return Returns the path or the name of this .NET Assembly.
	 */
	public String getAssemblyNameOrPath()
	{
		return this._assemblyNameOrPath;
	}
	
	/**
	 * @param typeName Name of the .NET Type.
	 * 
	 * @return Returns the .NET Type if exists.
	 */
	public JDNType getType(String typeName)
	{
		return JavaDotNet.INSTANCE.getType(typeName);
	}
	
	/**
	 * @param typeName Name of the .NET Type.
	 * 
	 * @return Returns the newly created .NET Object.
	 */
	public JDNObject create(String typeName)
	{
		JDNType type = getType(typeName);
		return type.create();
	}
	
	/**
	 * @param typeName Name of the .NET Type.
	 * @param parameters New .NET Object constructor parameters.
	 * 
	 * @return Returns the newly created .NET Object.
	 */
	public JDNObject create(String typeName, Object[] parameters)
	{
		JDNType type = getType(typeName);
		return type.create(parameters);
	}
}
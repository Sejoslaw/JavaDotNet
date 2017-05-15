package main.java.kd.jdn;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the root class of the JavaDotNet library.
 * 
 * @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyñski</a> -> https://github.com/Sejoslaw
 */
public class JavaDotNet
{
	/**
	 * Single instance of this bridge class.
	 */
	public static final JavaDotNet INSTANCE = new JavaDotNet();
	/**
	 * Current version of JDN - last build time.
	 */
	public final String version = "2017.05.14-18.47.11";
	/**
	 * JDN internal Logger.
	 */
	public final Logger logger = Logger.getLogger("JKDL");
	/**
	 * Main folder which contains all basic .NET libraries
	 */
	public String dotNetDir = "C:/Windows/Microsoft.NET/Framework/v4.0.30319";
	/**
	 * Extension of the DLL file.
	 */
	public String dllExt = ".dll";
	
	private JavaDotNet() 
	{
		System.loadLibrary("JavaDotNet.NET");
	}
	
	/**
	 * Adds and returns new .NET Assembly.
	 * 
	 * @param assemblyNameOrPath Name of the Assembly or path from which the new Assembly should be added.
	 * 
	 * @return Returns the newly added Assembly.
	 */
	public JDNAssembly addReference(String assemblyNameOrPath)
	{
		/**
		 * Arguments: 
		 * JavaDotNet command id, 
		 * "assemblyNameOrPath" - name of the assembly which should be added
		 */
		String[] dotNetAnswer = communicateDotNet(new String[] { String.valueOf(JDNCommand.ADD_NEW_REFERENCE), assemblyNameOrPath });
		return new JDNAssembly(dotNetAnswer);
	}
	
	/**
	 * Creates and returns a new .NET Object from the given name of the .NET Type.
	 * 
	 * @param typeName Type of the new .NET Object.
	 * 
	 * @return Returns the newly created .NET Object.
	 */
	public JDNObject create(String typeName)
	{
		/**
		 * Arguments:
		 * JDN command id
		 */
		String[] dotNetAnswer = communicateDotNet(new String[] { String.valueOf(JDNCommand.NEW_OBJECT) });
		return new JDNObject(dotNetAnswer);
	}
	
	/**
	 * Creates and returns a new .NET Object from the given name of the .NET Type and Parameters.
	 * 
	 * @param typeName Type of the new .NET Object.
	 * @param parameters New .NET Object constructor parameters.
	 * 
	 * @return Returns the newly created .NET Object.
	 */
	public JDNObject create(String typeName, Object[] parameters)
	{
		/**
		 * Arguments:
		 * JavaDotNet command id
		 * parameters - converted parameters
		 */
		String[] dotNetAnswer = communicateDotNet(new String[] { String.valueOf(JDNCommand.NEW_OBJECT), JDNUtils.parseParameters(parameters) });
        return new JDNObject(dotNetAnswer);
	}
	
	/**
	 * Returns the .NET Type from the given name.
	 * 
	 * @param typeName Name of the .NET Type.
	 * 
	 * @return Returns the .NET Type if exists.
	 */
	public JDNType getType(String typeName)
	{
		/**
		 * Arguments:
		 * JDN command id
		 */
		String[] dotNetAnswer = communicateDotNet(new String[] { String.valueOf(JDNCommand.GET_TYPE) });
        return new JDNType(dotNetAnswer);
	}
	
	/**
	 * Invokes a .NET Method.
	 * 
	 * @param typeName Name of the .NET Type from which to invoke method.
	 * @param objectId Unique identifier of the object from which the method is used.
	 * @param methodName Name of the .NET method which should be run.
	 * @param parameters Additional parameters parse as .NET method parameters.
	 * 
	 * @return Returns the return value of the invoked method.
	 */
	public JDNObject invoke(String typeName, int objectId, String methodName, Object[] parameters)
	{
		/**
		 * Arguments:
		 * .NET Type name
		 * Object id
		 * .NET method name
		 * .NET arguments
		 */
		String[] answer = communicateDotNet(new String[] { String.valueOf(JDNCommand.INVOKE), typeName, String.valueOf(objectId), methodName, JDNUtils.parseParameters(parameters) });
		return new JDNObject(answer);
	}
	
	/**
	 * @param fieldName Name of the .NET field
	 * @param objectId Unique identifier of the .NET object
	 * 
	 * @return Returns the value of the .NET field.
	 */
	public JDNObject getFieldValue(String fieldName, int objectId)
	{
		/**
		 * Arguments:
		 * .NET field name
		 * Object id
		 */
		String[] answer = communicateDotNet(new String[] { String.valueOf(JDNCommand.GET_FIELD_VALUE), fieldName, String.valueOf(objectId) });
		return new JDNObject(answer);
	}
	
	/**
	 * Sets new value for the given field.
	 * 
	 * @param fieldName Name of the field for which value should be set.
	 * @param newValue New value which should be set for field.
	 * @param objectId Unique identifier of the .NET object.
	 */
	public void setFieldValue(String fieldName, Object newValue, int objectId)
	{
		communicateDotNet(new String[] { String.valueOf(JDNCommand.SET_FIELD_VALUE), fieldName, String.valueOf(objectId), JDNUtils.parseParameters(new Object[] { newValue }) });
	}
	
	/**
	 * Add log using JDN Logger.
	 */
	public void log(Level level, String message)
	{
		logger.log(level, message);
	}
	
	/**
	 * Native method to communicate with .NET
	 * 
	 * @param args Arguments parse to .NET
	 * 
	 * @return Returns the .NET answer.
	 */
	public native String[] communicateDotNet(String[] args);
}
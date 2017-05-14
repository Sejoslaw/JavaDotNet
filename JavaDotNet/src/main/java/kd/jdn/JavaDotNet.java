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
	 * Current version of JDN - last build time.
	 */
	public static final String VERSION = "2017.05.14-18.47.11";
	/**
	 * JDN internal Logger.
	 */
	public static final Logger LOGGER = Logger.getLogger("JKDL");
	/**
	 * Main folder which contains all basic .NET libraries
	 */
	public static String DOTNET_DIR = "C:/Windows/Microsoft.NET/Framework/v4.0.30319";
	/**
	 * Extension of the DLL file.
	 */
	public static String DLL_EXT = ".dll";
	
	private JavaDotNet() { }
	
	/**
	 * Initialize Bridge
	 */
	public static void Initialize()
	{
	}
	
	/**
	 * Adds and returns new .NET Assembly.
	 * 
	 * @param assemblyNameOrPath Name of the Assembly or path from which the new Assembly should be added.
	 * 
	 * @return Returns the newly added Assembly.
	 */
	public static JDNAssembly addReference(String assemblyNameOrPath)
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
	public static JDNObject create(String typeName)
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
	public static JDNObject create(String typeName, Object[] parameters)
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
	 * @return Returns the .NET Type if exists.
	 */
	public static JDNType getType(String typeName)
    {
		/**
		 * Arguments:
		 * JDN command id
		 */
		String[] dotNetAnswer = communicateDotNet(new String[] { String.valueOf(JDNCommand.GET_TYPE) });
        return new JDNType(typeName);
    }
	
	/**
	 * Add log using JDN Logger.
	 */
	public static void log(Level level, String message)
	{
		LOGGER.log(level, message);
	}
	
	/**
	 * Native method to communicate with .NET
	 * 
	 * @param args Arguments parse to .NET
	 * 
	 * @return Returns the .NET answer.
	 */
	public static native String[] communicateDotNet(String[] args);
}
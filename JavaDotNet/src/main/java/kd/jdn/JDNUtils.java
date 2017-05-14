package main.java.kd.jdn;

/**
 * This class contains various utilities for JavaDotNet.
 * 
 * @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyñski</a> -> https://github.com/Sejoslaw
 */
public class JDNUtils 
{
	private JDNUtils() { }
	
	/**
	 * Read parameters and return them as a String - used mainly to create new .NET Object.
	 * 
	 * @param parameters Parameters which should be parse to .NET Object constructor.
	 * 
	 * @return Returns the String representing converted Objects.
	 */
	public static String parseParameters(Object[] parameters)
	{
		if (parameters == null || parameters.length == 0) return "";
	}
}
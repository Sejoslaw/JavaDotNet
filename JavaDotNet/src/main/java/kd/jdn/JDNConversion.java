package main.java.kd.jdn;

/**
 * This class contains various utilities for JavaDotNet.
 * 
 * @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyñski</a> -> https://github.com/Sejoslaw
 */
public class JDNConversion 
{
	/**
	 * Space which indicates when parameter ends and when next parameter starts.
	 * This should be a unique symbol beetween ANY possible combinations of parameters.
	 */
	public static final String SPACE = ";!;!;";
	
	private JDNConversion() { }
	
	/**
	 * When .NET answers the Java it returns it's answer in a form of String.
	 * This String is here divided into peaces for use of an constructors of JDN Classes.
	 * 
	 * @param dontNetAnswerString String which is an answer from .NET side.
	 * 
	 * @return Returns the array with selected separate values.
	 */
	public static String[] readAnswer(String dontNetAnswerString)
	{
		return dontNetAnswerString.split(SPACE);
	}
	
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
		// Return string
		String answer = "";
		for (Object object : parameters)
			answer += parseParameter(object) + SPACE;
		return answer;
	}
	
	/**
	 * Read parameter and return it as a String representation - used for conversion between Java and .NET
	 * Each new conversion MUST BE BEFORE last Object conversion.
	 * 
	 * @param parameter Object which should be convert to JDN cross-platform String representation.
	 * 
	 * @return Return converted input value as a String representation.
	 */
	public static String parseParameter(Object parameter)
	{
		if (Byte.class.isInstance(parameter))			return "Byte:" + parameter;
		else if (Short.class.isInstance(parameter))		return "Short: " + parameter;
		else if (Integer.class.isInstance(parameter))	return "Integer:" + parameter;
		else if (Long.class.isInstance(parameter))		return "Long:" + parameter;
		else if (Float.class.isInstance(parameter))		return "Float:" + parameter;
		else if (Double.class.isInstance(parameter))	return "Double:" + parameter;
		else if (Boolean.class.isInstance(parameter))	return "Boolean:" + parameter;
		else if (Character.class.isInstance(parameter))	return "Char:" + parameter;
		else if (String.class.isInstance(parameter))	return "String:" + parameter;
		else											return "Object:" + parameter;
	}
}
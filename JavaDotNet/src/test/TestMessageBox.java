package test;

import main.java.kd.jdn.JavaDotNet;

/**
 * This test show how to show simple .NET MessageBox
 * 
 * @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzynski</a> -> https://github.com/Sejoslaw
 */
public class TestMessageBox
{
	public static void main(String[] args)
	{
		// This will try to load .NET internal library.
		JavaDotNet.INSTANCE.loadDotNetInternalLibrary("System.Windows.Forms");
		// Add reference to required assembly
		JavaDotNet.INSTANCE.addReference("System.Windows.Forms");
		// Invoke Show method from MessageBox class
		JavaDotNet.INSTANCE
		.getType("System.Windows.Forms.MessageBox") // Get type with static methods
		.invoke("Show", new Object[] { ".NET can You hear Java ???" }); // Invoke static method with arguments
	}
}
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
		// Initialize JDN - this MUST be run as first. 
		// Not running this will not initialize .NET Bridge.
		JavaDotNet.Initialize();
		// Add reference to required assembly
		JavaDotNet.addReference("System.Windows.Forms");
		// Invoke Show method from MessageBox class
		JavaDotNet
		.getType("System.Windows.Forms.MessageBox") // Get type with static methods
		.invoke("Show", ".NET can You hear Java ???"); // Invoke static method with arguments
	}
}
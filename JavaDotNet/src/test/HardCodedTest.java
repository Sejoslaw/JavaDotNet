package test;

import main.java.kd.jdn.JavaDotNet;

/**
 * Hard coded test for testing JNI and if JDN can see JavaDotNet.NET.dll
 * 
 * @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzynski</a> -> https://github.com/Sejoslaw
 */
public class HardCodedTest 
{
	public static final String PATH = "C:/Users/Krzysztof/git/JavaDotNet/JavaDotNet.NET/x64/Debug/JavaDotNet.Bridge.dll";
	
	public static void main(String[] args)
	{
		// Hard coded loading library
		JavaDotNet.INSTANCE.loadLibrary(PATH);
		// It prints answer from .NET
		System.out.println(JavaDotNet.INSTANCE.communicateDotNet(".NET can You hear Java ???"));
	}
}
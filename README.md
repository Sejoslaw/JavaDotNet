<pre>
			..:: JavaDotNet Specification ::..

1) General info.
	JavaDotNet works similar to JavOnet but the code is written by me ( Krzysztof Dobrzyñski ).
	JDNObject is a wrapped .NET Object with unique identifier for each .NET Object.

	Conversion works like so:
	Java -> (run JDN command) -> [command_id, object_id, params] 
	-> run C++ and .NET Side magic -> send answer to Java
	command_id - unique identifier of an JDN command 
		(for instance: NEW_OBJECT means "create new .NET object"),
		all command ids are in JDNCommand.java
	object_id - unique identifier of an .NET object which should be used
	params - parameters passed to .NET side in a form of compressed String,
		conversion to String representation can be found in "Conversion" section below


2) Architecture
	Java <-> C++ <-> .NET / C#
	JavaDotNet.jar <-> JavaDotNet.dll
	JAR file is a bridge which should be added as a library to Java project.
	Use Java commands to connect with .NET JavaDotNet DLL library.
	JavaDotnet.NET.dll is a library which connects C++ and .NET environment.


3) Help
	3.1) You can generate header file using gen_header.bat
		It will be generated in JavaDotNet.NET folder.
		If "jni.h" is not visible. You can add it manually from:
			"C:/Program Files/Java/jdkVersion/include/jni.h"
	3.2) If (in my case) Visual Studio returns You an error with "jni.h"
		or other Java include header, change additional include directiories.
		(help -> https://msdn.microsoft.com/en-us/library/73f9s62w.aspx)


4) Conversion
	Java type <=> String representation
	byte x = 32; <=> "Byte:32"
	short x =32 <=> "Short:32"
	int x = 32; <=> "Int:32"
	long x = 32; <=> "Long:32"
	float x = 32.0; <=> "Float:32.0"
	double x = 32.0; <=> "Double:32.0"
	boolean x = true; <=> "Boolean:true"
	char x = 'N'; <=> "Char:N"
	String x = "MyAwesomeString"; <=> "String:MyAwesomeString"
	Object x = null; <=> "Object:null"
	
	If any other conversion should be added it must have the similar conversion.


5) Using
	First thing which You must do when writing Java program is to load
	JavaDotNet.NET.dll library. Either by using full path to file or by
	copying file into "C:/Windows/Microsoft.NET/Framework/v4.0.30319" and
	loading it by name "JavaDotNet.NET".
</pre>

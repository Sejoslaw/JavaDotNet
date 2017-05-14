			..:: JavaDotNet Specification ::..

1) General info.
JavaDotNet works similar to JavOnet but the code is written by me ( Krzysztof Dobrzyñski ).
JDNObject is a wrapped .NET Object with unique identifier for each .NET Object.
Conversion works like so:
Java -> (run JDN command) -> [command_id, object_id, params] -> run .NET Side magic -> send answer to Java
command_id - unique identifier of an JDN command (for instance: METHOD_STATIC which means "run static method"),
			 all ids are in JDNCommand.java
object_id - unique identifier of an object which should be used, 
			"new" as an id will force .NET side to create new object
params - parameters used for running JDN command, each JDN command can have different number of parameters


2) Architecture
JavaDotNet.jar <-> JavaDotNet.dll
JAR file is a bridge which should be added as a library to Java project.
Use Java command to connect with .NET JDN DLL.
DLL file is a wrapped .NET side.


3) Using

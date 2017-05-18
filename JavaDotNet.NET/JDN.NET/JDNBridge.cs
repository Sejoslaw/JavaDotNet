using System;
using System.Reflection;

namespace JavaDotNet
{
    /// <summary>
    ///
    /// This is a root class of the JavaDotNet .NET side.
    /// It reads the JDN command and executes it.
    /// 
    /// @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyński</a> -> https://github.com/Sejoslaw
    /// 
    /// </summary>
    public class JDNBridge
    {
        private JDNBridge() { }

        /// <summary>
        /// Main folder which contains all basic .NET libraries.
        /// </summary>
        public static string DOTNET_DIR = "C:/Windows/Microsoft.NET/Framework/v4.0.30319";
        /// <summary>
        /// Extension of the DLL file.
        /// </summary>
        public static string DLL_EXT = ".dll";
        /// <summary>
        /// Space between each JDN command parameter.
        /// </summary>
        public static string COMMAND_SPACE = ";!;!;";

        /// <summary>
        /// Main method which gets JDN command passed through C++, executes it and returns the answer for Java.
        /// </summary>
        /// <param name="jdnCommand"> JavaDotNet command passed to .NET </param>
        /// <returns> Returns builded answer for Java </returns>
        public static string readJDNCommand(string jdnCommand)
        {
            /// Splited arguments
            string[] commandArgs = jdnCommand.Split(COMMAND_SPACE.ToCharArray());
            /// Unique identified of the JDN command
            int commandId = Convert.ToInt32(commandArgs[0]);
            /// Run command connected with unique identifier
            if (commandId == JDNCommand.ADD_NEW_REFERENCE) return JDNCommand.CommandAddNewReference(commandArgs);
            else if (commandId == JDNCommand.NEW_OBJECT) return JDNCommand.CommandNewObject(commandArgs);
            else if (commandId == JDNCommand.GET_TYPE) return JDNCommand.CommandGetType(commandArgs);
            else if (commandId == JDNCommand.INVOKE) return JDNCommand.CommandInvoke(commandArgs);
            else if (commandId == JDNCommand.GET_FIELD_VALUE) return JDNCommand.CommandGetFieldValue(commandArgs);
            else if (commandId == JDNCommand.SET_FIELD_VALUE) return JDNCommand.CommandSetFieldValue(commandArgs);
            else return "";
        }

        /// <summary>
        /// Adds and returns new assembly.
        /// </summary>
        /// <param name="assemblyNameOrPath"> Name of the assembly or path from which the new assembly should be added. </param>
        /// <returns> Returns the newly added assembly </returns>
        public static JDNAssembly reference(string assemblyNameOrPath)
        {
            return new JDNAssembly(assemblyNameOrPath);
        }

        /// <summary>
        /// Creates and returns a new object from the given name of the type.
        /// </summary>
        /// <param name="typeName"> Type of the new object. </param>
        /// <returns> Returns the newly created object. </returns>
        public static JDNObject create(string typeName)
        {
            return new JDNObject(typeName);
        }

        /// <summary>
        /// Creates and returns a new object from the given name of the type AND given parameters.
        /// </summary>
        /// <param name="typeName"> Type of the new object. </param>
        /// <param name="parameters"> New object constructor parameters. </param>
        /// <returns> Returns the newly created object. </returns>
        public static JDNObject create(string typeName, object[] parameters)
        {
            return new JDNObject(typeName, parameters);
        }

        /// <summary>
        /// Creates and returns a new object from the given name of the type.
        /// </summary>
        /// <param name="typeName"> Type of the new object. </param>
        /// <returns> Returns the newly created object. </returns>
        public static JDNObject New(string typeName)
        {
            return create(typeName);
        }

        /// <summary>
        /// Creates and returns a new object from the given name of the type AND given parameters.
        /// </summary>
        /// <param name="typeName"> Type of the new object. </param>
        /// <param name="parameters"> New object constructor parameters. </param>
        /// <returns> Returns the newly created object. </returns>
        public static JDNObject New(string typeName, object[] parameters)
        {
            return create(typeName, parameters);
        }

        /// <summary>
        /// Returns the type from the given name.
        /// </summary>
        /// <param name="typeName"> Name of the type. </param>
        /// <returns> Returns the type if exists. </returns>
        public static JDNType getType(string typeName)
        {
            return new JDNType(typeName);
        }

        /// <summary>
        /// </summary>
        /// <param name="typeName"></param>
        /// <param name="genericTypes"></param>
        /// <returns></returns>
        public static JDNType getType(string typeName, object[] genericTypes)
        {
            // TODO
            return null;
        }

        /// <summary>
        /// </summary>
        /// <returns> Returns all loaded assemblies. </returns>
        public static JDNAssembly[] getLoadedAssemblies()
        {
            Assembly[] loaded = AppDomain.CurrentDomain.GetAssemblies();
            JDNAssembly[] jdnAssemblies = new JDNAssembly[loaded.Length];
            for (int i = 0; i < loaded.Length; ++i)
            {
                jdnAssemblies[i] = new JDNAssembly(loaded[i].ToString());
            }
            return jdnAssemblies;
        }
    }
}
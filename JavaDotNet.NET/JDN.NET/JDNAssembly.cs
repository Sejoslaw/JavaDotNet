using System;
using System.IO;
using System.Reflection;

namespace JavaDotNet
{
    /// <summary>
    /// 
    /// This is a single Assembly wrapper.
    /// 
    /// @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyński</a> -> https://github.com/Sejoslaw
    /// 
    /// </summary>
    public class JDNAssembly
    {
        /// <summary>
        /// Name or path of this assembly.
        /// </summary>
        private string _assemblyNameOrPath;
        /// <summary>
        /// Assembly itself.
        /// </summary>
        private Assembly _assembly;
        private string assemblyNameOrPath;

        public JDNAssembly(string assemblyNameOrPath)
        {
            this._assemblyNameOrPath = assemblyNameOrPath;
            this._assembly = loadAssembly();
        }

        /// <summary>
        /// Load a single new assembly.
        /// </summary>
        /// <param name="assemblyNameOrPath"> Assembly name or path to assembly. </param>
        /// <returns> Returns the newly loadded assembly. </returns>
        private Assembly loadAssembly()
        {
            // If the given name ends with .dll we assume that the user give full path to assembly.
            if (this._assemblyNameOrPath.EndsWith(".dll")) return Assembly.LoadFrom(this._assemblyNameOrPath);
            // Otherwise we must check .NET installed assemblies
            // Main directory
            DirectoryInfo dir = new DirectoryInfo(JDNBridge.DOTNET_DIR);
            // Search through files
            foreach (FileInfo file in dir.GetFiles())
            {
                if (file.Extension.Equals(JDNBridge.DLL_EXT)) // If it is a .dll file
                    if (file.Name.Equals(this._assemblyNameOrPath)) // If the name is correct
                        // For instance:   C:/Windows/Microsoft.NET/Framework/v4.0.30319 / System.Windows.Forms .dll
                        return Assembly.LoadFrom(JDNBridge.DOTNET_DIR + "/" + file.FullName + JDNBridge.DLL_EXT);
            }
            return null;
        }

        //============================= GETTERS ===================================

        /// <summary>
        /// </summary>
        /// <returns> Returns the name of this assembly or the path to this assembly. </returns>
        public string getAssemblyNameOrPath()
        {
            return this._assemblyNameOrPath;
        }

        //============================= END GETTERS ===============================

        /// <summary>
        /// </summary>
        /// <param name="typeName"> Name of the type from this assembly. </param>
        /// <returns> Returns the type from this assembly by the given name of the type. </returns>
        public JDNType getType(string typeName)
        {
            return new JDNType(typeName);
        }

        /// <summary>
        /// Creates a new object of the given Type.
        /// </summary>
        /// <param name="typeName"> Name of the type from this assembly. </param>
        /// <returns> Returns new object. </returns>
        public JDNObject create(string typeName)
        {
            JDNType type = getType(typeName);
            return type.create();
        }

        /// <summary>
        /// Creates a new object of the given Type and parameters.
        /// </summary>
        /// <param name="typeName"> Name of the type from this assembly. </param>
        /// <param name="parameters"> Parameters used in constructor. </param>
        /// <returns> Returns new object. </returns>
        public JDNObject create(string typeName, object[] parameters)
        {
            JDNType type = getType(typeName);
            return type.create(parameters);
        }
    }
}
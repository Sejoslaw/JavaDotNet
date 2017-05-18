using System;
using System.Reflection;

namespace JavaDotNet
{
    /// <summary>
    /// 
    /// Wrapper for basic Type.
    /// 
    /// @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyński</a> -> https://github.com/Sejoslaw
    /// 
    /// </summary>
    public class JDNType
    {
        /// <summary>
        /// Type name. It can be either just name of the class or full name of the namespace and class.
        /// 
        /// For instance it could be either:
        ///     1) MessageBox
        ///     2) System.Windows.Forms.MessageBox
        ///     3) System.Windows.Forms.MessageBox, System.Windows.Forms, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089
        /// </summary>
        private string _typeName;
        /// <summary>
        /// Wrapped type.
        /// </summary>
        private Type _type;

        public JDNType(string typeName)
        {
            this._typeName = typeName;
            this._type = loadType();
        }

        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
        private Type loadType()
        {
            // TODO
            // Find a way to convert any version of type name (see this._typeName) into right Type.
            return Type.GetType(this._typeName);
        }

        /// <summary>
        /// </summary>
        /// <returns> Returns the name of this Type. </returns>
        public string getTypeName()
        {
            return this._type.Name;
        }

        /// <summary>
        /// Creates new object of this type.
        /// </summary>
        /// <returns> Returns the newly created object. </returns>
        public JDNObject create()
        {
            return new JDNObject(getTypeName());
        }

        /// <summary>
        /// Creates new object of this type.
        /// </summary>
        /// <param name="parameters"> Parameters for the object constructor. </param>
        /// <returns> Returns the newly created object. </returns>
        public JDNObject create(object[] parameters)
        {
            return new JDNObject(getTypeName(), parameters);
        }

        /// <summary>
        /// Invokes a static method from this Type.
        /// </summary>
        /// <param name="methodName"> Name of the method to invoke. </param>
        /// <returns> Returns the value of the invoked static method. </returns>
        public object invoke(string methodName)
        {
            return invoke(methodName, null);
        }

        /// <summary>
        /// Invokes a static method from this Type.
        /// </summary>
        /// <param name="methodName"> Name of the method to invoke. </param>
        /// <param name="parameters"> Parameters to invoke method. </param>
        /// <returns> Returns the value of the invoked static method. </returns>
        public object invoke(string methodName, object[] parameters)
        {
            // Get method info
            MethodInfo methodInfo = this._type.GetMethod(methodName, JDNHelper.getTypeArray(parameters));
            // Invoke static method
            return methodInfo.Invoke(null, parameters);
        }

        // TODO
        // How to invoke generic method on the wrapped object using reflection ???
        // Similar to JDNObject.invokeGeneric(...)
        //public object invokeGeneric(...)

        /// <summary>
        /// Returns the static value of the field from the specified field name. It can be anything from "int" to "Button".
        /// </summary>
        /// <param name="fieldName"> Name of the field from which we want static value. </param>
        /// <returns> Returns the static value of the field, NULL if the field with specified name does not exists. </returns>
        public object get(string fieldName)
        {
            // Get the field for the given name.
            FieldInfo field = this._type.GetField(fieldName);
            // If field is wrong return null. (misspelled or something)
            if (field == null) return null;
            // If field name exists get the value of it.
            return field.GetValue(null);
        }

        /// <summary>
        /// Sets the new static value to the field.
        /// </summary>
        /// <param name="fieldName"> Name of the static field for which we want to sets new value. </param>
        /// <param name="newValue"> New value which should be set to the specified static field. </param>
        /// <returns> Returns this object for the static sets chain. </returns>
        public object set(string fieldName, object newValue)
        {
            // Get field by it's name.
            FieldInfo field = this._type.GetField(fieldName);
            // if field does not exist return this object.
            if (field == null) return null;
            // If it exists set the value of it.
            field.SetValue(null, newValue);
            // Return this object.
            return this;
        }
    }
}
using System;
using System.Reflection;

namespace JavaDotNet
{
    /// <summary>
    /// 
    /// Think about it like a wrapper for a single object.
    /// This is a root object of the JavaDotNet.
    /// It can represent any .NET object in Java.
    /// 
    /// @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyński</a> -> https://github.com/Sejoslaw
    /// 
    /// </summary>
    public class JDNObject
    {
        /// <summary>
        /// Given type name
        /// </summary>
        private string _typeName;
        /// <summary>
        /// Wanted type
        /// </summary>
        private Type _type;
        /// <summary>
        /// This is the object which is wrapped by this class
        /// </summary>
        private object _objectInstance;
        /// <summary>
        /// Parameters of the constructor of the wrapped object
        /// </summary>
        private object[] _constructorParameters;

        public JDNObject(string typeName)
        {
            this._typeName = typeName;
            this._type = Type.GetType(this._typeName);
            this._constructorParameters = new object[] { }; // Create an empty array if there are no parameters
            this._objectInstance = Activator.CreateInstance(this._type);
        }

        public JDNObject(string typeName, object[] constructorParameters)
        {
            this._typeName = typeName;
            this._type = Type.GetType(this._typeName);
            this._constructorParameters = constructorParameters;
            this._objectInstance = Activator.CreateInstance(this._type, this._constructorParameters);
        }

        //============================ GETTERS =======================================

        /// <summary>
        /// </summary>
        /// <returns> Returns the name of the type of the wrapped object. </returns>
        public string getTypeName()
        {
            return this._typeName;
        }

        /// <summary>
        /// </summary>
        /// <returns> Returns the type of the wrapped object. </returns>
        public JDNType getType()
        {
            return new JDNType(this._typeName);
        }

        /// <summary>
        /// If the object was created using empty constructor this should return empty array.
        /// </summary>
        /// <returns> Returns the parameters which were used for creating object. </returns>
        public object[] getConstructorParameters()
        {
            return this._constructorParameters;
        }

        //============================ END GETTERS ===================================

        /// <summary>
        /// Returns the value of the field from the specified field name. It can be anything from "int" to "Button".
        /// </summary>
        /// <param name="fieldName"> Name of the field from which we want value. </param>
        /// <returns> Returns the value of the field, NULL if the field with specified name does not exists. </returns>
        public object get(string fieldName)
        {
            // Get the field for the given name.
            FieldInfo field = this._type.GetField(fieldName);
            // If field is wrong return null. (misspelled or something)
            if (field == null) return null;
            // If field name exists get the value of it.
            return field.GetValue(this._objectInstance);
        }

        /// <summary>
        /// Sets the new value to the field.
        /// </summary>
        /// <param name="fieldName"> Name of the field for which we want to sets new value. </param>
        /// <param name="newValue"> New value which should be set to the specified field. </param>
        /// <returns> Returns this object for the sets chain. </returns>
        public JDNObject set(string fieldName, object newValue)
        {
            // Get field by it's name.
            FieldInfo field = this._type.GetField(fieldName);
            // if field does not exist return this object.
            if (field == null) return this;
            // If it exists set the value of it.
            field.SetValue(this._objectInstance, newValue);
            // Return this object.
            return this;
        }

        /// <summary>
        /// Invokes method by it's specified name.
        /// </summary>
        /// <param name="methodName"> Name of the method to invoke. </param>
        /// <returns> Returns the result of the method, NULL if wrong method name was specified. </returns>
        public object invoke(string methodName)
        {
            return invoke(methodName, null);
        }

        /// <summary>
        /// Invokes method by it's specified name and parameters.
        /// </summary>
        /// <param name="methodName"> Name of the method to invoke. </param>
        /// <param name="parameters"> Parameters of the method. </param>
        /// <returns> Returns the result of the method, NULL if wrong method name or any parameter was specifid. </returns>
        public object invoke(string methodName, object[] parameters)
        {
            // Get method info
            MethodInfo method = this._type.GetMethod(methodName);
            // Return null if method does not exists.
            if (method == null) return null;
            // Now we know that the method exists
            return method.Invoke(this._objectInstance, parameters);
        }

        //// TODO
        //// How to invoke generic method on the wrapped object using reflection ???
        //// public object invokeGeneric(...)

        ///// <summary>
        ///// Creates an delegate which will connect given Event and run the eventOccured method from the given listener.
        ///// </summary>
        ///// <param name="eventInfo"></param>
        ///// <param name="eventListener"></param>
        ///// <returns></returns>
        //private Delegate constructDelegate(EventInfo eventInfo, JDNEventListener eventListener)
        //{
        //    return Delegate.CreateDelegate(
        //                            eventInfo.EventHandlerType,
        //                            eventListener,
        //                            eventListener.GetType().GetMethod("eventOccurred"));
        //}

        ///// <summary>
        ///// Adds listener for the specified event.
        ///// </summary>
        ///// <param name="eventName"> Name of the event for which listener should be added. </param>
        ///// <param name="eventListener"> Listener which should be added to the event. </param>
        //public void addEventListener(string eventName, JDNEventListener eventListener)
        //{
        //    // Event for which we want to add a listener
        //    EventInfo eventInfo = this._type.GetEvent(eventName);
        //    // If there is no Event with the specified name, return;
        //    if (eventInfo == null) return;
        //    // Create a delegate for the Event which will use the given listener.
        //    Delegate handler = constructDelegate(eventInfo, eventListener);
        //    // Add listener (delegate) to the specified event of the wrapped object
        //    eventInfo.AddEventHandler(this._objectInstance, handler);
        //}

        ///// <summary>
        ///// Removes the listener from the Event by it's name.
        ///// </summary>
        ///// <param name="eventName"> Name of the Event from which to remove listener. </param>
        //public void removeEventListener(string eventName, JDNEventListener eventListener)
        //{
        //    // Event from which we want to remove a listener
        //    EventInfo eventInfo = this._type.GetEvent(eventName);
        //    // If there is no Event with the specified name, return;
        //    if (eventInfo == null) return;
        //    // Create a delegate for the Event which will use the given listener.
        //    Delegate handler = constructDelegate(eventInfo, eventListener);
        //    // Remove listener (handler) for this event from the given wrapped object.
        //    eventInfo.RemoveEventHandler(this._objectInstance, handler);
        //}

        ///// <summary>
        ///// Raises the specified event.
        ///// </summary>
        ///// <param name="eventName"> Name of the Event which should raise. </param>
        ///// <param name="arguments"> Arguments of the raised events. </param>
        //public object raiseEvent(string eventName, object[] arguments)
        //{
        //    // Event which we want to raise
        //    EventInfo eventInfo = this._type.GetEvent(eventName);
        //    // If there is no Event with the specified name, return null.
        //    if (eventInfo == null) return null;
        //    // Invoke the raise method.
        //    return eventInfo.GetRaiseMethod().Invoke(this._objectInstance, arguments);
        //}
    }
}
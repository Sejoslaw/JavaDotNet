using System;

namespace JavaDotNet
{
    /// <summary>
    /// 
    /// Some additional methods.
    /// 
    /// @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyński</a> -> https://github.com/Sejoslaw
    /// 
    /// </summary>
    public class JDNHelper
    {
        private JDNHelper() { }

        /// <summary>
        /// Convert object[] to Type[]. Used mainly to convert constructor arguments.
        /// </summary>
        /// <param name="parameters"></param>
        /// <returns></returns>
        public static Type[] getTypeArray(object[] parameters)
        {
            Type[] typeArray = new Type[parameters.Length];
            for (int i = 0; i < parameters.Length; ++i)
                typeArray[i] = parameters[i].GetType();
            return typeArray;
        }
    }
}
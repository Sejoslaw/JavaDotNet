using System;

namespace JavaDotNet
{
    /// <summary>
    ///
    /// This class contains all JDN command values declared in JDNCommand.java
    /// For any change or information look inside JDNCommand.java
    /// 
    /// @author <a href="mailto:k.dobrzynski94@gmail.com">Krzysztof Dobrzyński</a> -> https://github.com/Sejoslaw
    /// 
    /// </summary>
    public class JDNCommand
    {
        public static readonly int ADD_NEW_REFERENCE = 1;
        public static readonly int NEW_OBJECT = 2;
        public static readonly int GET_TYPE = 3;
        public static readonly int INVOKE = 4;
        public static readonly int GET_FIELD_VALUE = 5;
        public static readonly int SET_FIELD_VALUE = 6;
    }
}

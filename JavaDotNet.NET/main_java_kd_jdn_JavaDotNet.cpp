/**
        Created by: Krzysztof Dobrzynski - k.dobrzynski94@gmail.com
                https://github.com/Sejoslaw
        This file is an implementation of the header.
        This file contains all the logic required for JavaDotNet bridge to work.
*/

/// JNI header
#include <jni.h>
/// JDN generated header file
#include "main_java_kd_jdn_JavaDotNet.h"

/// JDNCommands - look in JDNCommand.java for more info
#define COMMAND_ADD_NEW_REFERENCE 1
#define COMMAND_NEW_OBJECT 2
#define COMMAND_GET_TYPE 3
#define COMMAND_INVOKE 4
#define COMMAND_GET_FIELD_VALUE 5
#define COMMAND_SET_FIELD_VALUE 6

/// Represents a space between JDN arguments - see JDNConversion.java
#define ARGS_SPACE ";!;!;"
/// Represents a static folder on Windows with all .NET libraries.
#define DOT_NET_DIR "C:/Windows/Microsoft.NET/Framework/v4.0.30319/"
/// Defines the DLL file extension
#define DLL_EXTENSION ".dll"

/// Main method for JavaDotNet bridge to communicate with .NET
JNIEXPORT jstring JNICALL Java_main_java_kd_jdn_JavaDotNet_communicateDotNet
    (JNIEnv *env, jobject object, jstring args)
{
    /// string representation of the full JDN command
    const char* string_rep = env->GetStringUTFChars(args, NULL);
}

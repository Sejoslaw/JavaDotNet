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

///Few internal variables

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
}

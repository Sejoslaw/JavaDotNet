/**
        Created by: Krzysztof Dobrzynski - k.dobrzynski94@gmail.com
                https://github.com/Sejoslaw
        This file is an implementation of the header.
        This file contains all the logic required for JavaDotNet bridge to work 
		and communicate with .NET
		Main idea behind this bridge was for it to be a single C++ file which contains
		ALL what is needed.
*/

#pragma once

/// ========================================================= Includes

/// Visual Studio header file
#include "stdafx.h"
/// JNI header
#include "jni.h"
/// JDN generated header file
#include "main_java_kd_jdn_JavaDotNet.h"

/// ========================================================= .NET Specific

/// .NET Includes and usings
//#using "mscorlib.dll" // Optional using if needed

/// Main .NET library and namespace.
using namespace System;
/// Namespace used for converting:  System::String -> const char*
using namespace System::Runtime::InteropServices;

/// ========================================================= JavaDotNet functionality / methods

/// Main method for JavaDotNet bridge to communicate with .NET
JNIEXPORT jstring JNICALL Java_main_java_kd_jdn_JavaDotNet_communicateDotNet
    (JNIEnv *env, jobject object, jstring args)
{
    /// String representation of the full JDN command
    const char* jdn_command_string_rep = env->GetStringUTFChars(args, NULL);

	/// Build .NET String from representation
	String^ dot_net_jdn_command = gcnew String(jdn_command_string_rep);

	/// Here is where the connection to .NET happens
	/// .NET reads JDN command and prepares answer
	String^ dot_net_answer = JavaDotNet::JDNBridge::readJDNCommand(dot_net_jdn_command);

	/// .NET answer is converted to basic C++ const char*
	/// .NET answer string representation
	const char* dot_net_answer_string_rep = (const char*) (Marshal::StringToHGlobalAnsi(dot_net_answer)).ToPointer();

	/// Convert .NET answer to Java string
	jstring to_java_answer = env->NewStringUTF(dot_net_answer_string_rep);

	/// Return .NET answer to Java
	return to_java_answer;
}
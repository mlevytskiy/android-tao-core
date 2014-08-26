/*******************************************************************************
 * Copyright (c) 2010 Alexandr Tsvetkov.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * Contributors:
 *     Alexandr Tsvetkov - initial API and implementation
 *
 * Project:
 *     TAO Util
 *
 * License agreement:
 *
 * 1. This code is published AS IS. Author is not responsible for any damage that can be
 *    caused by any application that uses this code.
 * 2. Author does not give a garantee, that this code is error free.
 * 3. This code can be used in NON-COMMERCIAL applications AS IS without any special
 *    permission from author.
 * 4. This code can be modified without any special permission from author IF AND ONLY IF
 *    this license agreement will remain unchanged.
 ******************************************************************************/
package ua.at.tsvetkov.util;

import android.text.TextUtils;

/**
 * Extended logger. Allows you to automatically adequately logged class, method and line call in the log. Makes it easy to write logs. For
 * example Log.v("Test") will in the log some the record: 04-04 08:29:40.336: V > SomeClass: someMethod: 286 Test
 * 
 * @author A.Tsvetkov 2010 http://tsvetkov.at.ua mailto:al@ukr.net
 */
public final class Log {

	// ==========================================================

	private static final String	COLON						= ":";
	private static final String	POSFIX_STRING			= ")";
	private static final String	PREFIX_STRING			= "> (";
	private static final String	PREFIX_MAIN_STRING	= " > ";
	private static final String	STRING_MORE				= "> ";
	private static final String	GROUP						= "|Group:";
	private static final String	PRIORITY					= "|priority:";
	private static final String	ID							= "|id:";
	private static final String	NAME						= "Name:";
	private static final String	THREAD					= "< Thread";

	private static boolean			isDisabled				= false;

	public static boolean isDisabled() {
		return isDisabled;
	}

	public static void setDisabled(boolean isDisabled) {
		Log.isDisabled = isDisabled;
	}

	/**
	 * Send a VERBOSE log message.
	 * 
	 * @param msg The message you would like logged.
	 */
	public static void v(String msg) {
		if (isDisabled)
			return;
		android.util.Log.v(getLocation(), msg);
	}

	/**
	 * Send a DEBUG log message.
	 * 
	 * @param msg The message you would like logged.
	 */
	public static void d(String msg) {
		if (isDisabled)
			return;
		android.util.Log.d(getLocation(), msg);
	}

	/**
	 * Send a INFO log message.
	 * 
	 * @param msg The message you would like logged.
	 */
	public static void i(String msg) {
		if (isDisabled)
			return;
		android.util.Log.i(getLocation(), msg);
	}

	/**
	 * Send a WARN log message.
	 * 
	 * @param msg The message you would like logged.
	 */
	public static void w(String msg) {
		if (isDisabled)
			return;
		android.util.Log.w(getLocation(), msg);
	}

	/**
	 * Send a ERROR log message.
	 * 
	 * @param msg The message you would like logged.
	 */
	public static void e(String msg) {
		if (isDisabled)
			return;
		android.util.Log.e(getLocation(), msg);
	}

	/**
	 * What a Terrible Failure: Report a condition that should never happen. The error will always be logged at level ASSERT with the call
	 * stack. Depending on system configuration, a report may be added to the DropBoxManager and/or the process may be terminated immediately
	 * with an error dialog.
	 * 
	 * @param msg The message you would like logged.
	 */
	public static void wtf(String msg) {
		if (isDisabled)
			return;
		android.util.Log.wtf(getLocation(), msg);
	}

	// ==========================================================

	/**
	 * Send a VERBOSE log message and log the exception.
	 * 
	 * @param msg The message you would like logged.
	 * @param tr An exception to log
	 */
	public static void v(String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.v(getLocation(), msg, tr);
	}

	/**
	 * Send a DEBUG log message and log the exception.
	 * 
	 * @param msg The message you would like logged.
	 * @param tr An exception to log
	 */
	public static void d(String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.d(getLocation(), msg, tr);
	}

	/**
	 * Send a INFO log message and log the exception.
	 * 
	 * @param msg The message you would like logged.
	 * @param tr An exception to log
	 */
	public static void i(String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.i(getLocation(), msg, tr);
	}

	/**
	 * Send a WARN log message and log the exception.
	 * 
	 * @param msg The message you would like logged.
	 * @param tr An exception to log
	 */
	public static void w(String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.w(getLocation(), msg, tr);
	}

	/**
	 * Send a ERROR log message and log the exception.
	 * 
	 * @param msg The message you would like logged.
	 * @param tr An exception to log
	 */
	public static void e(String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.e(getLocation(), msg, tr);
	}

	/**
	 * What a Terrible Failure: Report an exception that should never happen. Similar to wtf(String, Throwable), with a message as well.
	 * 
	 * @param msg The message you would like logged.
	 * @param tr An exception to log
	 */
	public static void wtf(String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.wtf(getLocation(), msg, tr);
	}

	// ==========================================================

	/**
	 * Send a VERBOSE log the exception.
	 * 
	 * @param tr An exception to log
	 */
	public static void v(Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.v(getLocation(), "", tr);
	}

	/**
	 * Send a DEBUG log the exception.
	 * 
	 * @param tr An exception to log
	 */
	public static void d(Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.d(getLocation(), "", tr);
	}

	/**
	 * Send a INFO log the exception.
	 * 
	 * @param tr An exception to log
	 */
	public static void i(Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.i(getLocation(), "", tr);
	}

	/**
	 * Send a WARN log the exception.
	 * 
	 * @param tr An exception to log
	 */
	public static void w(Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.w(getLocation(), "", tr);
	}

	/**
	 * Send a ERROR log the exception.
	 * 
	 * @param tr An exception to log
	 */
	public static void e(Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.e(getLocation(), "", tr);
	}

	/**
	 * What a Terrible Failure: Report an exception that should never happen. Similar to wtf(String, Throwable), with a message as well.
	 * 
	 * @param tr An exception to log
	 */
	public static void wtf(Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.wtf(getLocation(), "", tr);
	}

	// ==========================================================

	/**
	 * Send a <b>VERBOSE</b> log message. Using when you extend any Class and wont to receive full info in LogCat tag. Usually you can use
	 * "this" in "obj" parameter. As result you receive tag string
	 * "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumberClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param msg The message you would like logged.
	 */
	public static void v(Object obj, String msg) {
		if (isDisabled)
			return;
		android.util.Log.v(gatExtendedTag(obj), msg);
	}

	/**
	 * Send a <b>DEBUG</b> log message. Using when you extend any Class and wont to receive full info in LogCat tag. Usually you can use
	 * "this" in "obj" parameter. As result you receive tag string "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param msg The message you would like logged.
	 */
	public static void d(Object obj, String msg) {
		if (isDisabled)
			return;
		android.util.Log.d(gatExtendedTag(obj), msg);
	}

	/**
	 * Send a <b>INFO</b> log message. Using when you extend any Class and wont to receive full info in LogCat tag. Usually you can use
	 * "this" in "obj" parameter. As result you receive tag string "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param msg The message you would like logged.
	 */
	public static void i(Object obj, String msg) {
		if (isDisabled)
			return;
		android.util.Log.i(gatExtendedTag(obj), msg);
	}

	/**
	 * Send a <b>WARN</b> log message. Using when you extend any Class and wont to receive full info in LogCat tag. Usually you can use
	 * "this" in "obj" parameter. As result you receive tag string "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param msg The message you would like logged.
	 */
	public static void w(Object obj, String msg) {
		if (isDisabled)
			return;
		android.util.Log.w(gatExtendedTag(obj), msg);
	}

	// /**
	// * Send a <b>ERROR</b> log message. Using when you extend any Class and wont to receive full info in LogCat tag. Usually you can use
	// "this" in "obj" parameter. As result you receive tag string
	// * "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	// *
	// * @param obj main class
	// * @param msg The message you would like logged.
	// */
	// public static void e(Object obj, String msg) {
	// android.util.Log.e(gatExtendedTag(obj), msg);
	// }

	/**
	 * Send a <b>What a Terrible Failure: Report a condition that should never happen</b> log message. Using when you extend any Class and
	 * wont to receive full info in LogCat tag. Usually you can use "this" in "obj" parameter. As result you receive tag string
	 * "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param msg The message you would like logged.
	 */
	public static void wtf(Object obj, String msg) {
		if (isDisabled)
			return;
		android.util.Log.wtf(gatExtendedTag(obj), msg);
	}

	// ==========================================================

	/**
	 * Send a <b>VERBOSE</b> log message and log the exception. Using when you extend any Class and wont to receive full info in LogCat tag.
	 * Usually you can use "this" in "obj" parameter. As result you receive tag string
	 * "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param msg The message you would like logged.
	 * @param tr An exception to log
	 */
	public static void v(Object obj, String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.v(gatExtendedTag(obj), msg, tr);
	}

	/**
	 * Send a <b>DEBUG</b> log message and log the exception. Using when you extend any Class and wont to receive full info in LogCat tag.
	 * Usually you can use "this" in "obj" parameter. As result you receive tag string
	 * "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param msg The message you would like logged.
	 * @param tr An exception to log
	 */
	public static void d(Object obj, String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.d(gatExtendedTag(obj), msg, tr);
	}

	/**
	 * Send a <b>INFO</b> log message and log the exception. Using when you extend any Class and wont to receive full info in LogCat tag.
	 * Usually you can use "this" in "obj" parameter. As result you receive tag string
	 * "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param msg The message you would like logged.
	 * @param tr An exception to log
	 */
	public static void i(Object obj, String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.i(gatExtendedTag(obj), msg, tr);
	}

	/**
	 * Send a <b>WARN</b> log message and log the exception. Using when you extend any Class and wont to receive full info in LogCat tag.
	 * Usually you can use "this" in "obj" parameter. As result you receive tag string
	 * "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param msg The message you would like logged.
	 * @param tr An exception to log
	 */
	public static void w(Object obj, String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.w(gatExtendedTag(obj), msg, tr);
	}

	/**
	 * Send a <b>ERROR</b> log message and log the exception. Using when you extend any Class and wont to receive full info in LogCat tag.
	 * Usually you can use "this" in "obj" parameter. As result you receive tag string
	 * "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param tr An exception to log
	 * @param msg The message you would like logged.
	 */
	public static void e(Object obj, String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.e(gatExtendedTag(obj), msg, tr);
	}

	/**
	 * Send a <b>What a Terrible Failure: Report a condition that should never happen</b> log message and log the exception. Using when you
	 * extend any Class and wont to receive full info in LogCat tag. Usually you can use "this" in "obj" parameter. As result you receive tag
	 * string "<b>(Called Main Class) LogginClass:MethodInLogginClass:lineNumber</b>"
	 * 
	 * @param obj main class
	 * @param tr An exception to log
	 * @param msg The message you would like logged.
	 */
	public static void wtf(Object obj, String msg, Exception tr) {
		if (isDisabled)
			return;
		android.util.Log.wtf(gatExtendedTag(obj), msg, tr);
	}

	/**
	 * Logging the current Thread info
	 */
	public static void threadInfo() {
		if (isDisabled)
			return;
		Thread thread = Thread.currentThread();
		long id = thread.getId();
		String name = thread.getName();
		long priority = thread.getPriority();
		String groupName = thread.getThreadGroup().getName();
		StringBuilder sb = new StringBuilder();
		sb.append(NAME);
		sb.append(name);
		sb.append(ID);
		sb.append(id);
		sb.append(PRIORITY);
		sb.append(priority);
		sb.append(GROUP);
		sb.append(groupName);
		android.util.Log.v(THREAD + getLocation(), sb.toString());
		// android.util.Log.v(THREAD + getLocation(), NAME + name + ID + id + PRIORITY + priority + GROUP + groupName);
	}

	/**
	 * Logging the current Thread info and a message
	 */
	public static void threadInfo(String msg) {
		if (isDisabled)
			return;
		Thread thread = Thread.currentThread();
		long id = thread.getId();
		String name = thread.getName();
		long priority = thread.getPriority();
		String groupName = thread.getThreadGroup().getName();
		StringBuilder sb = new StringBuilder();
		sb.append(NAME);
		sb.append(name);
		sb.append(ID);
		sb.append(id);
		sb.append(PRIORITY);
		sb.append(priority);
		sb.append(GROUP);
		sb.append(groupName);
		sb.append(STRING_MORE);
		sb.append(msg);
		android.util.Log.v(THREAD + getLocation(), sb.toString());
		// android.util.Log.v(THREAD + getLocation(), NAME + name + ID + id + PRIORITY + priority + GROUP + groupName + STRING_MORE + msg);
	}

	private static String getLocation() {
		final String className = Log.class.getName();
		final StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		boolean found = false;
		for (int i = 0; i < traces.length; i++) {
			StackTraceElement trace = traces[i];

			try {
				if (found) {
					if (!trace.getClassName().startsWith(className)) {
						Class<?> clazz = Class.forName(trace.getClassName());
						StringBuilder sb = new StringBuilder();
						sb.append(PREFIX_MAIN_STRING);
						sb.append(getClassName(clazz));
						sb.append(COLON);
						sb.append(trace.getMethodName());
						sb.append(COLON);
						sb.append(trace.getLineNumber());
						return sb.toString();
						// return PREFIX_MAIN_STRING + getClassName(clazz) + ":" + trace.getMethodName() + ":" + trace.getLineNumber();
					}
				} else if (trace.getClassName().startsWith(className)) {
					found = true;
					continue;
				}
			} catch (ClassNotFoundException e) {
			}
		}
		return "[]: ";
	}

	private static String gatExtendedTag(Object obj) {
		StringBuilder sb = new StringBuilder();
		sb.append(PREFIX_STRING);
		sb.append(obj.getClass().getSimpleName());
		sb.append(POSFIX_STRING);
		sb.append(getLocation());
		return sb.toString();
		// return PREFIX_STRING + obj.getClass().getSimpleName() + POSFIX_STRING + getLocation();
	}

	private static String getClassName(Class<?> clazz) {
		if (clazz != null) {
			if (!TextUtils.isEmpty(clazz.getSimpleName())) {
				return clazz.getSimpleName();
			}

			return getClassName(clazz.getEnclosingClass());
		}

		return "";
	}
}
package com.amazon.in_Utility;

import org.apache.log4j.Logger;

public class Log {
	
	private static Logger logger = Logger.getLogger(Log.class.getName());
	
	public static void info(String msg) {
		logger.info(msg);
	}
	
	public static void debug(String msg) {
		logger.debug(msg);
	}

}

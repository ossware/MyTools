package com.iunet.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Hello world!
 * 
 */
public class Log4jApp {

	private static Logger log = Logger.getLogger(Log4jApp.class);

	public static void main(String[] args) {
		System.out.println("测试 Log4j !");
		printLog();
	}

	private static void printLog() {
		BasicConfigurator.configure();

		PropertyConfigurator.configure("config/log4j.properties");

		DOMConfigurator.configure("");

		log.debug("log4j debug");

		log.info("log4j info");

		log.warn("log4j warn");

		log.error("log4j error");

		log.fatal("log4j fatal");
	}

}

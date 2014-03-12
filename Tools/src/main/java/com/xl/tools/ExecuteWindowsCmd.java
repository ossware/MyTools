package com.xl.tools;

import java.io.IOException;
import java.util.Calendar;

public class ExecuteWindowsCmd {

	// 设置关机时与分
	private static int shutdownHour = 10;
	private static int shutdownMinute = 35;

	public void shudownPC() {
		// 获取当前时和分
		int currentH = Calendar.HOUR_OF_DAY;
		int currentM = Calendar.MINUTE;
		if (shutdownHour == currentH && shutdownMinute == currentM) {
			try {
				Runtime.getRuntime().exec("shutdown -s -t 600");	// 以秒为单位
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	

}

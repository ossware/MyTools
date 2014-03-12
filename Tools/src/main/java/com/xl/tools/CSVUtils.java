package com.xl.tools;

import java.io.FileWriter;
import java.io.IOException;

public class CSVUtils {

	public static void main(String[] args) {
		try {
			FileWriter fw = new FileWriter("C:\\helloworld.csv");
			fw.write("aaa,bbb,hhh\r\naa1,bb1,hh1\r\naaa\r\naa2,bb2,hh2\r\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

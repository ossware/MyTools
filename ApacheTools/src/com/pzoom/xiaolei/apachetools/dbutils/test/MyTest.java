package com.pzoom.xiaolei.apachetools.dbutils.test;

import org.junit.Test;

public class MyTest {

	@Test
	public void testString() {
		String s = "编译和运行(都是在classloader目录下)中间夹几个字(这是第二个括号里的内容)";
		String[] ss = s.split("");
		for(int i = 0; i<= ss.length; i++) {
//			s.ss[i];
		}
		
		System.out.println(s);
		
	}
	
	
	
}

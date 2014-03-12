package com.iunet.ehcache.test;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import com.iunet.ehcache.EhcacheUtil;

public class Start implements Runnable {

	public void init() {
		Ehcache cache = EhcacheUtil.getCache("demoCache");
		for(int i = 1; i <= 10; i++) {
			Element element = new Element(i, "第 " + i + " 元素");
			cache.put(element);
		}
	}

	public void run() {
		init();
	}
	
	public static void main(String[] args) {
		Runnable start = new Start();
		start.run();
	}
}

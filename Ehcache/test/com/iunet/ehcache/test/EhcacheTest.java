package com.iunet.ehcache.test;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Query;

import org.junit.Before;
import org.junit.Test;

import com.iunet.ehcache.EhcacheUtil;

public class EhcacheTest {
	public static Ehcache cache = null;
	
	@Before
	public void init() {
		cache = EhcacheUtil.getCache("demoCache");
	}
	
	@Test
	public void putElement() {
		System.out.println("是否已经有元素了："  + cache.isKeyInCache(2));
		for(int i = 1; i <= 10; i++) {
			Element element = new Element(i, "第 " + i + " 元素");
			cache.put(element);
		}
	}
	
	@Test
	public void getElementData() {
		Element element = cache.get(2);
		System.out.println(element.toString());
	}
}

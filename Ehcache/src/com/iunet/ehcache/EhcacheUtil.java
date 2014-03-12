package com.iunet.ehcache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class EhcacheUtil {

	private static CacheManager cacheManager = null;

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

	static {
		try {
			cacheManager = CacheManager.create();
		} catch (CacheException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取缓存名称
	 * @param cacheName
	 * @return
	 */
	public static Ehcache getCache(String cacheName) {
		return cacheManager.getEhcache(cacheName);
	}
	
	/**
	 * 往缓存里放元素
	 * @param cache
	 * @param element
	 */
	public static void putElement(Ehcache cache, Element element) {
		cache.put(element);
	}

	/**
	 * 从缓存里拿元素
	 */
	public static Element getElement(Ehcache cache, Object key) {
		return cache.get(key);
	}
	

}

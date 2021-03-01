package com.choies;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author 최의신 (choies@kr.ibm.com)
 *
 */
@RestController
public class EhCacheController {
	@Autowired
	private CacheManager cacheManager;

	/**
	 * @param name
	 * @param value
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/put/{name}/{value}", method = RequestMethod.GET)
	public Map<String, String> put(@PathVariable String name, @PathVariable String value,HttpServletRequest request)
	{
		Cache cache = cacheManager.getCache("CHOIES");
		Element em = new Element(name, value);
		cache.put(em);
		
		Map<String, String> model = new HashMap<>();

		model.put("server", getServer());
		model.put("message", "SUCCESS");

		return model;
	}
	
	/**
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
	public Map<String, String> get(@PathVariable String name, HttpServletRequest request)
	{
		Map<String, String> model = new HashMap<>();
		
		Cache cache = cacheManager.getCache("CHOIES");
		Element em = cache.get(name);
		if ( em != null )
			model.put("message", "Value = " + em.getObjectValue());
		else
			model.put("message", "NOT FOUND");
		
		model.put("server", getServer());
		
		return model;
	}
	
	private String getServer() {
		String host = null;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			host = ip.getHostAddress();
		}catch(Exception e) {}
		
		return host;
	}
}

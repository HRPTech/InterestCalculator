package com.bestdeals.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CachingConfig {
	
	@Bean
	public EhCacheCacheManager cacheManager(){
		return new EhCacheCacheManager();
	}
	
	@Bean
	public EhCacheManagerFactoryBean ehCache(){
		EhCacheManagerFactoryBean ehCacheManagerBean = new EhCacheManagerFactoryBean();
		ehCacheManagerBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		ehCacheManagerBean.setShared(true);
		return ehCacheManagerBean;
	}

}

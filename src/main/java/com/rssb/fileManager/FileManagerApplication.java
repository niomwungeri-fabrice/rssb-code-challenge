package com.rssb.fileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import redis.clients.jedis.Jedis;

@SpringBootApplication
@EnableCaching
public class FileManagerApplication {


	public static void main(String[] args) {
		SpringApplication.run(FileManagerApplication.class, args);
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.flushAll();
	}

}

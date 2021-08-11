package com.rssb.fileManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.util.Objects;

@SpringBootApplication
@EnableCaching
public class FileManagerApplication {
	@Autowired
	static private Environment env;

	static Logger logger = LoggerFactory.getLogger(FileManagerApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(FileManagerApplication.class, args);
//		logger.info("Deleting Data in Redis....Started");
//
//		JedisShardInfo shardInfo = new JedisShardInfo("localhost", 6379);
//		shardInfo.setPassword("CIERRY12");
//		Jedis jedis = new Jedis(shardInfo);
//		jedis.connect();
//
//		jedis.flushAll();
//
//		logger.info("Deleting Data in Redis...Completed");
	}

}

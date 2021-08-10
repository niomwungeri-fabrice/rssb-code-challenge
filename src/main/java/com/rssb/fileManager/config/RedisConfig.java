package com.rssb.fileManager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Objects;

@Configuration
@ComponentScan("com.rssb.fileManager")
@PropertySource("classpath:config/redis.properties")
public class RedisConfig {
    Logger logger = LoggerFactory.getLogger(RedisConfig.class);
    @Autowired
    private Environment env;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = null;
        try {

            String redisHost =Objects.requireNonNull(env.getProperty("redis.host"));
            int redisPort = Integer.parseInt(Objects.requireNonNull(env.getProperty("redis.port")));
            String redisPassword = Objects.requireNonNull(env.getProperty("redis.password"));
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost,redisPort );
            redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
            jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
            jedisConnectionFactory.getPoolConfig().setMaxTotal(128);
            jedisConnectionFactory.getPoolConfig().setMaxIdle(5);
            jedisConnectionFactory.getPoolConfig().setMinIdle(1);
            jedisConnectionFactory.getPoolConfig().setTestOnBorrow(true);
            jedisConnectionFactory.getPoolConfig().setTestOnReturn(true);
            jedisConnectionFactory.getPoolConfig().setTestWhileIdle(true);

            JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
            jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));

        }catch (Exception e ){
            throw new RuntimeException("Cannot obtain Redis connection!", e.getCause());
        }
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        template.setEnableTransactionSupport(true);
        return template;
    }
}

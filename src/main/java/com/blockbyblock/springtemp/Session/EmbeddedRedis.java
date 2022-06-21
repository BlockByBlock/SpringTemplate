package com.blockbyblock.springtemp.Session;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import redis.embedded.Redis;
import redis.embedded.RedisServer;

@Getter
@Configuration
public class EmbeddedRedis {
  private int redisPort;
  private String redisHost;
  private Redis redisServer;

  public EmbeddedRedis(
    @Value("${spring.redis.port}") int redisPort, 
    @Value("${spring.redis.host}") String redisHost) {
      this.redisPort = redisPort;
      this.redisHost = redisHost;
      this.redisServer = new RedisServer(redisPort);
  }

  @PostConstruct
	public void postConstruct() {
		redisServer.start();
	}

  @PreDestroy
	public void preDestroy() {
		redisServer.stop();
	}
}

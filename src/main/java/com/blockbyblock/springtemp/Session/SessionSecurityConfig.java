package com.blockbyblock.springtemp.Session;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@AutoConfigureAfter(EmbeddedRedis.class)
@ConditionalOnBean(EmbeddedRedis.class)
@Configuration
@EnableRedisHttpSession
public class SessionSecurityConfig {
  @Bean
  LettuceConnectionFactory connectionFactory() {
    return new LettuceConnectionFactory();
  }
}

// package com.blockbyblock.springtemp.Session;

// import org.springframework.boot.autoconfigure.AutoConfigureAfter;
// import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

// @AutoConfigureAfter(EmbeddedRedis.class)
// @ConditionalOnBean(EmbeddedRedis.class)
// @Configuration
// @EnableRedisRepositories
// public class RedisTemplateConfiguration {
//   @Bean
//   LettuceConnectionFactory redisConnectionFactory(EmbeddedRedis redis) {
//       return new LettuceConnectionFactory(
//         redis.getRedisHost(), redis.getRedisPort());
//   }

//   @Bean
//   public RedisTemplate<?, ?> redisTemplate(LettuceConnectionFactory connectionFactory) {
//     RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
//     template.setConnectionFactory(connectionFactory);
//     return template;
//   }
// }

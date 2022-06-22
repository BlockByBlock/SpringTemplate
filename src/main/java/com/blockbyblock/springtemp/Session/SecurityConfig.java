package com.blockbyblock.springtemp.Session;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  // http security configuration
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests((authz) -> authz
      .anyRequest()
      .authenticated()
      ).httpBasic();
        
    return http.build();
  }

  // jdbc authentication
  @Bean
  DataSource dataSource() throws Exception {
    return new EmbeddedDatabaseBuilder()
      .setType(EmbeddedDatabaseType.H2)
      .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
      .build();
  }

  @Bean
  UserDetailsManager users(DataSource dataSource) throws Exception {
    UserDetails user = User
      .withUsername("user")
      .password(passwordEncoder().encode("password"))
      .roles("USER")
      .build();

    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
    
    users.createUser(user);
    return users;
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

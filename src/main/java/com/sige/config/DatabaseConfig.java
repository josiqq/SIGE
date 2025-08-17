package com.sige.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"file:${HOME}/dep.properties"})
public class DatabaseConfig {
   @Value("${database.url}")
   private String databaseUrl;
   @Value("${database.username}")
   private String databaseUsername;
   @Value("${database.password}")
   private String databasePassword;
   @Value("${port}")
   private Integer serverPort;
   @Value("${sql}")
   private boolean sql;
}

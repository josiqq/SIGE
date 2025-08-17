package com.sige;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MetaApplication {
   public static void main(String[] args) {
      SpringApplication.run(MetaApplication.class, args);
   }
}

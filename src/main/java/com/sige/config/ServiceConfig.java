package com.sige.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sige.storage.FotoStorage;
import com.sige.storage.local.FotoStorageLocal;

@Configuration
public class ServiceConfig {
   @Bean
   public FotoStorage fotoStorage() {
      return new FotoStorageLocal();
   }
}

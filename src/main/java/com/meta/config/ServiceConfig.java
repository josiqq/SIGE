package com.meta.config;

import com.meta.storage.FotoStorage;
import com.meta.storage.local.FotoStorageLocal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
   @Bean
   public FotoStorage fotoStorage() {
      return new FotoStorageLocal();
   }
}

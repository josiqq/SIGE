package com.meta.service;

import com.meta.modelo.ComisionTarjeta;
import com.meta.repository.ComisionTarjetas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComisionTarjetaService {
   @Autowired
   private ComisionTarjetas comisionTarjetas;

   public void guardar(ComisionTarjeta comisionTarjeta) {
      System.out.println("importe: " + comisionTarjeta.getImporte());
      this.comisionTarjetas.save(comisionTarjeta);
   }
}

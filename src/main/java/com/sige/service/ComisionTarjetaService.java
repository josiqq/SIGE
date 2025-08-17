package com.sige.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.ComisionTarjeta;
import com.sige.repository.ComisionTarjetas;

@Service
public class ComisionTarjetaService {
   @Autowired
   private ComisionTarjetas comisionTarjetas;

   public void guardar(ComisionTarjeta comisionTarjeta) {
      System.out.println("importe: " + comisionTarjeta.getImporte());
      this.comisionTarjetas.save(comisionTarjeta);
   }
}

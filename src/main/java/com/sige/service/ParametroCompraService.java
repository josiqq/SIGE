package com.sige.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.ParametroCompra;
import com.sige.repository.ParametroCompras;

@Service
public class ParametroCompraService {
   @Autowired
   private ParametroCompras parametroCompras;

   public void guardar(ParametroCompra parametroCompra) {
      this.parametroCompras.saveAndFlush(parametroCompra);
   }

   public ParametroCompra getParametroCompra() {
      return this.parametroCompras.getParametroCompra();
   }
}

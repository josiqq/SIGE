package com.meta.service;

import com.meta.modelo.ParametroCompra;
import com.meta.repository.ParametroCompras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

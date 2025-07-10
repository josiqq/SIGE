package com.meta.service;

import com.meta.repository.Productos;
import com.meta.repository.filter.StockFilter;
import com.meta.service.exeption.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
   @Autowired
   private Productos productos;

   public void recalcularStock(StockFilter stockFilter) {
      if (stockFilter.getProducto() != null && stockFilter.getDeposito() != null) {
         this.productos.recalcularStock(stockFilter.getProducto().getId(), stockFilter.getDeposito().getId());
      } else {
         throw new NegocioException("Debe informar el producto y el deposito!");
      }
   }
}

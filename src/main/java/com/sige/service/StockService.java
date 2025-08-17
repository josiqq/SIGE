package com.sige.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.repository.Productos;
import com.sige.repository.filter.StockFilter;
import com.sige.service.exeption.NegocioException;

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

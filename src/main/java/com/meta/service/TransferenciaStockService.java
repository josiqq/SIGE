package com.meta.service;

import com.meta.modelo.ItemTransferenciaStock;
import com.meta.modelo.TransferenciaStock;
import com.meta.repository.ItemTransferenciaStocks;
import com.meta.repository.TransferenciaStocks;
import com.meta.service.exeption.NegocioException;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaStockService {
   @Autowired
   private TransferenciaStocks transferenciaStocks;
   @Autowired
   private ItemTransferenciaStocks itemTransferenciaStocks;

   public void guardar(TransferenciaStock transferenciaStock) {
      transferenciaStock.setHora(LocalTime.now());
      this.validar(transferenciaStock);
      this.itemTransferenciaStocks.elimianrItem(transferenciaStock.getId());
      this.transferenciaStocks.save(transferenciaStock);
      this.agregarDetalle(transferenciaStock);
   }

   public void eliminar(Long id) {
      try {
         this.transferenciaStocks.deleteById(id);
      } catch (DataAccessException var5) {
         Throwable rootCause = var5.getRootCause();
         String errorMensaje = "ERROR EN LA BASE DE DATOS :" + rootCause.getMessage();
         throw new NegocioException(errorMensaje);
      }
   }

   private void validar(TransferenciaStock transferenciaStock) {
      if (transferenciaStock.getItems().size() == 0) {
         throw new NegocioException("Debe informar al menos un produccto para la transferencia!");
      } else if (transferenciaStock.getDepositoOrigen().equals(transferenciaStock.getDepositoDestino())) {
         throw new NegocioException("El deposito origen no puede ser igual al deposito destino");
      }
   }

   private void agregarDetalle(TransferenciaStock transferenciaStock) {
      for (ItemTransferenciaStock item : transferenciaStock.getItems()) {
         this.itemTransferenciaStocks.save(item);
      }
   }
}

package com.sige.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.TransferenciaEfectivo;
import com.sige.repository.TransferenciaEfectivos;
import com.sige.repository.filter.TransferenciaEfectivoFilter;
import com.sige.service.exeption.NegocioException;

@Service
public class TransferenciaEfectivoService {
   @Autowired
   private TransferenciaEfectivos transferenciaEfectivos;

   public void guardar(TransferenciaEfectivo transferenciaEfectivo) {
      this.validaciones(transferenciaEfectivo);
      this.transferenciaEfectivos.save(transferenciaEfectivo);
   }

   private void validaciones(TransferenciaEfectivo transferenciaEfectivo) {
      if (transferenciaEfectivo.getImporte().setScale(0, RoundingMode.HALF_DOWN) == BigDecimal.ZERO) {
         throw new NegocioException("Importe no puede ser cero !");
      } else if (transferenciaEfectivo.getMoneda() == null || transferenciaEfectivo.getMonedaDestino() == null) {
         throw new NegocioException("Debe informar la moneda origen y destino!");
      } else if (transferenciaEfectivo.getCondicion() == null) {
         throw new NegocioException("Debe informar la condición");
      } else if (transferenciaEfectivo.getCuentaOrigen().getMoneda() == null) {
         throw new NegocioException("La cuenta origen no tiene moneda asignada");
      } else if (transferenciaEfectivo.getCuentaDestino().getMoneda() == null) {
         throw new NegocioException("La cuenta destino no tiene moneda asignada");
      }
   }

   public TransferenciaEfectivo buscarPorId(Long id) {
      return (TransferenciaEfectivo)this.transferenciaEfectivos.findById(id).orElse(null);
   }

   public List<TransferenciaEfectivo> buscarTransferencia(TransferenciaEfectivoFilter transferenciaEfectivoFilter) {
      return this.transferenciaEfectivos.buscarTransferencia(transferenciaEfectivoFilter);
   }

   public BigDecimal totalImporte(TransferenciaEfectivoFilter transferenciaEfectivoFilter) {
      return this.transferenciaEfectivos.totalImporte(transferenciaEfectivoFilter);
   }

   public void eliminar(Long id) {
      try {
         this.transferenciaEfectivos.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }
}

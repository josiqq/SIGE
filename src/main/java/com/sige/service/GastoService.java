package com.sige.service;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Gasto;
import com.sige.repository.Gastos;
import com.sige.repository.filter.GastoFilter;
import com.sige.service.exeption.ImposibleEliminarExeption;
import com.sige.service.exeption.NegocioException;

@Service
public class GastoService {
   @Autowired
   private Gastos gastos;

   public void guardar(Gasto gasto) {
      this.validaciones(gasto);
      this.gastos.save(gasto);
   }

   private void validaciones(Gasto gasto) {
      if (gasto.getMoneda() == null) {
         throw new NegocioException("Debe informar la moneda del gasto !");
      } else if (!gasto.getMoneda().equals(gasto.getCuenta().getMoneda())) {
         throw new NegocioException("La moneda de la cuenta no es igaul a la moneda del gasto!");
      }
   }

   public void eliminar(Long id) {
      try {
         this.gastos.deleteById(id);
         this.gastos.flush();
      } catch (PersistenceException var3) {
         throw new ImposibleEliminarExeption("No se puede eliminar, ya tuvo movimiento");
      }
   }

   public Gasto buscarPorId(Long id) {
      return (Gasto)this.gastos.findById(id).orElse(null);
   }

   public List<Gasto> buscarGasto(GastoFilter gastoFilter) {
      return this.gastos.BuscarGasto(gastoFilter);
   }

   public BigDecimal totalImporte(GastoFilter gastoFilter) {
      return this.gastos.totalImporte(gastoFilter);
   }
}

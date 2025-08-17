package com.sige.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.CobroServicio;
import com.sige.repository.CobroServicios;
import com.sige.repository.filter.CobroServicioFilter;
import com.sige.service.exeption.NegocioException;

@Service
public class CobroServicioService {
   @Autowired
   private CobroServicios cobroServicios;

   public void guardar(CobroServicio cobroServicio) {
      if (cobroServicio.getImporte().setScale(0, RoundingMode.HALF_UP) == BigDecimal.ZERO
         && cobroServicio.getValor().setScale(0, RoundingMode.HALF_UP) == BigDecimal.ZERO) {
         throw new NegocioException("Debe informar el importe o el valor!");
      } else {
         this.cobroServicios.save(cobroServicio);
      }
   }

   public List<CobroServicio> buscarCobroSercicio(CobroServicioFilter cobroServicioFilter) {
      return this.cobroServicios.buscarCobroServicio(cobroServicioFilter);
   }

   public BigDecimal totalImporte(CobroServicioFilter cobroServicioFilter) {
      return this.cobroServicios.totalImporte(cobroServicioFilter);
   }

   public CobroServicio buscarPorId(Long id) {
      return (CobroServicio)this.cobroServicios.findById(id).orElse(null);
   }

   public void eliminar(Long id) {
      try {
         this.cobroServicios.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimientos");
      }
   }

   public void imprimirServicio(CobroServicio cobroServicio) {
   }
}

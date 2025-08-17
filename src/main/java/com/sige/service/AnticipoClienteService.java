package com.sige.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.AnticipoCliente;
import com.sige.repository.AnticipoClientes;
import com.sige.repository.filter.AnticipoClienteFilter;
import com.sige.service.exeption.NegocioException;

@Service
public class AnticipoClienteService {
   @Autowired
   private AnticipoClientes anticipoClientes;

   public void guardar(AnticipoCliente anticipoCliente) {
      this.anticipoClientes.save(anticipoCliente);
   }

   public AnticipoCliente buscarPorid(Long id) {
      return (AnticipoCliente)this.anticipoClientes.findById(id).orElse(null);
   }

   public BigDecimal totalImporte(AnticipoClienteFilter anticipoClienteFilter) {
      BigDecimal total = BigDecimal.ZERO;

      try {
         total = this.anticipoClientes.totalImporte(anticipoClienteFilter).setScale(0, RoundingMode.HALF_UP);
      } catch (Exception var4) {
         total = BigDecimal.ZERO;
      }

      return total;
   }

   public List<AnticipoCliente> buscarAnticpo(AnticipoClienteFilter anticipoClienteFilter) {
      return this.anticipoClientes.buscarAnticipo(anticipoClienteFilter);
   }

   public void eliminar(Long id) {
      try {
         this.anticipoClientes.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuo movimiento");
      }
   }
}

package com.sige.service;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.PagoVendedor;
import com.sige.repository.PagoVendedores;
import com.sige.repository.filter.PagoVendedorFilter;
import com.sige.service.exeption.NegocioException;

@Service
public class PagoVendedorService {
   @Autowired
   private PagoVendedores pagoVendedores;

   public void guardar(PagoVendedor pagoVendedor) {
      System.out.println("Importe de pago a vendedores=>" + pagoVendedor.getImporte());
      if (pagoVendedor.getImporte().setScale(0, 4) == BigDecimal.ZERO) {
         throw new NegocioException("Importe no puede ser cero!");
      } else {
         this.pagoVendedores.save(pagoVendedor);
      }
   }

   public PagoVendedor buscarPorId(Long id) {
      return (PagoVendedor)this.pagoVendedores.findById(id).orElse(null);
   }

   public List<PagoVendedor> buscarTodos() {
      return this.pagoVendedores.findAll();
   }

   public List<PagoVendedor> buscarPagoVendedor(PagoVendedorFilter pagoVendedorFilter) {
      return this.pagoVendedores.buscarPagoVendedores(pagoVendedorFilter);
   }

   public BigDecimal totalPagoVendedor(PagoVendedorFilter pagoVendedorFilter) {
      return this.pagoVendedores.totalPagoVendedor(pagoVendedorFilter);
   }

   public void borrar(Long id) {
      try {
         this.pagoVendedores.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible Eliminar, ya tuvo movimiento!");
      }
   }
}

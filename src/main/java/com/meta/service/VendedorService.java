package com.meta.service;

import com.meta.modelo.Vendedor;
import com.meta.repository.Vendedores;
import com.meta.repository.filter.VendedorFilter;
import com.meta.service.exeption.NegocioException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {
   @Autowired
   private Vendedores vendedores;

   public void guardar(Vendedor vendedor) {
      Optional<Vendedor> optionalDocumento = this.vendedores.findByDocumento(vendedor.getDocumento());
      if (optionalDocumento.isPresent() && !optionalDocumento.get().equals(vendedor)) {
         throw new NegocioException("Este vendedor tiene el mismo documento: " + optionalDocumento.get().getNombre());
      } else {
         this.vendedores.save(vendedor);
      }
   }

   public Vendedor buscarPorId(Long id) {
      return (Vendedor)this.vendedores.findById(id).orElse(null);
   }

   public void eliminar(Long id) {
      try {
         this.vendedores.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar,ya tuvo movimiento!!");
      }
   }

   public List<Vendedor> buscarTodos() {
      return this.vendedores.findAll();
   }

   public BigDecimal buscarSaldo(Long id) {
      return this.vendedores.buscarSaldo(id);
   }

   public List<Vendedor> buscaVendedor(VendedorFilter vendedorFilter) {
      return this.vendedores.buscarVendedor(vendedorFilter);
   }

   public List<Vendedor> getVendedorSupervidor() {
      return this.vendedores.getVendedorSupervisor();
   }
}

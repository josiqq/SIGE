package com.meta.service;

import com.meta.modelo.Proveedor;
import com.meta.repository.Proveedores;
import com.meta.service.exeption.ImposibleEliminarExeption;
import com.meta.service.exeption.NegocioException;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {
   @Autowired
   private Proveedores proveedores;

   public void guardar(Proveedor proveedor) {
      Optional<Proveedor> proveedorExiste = this.proveedores.findByDocumento(proveedor.getDocumento());
      if (proveedorExiste.isPresent() && !proveedorExiste.get().equals(proveedor)) {
         throw new NegocioException("Ya existe otro poveedor con este documento: " + proveedor.getDocumento());
      } else {
         this.proveedores.save(proveedor);
      }
   }

   public void eliminar(Long id) {
      try {
         this.proveedores.deleteById(id);
         this.proveedores.flush();
      } catch (PersistenceException var3) {
         throw new ImposibleEliminarExeption("No se puede eliminar, ya tuvo movimiento");
      }
   }

   public List<Proveedor> buscar() {
      return this.proveedores.findAll();
   }

   public Proveedor buscarPorId(Long id) {
      return (Proveedor)this.proveedores.findById(id).orElse(null);
   }

   public List<Proveedor> buscarPorNombreDocumento(String nombreDocumento) {
      return this.proveedores.buscarPorNombreODocumento(nombreDocumento);
   }
}

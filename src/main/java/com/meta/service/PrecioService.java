package com.meta.service;

import com.meta.modelo.Precio;
import com.meta.repository.Precios;
import com.meta.service.exeption.NegocioException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrecioService {
   @Autowired
   private Precios precios;

   public void guardar(Precio precio) {
      this.validaciones(precio);
      this.precios.save(precio);
   }

   private void validaciones(@Valid Precio precio) {
      if (precio.getMoneda() == null) {
         throw new NegocioException("Debe informar la moneda!");
      }
   }

   public List<Precio> buscarPrecio() {
      return this.precios.findAll();
   }

   public Precio buscarPorId(Long id) {
      return (Precio)this.precios.findById(id).orElse(null);
   }

   public void eliminar(Long id) {
      this.precios.deleteById(id);
   }
}

package com.meta.service;

import com.meta.modelo.AjusteLote;
import com.meta.modelo.ItemAjusteLote;
import com.meta.modelo.Lote;
import com.meta.repository.AjusteLotes;
import com.meta.repository.ItemAjusteLotes;
import com.meta.repository.Lotes;
import com.meta.service.exeption.NegocioException;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AjusteLoteService {
   @Autowired
   private AjusteLotes ajusteLotes;
   @Autowired
   private ItemAjusteLotes itemAjusteLotes;
   @Autowired
   private Lotes lotes;

   public void guardar(AjusteLote ajusteLote) {
      this.validaciones(ajusteLote);
      this.ajusteLotes.save(ajusteLote);
      this.agregarDetalle(ajusteLote);
   }

   public void eliminar(Long id) {
      try {
         this.ajusteLotes.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }

   private void validaciones(AjusteLote ajusteLote) {
      if (ajusteLote.getItems().size() == 0) {
         throw new NegocioException("No puede guardar ajuste sin detalles!");
      } else {
         for (ItemAjusteLote item : ajusteLote.getItems()) {
            Lote lote = this.lotes.getLoteByNumero(item.getNroLote());
            if (lote.getProducto() != null && !lote.getProducto().equals(item.getProducto())) {
               throw new NegocioException(
                  "Ya existe un producto con este lote: "
                     + item.getNroLote()
                     + " ,este es el producto: ("
                     + lote.getProducto().getCodigo()
                     + ")"
                     + lote.getProducto().getNombre()
               );
            }
         }
      }
   }

   private void agregarDetalle(AjusteLote ajusteLote) {
      this.itemAjusteLotes.eliminarByAjusteLote(ajusteLote.getId());

      for (ItemAjusteLote item : ajusteLote.getItems()) {
         this.itemAjusteLotes.save(item);
      }
   }
}

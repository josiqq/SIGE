package com.sige.service;

import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.AjusteLote;
import com.sige.model.ItemAjusteLote;
import com.sige.model.Lote;
import com.sige.repository.AjusteLotes;
import com.sige.repository.ItemAjusteLotes;
import com.sige.repository.Lotes;
import com.sige.service.exeption.NegocioException;

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

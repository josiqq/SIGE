package com.meta.service;

import com.meta.modelo.Deposito;
import com.meta.modelo.Lote;
import com.meta.repository.Lotes;
import com.meta.service.exeption.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoteService {
   @Autowired
   private Lotes lotes;

   public Lote getLoteByNumero(String nroLote, Long id, Deposito deposito) {
      Lote lote = this.lotes.getLoteByNumero(nroLote);
      Lote loteReal = new Lote();
      if (id == null) {
         throw new NegocioException("Debe informar el producto!");
      } else if (deposito.getId() == null) {
         throw new NegocioException("Debe informar el deposito!");
      } else {
         if (lote.getProducto() != null) {
            if (!lote.getProducto().getId().equals(id)) {
               throw new NegocioException(
                  "Ya existe un producto con este lote, este es el producto: (" + lote.getProducto().getCodigo() + ")" + lote.getProducto().getNombre()
               );
            }

            loteReal = this.lotes.getByLoteDepositoProducto(nroLote, id, deposito);
         }

         return loteReal;
      }
   }
}

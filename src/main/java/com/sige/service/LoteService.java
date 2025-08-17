package com.sige.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Deposito;
import com.sige.model.Lote;
import com.sige.repository.Lotes;
import com.sige.service.exeption.NegocioException;

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

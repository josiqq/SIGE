package com.sige.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.AjusteStock;
import com.sige.model.Auditoria;
import com.sige.model.ItemAjusteStock;
import com.sige.repository.AjusteStocks;
import com.sige.repository.Auditorias;
import com.sige.repository.filter.AjusteStockFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.exeption.NegocioException;

@Service
public class AjusteStockService {
   @Autowired
   private AjusteStocks ajusteStocks;
   @Autowired
   private Auditorias auditorias;

   public void guardar(AjusteStock ajusteStock, UsuarioSistema usuarioSistema) {
      int upd = 0;
      if (ajusteStock.getId() != null) {
         upd = 1;
      }

      if (ajusteStock.getItems().isEmpty()) {
         throw new NegocioException("No puede guardar ajuste sin detalles");
      } else {
         this.ajusteStocks.save(ajusteStock);
         this.agregarAuditoria(ajusteStock, usuarioSistema, upd);
      }
   }

   public List<AjusteStock> buscarAjustes(AjusteStockFilter ajusteStockFilter) {
      return this.ajusteStocks.buscarAjuste(ajusteStockFilter);
   }

   public AjusteStock buscarPorId(Long id) {
      return (AjusteStock)this.ajusteStocks.findById(id).orElse(null);
   }

   public void eliminar(Long id, UsuarioSistema usuarioSistema) {
      AjusteStock ajusteStock = (AjusteStock)this.ajusteStocks.findById(id).orElse(null);

      try {
         this.ajusteStocks.deleteById(id);
         this.agregarAuditoria(ajusteStock, usuarioSistema, 2);
      } catch (PersistenceException var5) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }

   public BigDecimal getCantidadStock(Long producto, Long deposito) {
      return this.ajusteStocks.getCantidadStock(producto, deposito);
   }

   private void agregarAuditoria(AjusteStock ajusteStock, UsuarioSistema usuarioSistema, int upd) {
      String tipo = "";
      if (upd == 0) {
         tipo = "INSERT";
      } else if (upd == 1) {
         tipo = "UPDATE";
      } else if (upd == 2) {
         tipo = "DELETE";
      }

      for (ItemAjusteStock item : ajusteStock.getItems()) {
         Auditoria auditoriaitems = new Auditoria();
         auditoriaitems.setRegistro(ajusteStock.getId());
         auditoriaitems.setTabla("ITEM_AJUSTE_STOCK");
         auditoriaitems.setFecha(LocalDate.now());
         auditoriaitems.setHora(LocalTime.now());
         auditoriaitems.setTipo(tipo);
         auditoriaitems.setCodigo(item.getProducto().getCodigo());
         auditoriaitems.setProducto(item.getProducto().getNombre());
         auditoriaitems.setUsuario(usuarioSistema.getUsername());
         this.auditorias.save(auditoriaitems);
      }
   }
}

package com.sige.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.AjustePrecio;
import com.sige.model.Auditoria;
import com.sige.model.ItemAjustePrecio;
import com.sige.repository.AjustePrecios;
import com.sige.repository.Auditorias;
import com.sige.repository.filter.AjustePrecioFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.exeption.NegocioException;

@Service
public class AjustePrecioService {
   @Autowired
   private AjustePrecios ajustePrecios;
   @Autowired
   private Auditorias auditorias;

   public void guardar(AjustePrecio ajustePrecio, UsuarioSistema usuario) {
      int upd = 0;
      if (ajustePrecio.getId() != null) {
         upd = 1;
      }

      if (ajustePrecio.getItems().isEmpty()) {
         throw new NegocioException("No puede guardar Ajuste sin informar producto!");
      } else {
         this.ajustePrecios.save(ajustePrecio);
         this.agregarAuditoria(ajustePrecio, usuario, upd);
      }
   }

   public List<AjustePrecio> buscarAjuste(AjustePrecioFilter ajustePrecioFilter) {
      return this.ajustePrecios.buscarAjustePrecio(ajustePrecioFilter);
   }

   public AjustePrecio buscarPorId(Long id) {
      return (AjustePrecio)this.ajustePrecios.findById(id).orElse(null);
   }

   public void eliminar(Long id, UsuarioSistema usuarioSistema) {
      AjustePrecio ajustePrecio = (AjustePrecio)this.ajustePrecios.findById(id).orElse(null);

      try {
         this.ajustePrecios.deleteById(id);
         this.agregarAuditoria(ajustePrecio, usuarioSistema, 2);
      } catch (PersistenceException var5) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }

   private void agregarAuditoria(AjustePrecio ajustePrecio, UsuarioSistema usuarioSistema, int upd) {
      String tipo = "";
      if (upd == 0) {
         tipo = "INSERT";
      } else if (upd == 1) {
         tipo = "UPDATE";
      } else if (upd == 2) {
         tipo = "DELETE";
      }

      for (ItemAjustePrecio item : ajustePrecio.getItems()) {
         Auditoria auditoriaitems = new Auditoria();
         auditoriaitems.setRegistro(ajustePrecio.getId());
         auditoriaitems.setTabla("ITEM_AJUSTE_PRECIO");
         auditoriaitems.setFecha(LocalDate.now());
         auditoriaitems.setHora(LocalTime.now());
         auditoriaitems.setTipo(tipo);
         auditoriaitems.setCodigo(item.getProducto().getCodigo());
         auditoriaitems.setProducto(item.getProducto().getNombre());
         auditoriaitems.setPrecio(item.getAjustePrecio().getPrecio().getNombre());
         auditoriaitems.setUsuario(usuarioSistema.getUsername());
         this.auditorias.save(auditoriaitems);
      }
   }
}

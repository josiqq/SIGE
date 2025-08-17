package com.sige.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Auditoria;
import com.sige.model.ItemPago;
import com.sige.model.ItemPagoImporte;
import com.sige.model.Pago;
import com.sige.repository.Auditorias;
import com.sige.repository.Pagos;
import com.sige.repository.filter.PagoFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.exeption.NegocioException;

@Service
public class PagoService {
   @Autowired
   private Pagos pagos;
   @Autowired
   private Auditorias auditorias;

   public void guardar(Pago pago, UsuarioSistema usuarioSistema) {
      int upd = 0;
      if (pago.getId() != null) {
         upd = 1;
      }

      if (pago.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
         throw new NegocioException("El saldo no puede ser mayor a cero!");
      } else if (!pago.getItemPagos().isEmpty() && !pago.getItemPagoImportes().isEmpty()) {
         this.pagos.save(pago);
         this.agregarAuditoria(pago, usuarioSistema, upd);
      } else {
         throw new NegocioException("No puede guardar sin detalles de pago o documento !");
      }
   }

   public void elimina(Long id, UsuarioSistema usuarioSistema) {
      Pago pago = (Pago)this.pagos.findById(id).orElse(null);

      try {
         this.pagos.deleteById(id);
         this.agregarAuditoria(pago, usuarioSistema, 2);
      } catch (PersistenceException var5) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }

   public Pago getOne(Long id) {
      return (Pago)this.pagos.findById(id).orElse(null);
   }

   public List<Pago> getPagos(PagoFilter pagoFilter) {
      return this.pagos.getPagos(pagoFilter);
   }

   private void agregarAuditoria(Pago pago, UsuarioSistema usuarioSistema, int upd) {
      String tipo = "";
      if (upd == 0) {
         tipo = "INSERT";
      } else if (upd == 1) {
         tipo = "UPDATE";
      } else if (upd == 2) {
         tipo = "DELETE";
      }

      Auditoria auditoria = new Auditoria();
      auditoria.setRegistro(pago.getId());
      auditoria.setTabla("PAGO");
      auditoria.setFecha(LocalDate.now());
      auditoria.setHora(LocalTime.now());
      auditoria.setTipo(tipo);
      auditoria.setUsuario(usuarioSistema.getUsername());
      this.auditorias.save(auditoria);

      for (ItemPago item : pago.getItemPagos()) {
         Auditoria auditoriaitems = new Auditoria();
         auditoriaitems.setRegistro(pago.getId());
         auditoriaitems.setTabla("ITEM_PAGO");
         auditoriaitems.setFecha(LocalDate.now());
         auditoriaitems.setHora(LocalTime.now());
         auditoriaitems.setTipo(tipo);
         auditoriaitems.setUsuario(usuarioSistema.getUsername());
         this.auditorias.save(auditoriaitems);
      }

      for (ItemPagoImporte itemPIM : pago.getItemPagoImportes()) {
         Auditoria auditoriaitems = new Auditoria();
         auditoriaitems.setRegistro(pago.getId());
         auditoriaitems.setTabla("ITEM_PAGO_IMPORTE");
         auditoriaitems.setFecha(LocalDate.now());
         auditoriaitems.setHora(LocalTime.now());
         auditoriaitems.setTipo(tipo);
         auditoriaitems.setUsuario(usuarioSistema.getUsername());
         this.auditorias.save(auditoriaitems);
      }
   }
}

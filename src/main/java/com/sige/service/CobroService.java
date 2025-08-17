package com.sige.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Auditoria;
import com.sige.model.Cobro;
import com.sige.model.ItemCobro;
import com.sige.model.ItemCobroImporte;
import com.sige.model.Parametro;
import com.sige.model.Planilla;
import com.sige.repository.Auditorias;
import com.sige.repository.Cobros;
import com.sige.repository.Parametros;
import com.sige.repository.Planillas;
import com.sige.repository.filter.CobroFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.exeption.NegocioException;

@Service
public class CobroService {
   @Autowired
   private Cobros cobros;
   @Autowired
   private Planillas planillas;
   @Autowired
   private Parametros parametros;
   @Autowired
   private Auditorias auditorias;

   public void guardar(Cobro cobro, UsuarioSistema usuarioSistema) {
      int upd = 0;
      if (cobro.getId() != null) {
         upd = 1;
      }

      this.cobros.save(cobro);
      this.agregarAuditoria(cobro, usuarioSistema, upd);
   }

   public void guardarPrincipal(Cobro cobro, UsuarioSistema usuarioSistema) {
      int upd = 0;
      if (cobro.getId() != null) {
         upd = 1;
      }

      Optional<Planilla> planillaOp = this.planillas.getByCuenta(cobro.getCuenta());
      Parametro parametro = (Parametro)this.parametros.findById(1L).orElse(null);
      if (parametro.isAbrePlanilla()) {
         if (!planillaOp.isPresent()) {
            throw new NegocioException("No existe planilla abierta para esta cuenta : " + cobro.getCuenta().getNombre());
         }

         if (!planillaOp.get().getFecha().equals(LocalDate.now())) {
            throw new NegocioException(
               "La fecha de la planilla es distinto a la fecha del sistema, fecha de planilla: "
                  + planillaOp.get().getFecha()
                  + " Fecha del sistema:"
                  + LocalDate.now()
            );
         }
      }

      if (cobro.getItemsCobros().size() == 0) {
         throw new NegocioException("No puede guardar un cobro sin documentos !");
      } else if (cobro.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
         throw new NegocioException("El saldo no puede ser mayor a cero !");
      } else {
         this.cobros.save(cobro);
         this.agregarAuditoria(cobro, usuarioSistema, upd);
      }
   }

   public void eliminar(Long id, UsuarioSistema usuarioSistema) {
      Cobro cobro = (Cobro)this.cobros.findById(id).orElse(null);

      try {
         this.cobros.deleteById(id);
         this.agregarAuditoria(cobro, usuarioSistema, 2);
      } catch (PersistenceException var5) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento");
      }
   }

   public List<Cobro> getCobros(CobroFilter cobroFilter) {
      return this.cobros.getCobros(cobroFilter);
   }

   public Cobro getById(Long id) {
      return (Cobro)this.cobros.findById(id).orElse(null);
   }

   private void agregarAuditoria(Cobro cobro, UsuarioSistema usuarioSistema, int upd) {
      String tipo = "";
      if (upd == 0) {
         tipo = "INSERT";
      } else if (upd == 1) {
         tipo = "UPDATE";
      } else if (upd == 2) {
         tipo = "DELETE";
      }

      Auditoria auditoria = new Auditoria();
      auditoria.setRegistro(cobro.getId());
      auditoria.setTabla("COBRO");
      auditoria.setFecha(LocalDate.now());
      auditoria.setHora(LocalTime.now());
      auditoria.setTipo(tipo);
      auditoria.setUsuario(usuarioSistema.getUsername());
      this.auditorias.save(auditoria);

      for (ItemCobro item : cobro.getItemsCobros()) {
         Auditoria auditoriaitems = new Auditoria();
         auditoriaitems.setRegistro(cobro.getId());
         auditoriaitems.setTabla("ITEM_COBRO");
         auditoriaitems.setFecha(LocalDate.now());
         auditoriaitems.setHora(LocalTime.now());
         auditoriaitems.setTipo(tipo);
         auditoriaitems.setUsuario(usuarioSistema.getUsername());
         this.auditorias.save(auditoriaitems);
      }

      for (ItemCobroImporte itemCIm : cobro.getItemsCobrosImportes()) {
         Auditoria auditoriaitems = new Auditoria();
         auditoriaitems.setRegistro(cobro.getId());
         auditoriaitems.setTabla("ITEM_COBRO_IMPORTE");
         auditoriaitems.setFecha(LocalDate.now());
         auditoriaitems.setHora(LocalTime.now());
         auditoriaitems.setTipo(tipo);
         auditoriaitems.setUsuario(usuarioSistema.getUsername());
         this.auditorias.save(auditoriaitems);
      }
   }
}

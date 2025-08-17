package com.sige.service.event.equipo;

import org.springframework.util.StringUtils;

import com.sige.model.Equipo;

public class EquipoGuardaEvent {
   private Equipo equipo;

   public EquipoGuardaEvent(Equipo equipo) {
      this.equipo = equipo;
   }

   public Equipo getEquipo() {
      return this.equipo;
   }

   public boolean tieneFoto() {
      return !StringUtils.isEmpty(this.equipo.getFoto());
   }

   public boolean isNuevaFoto() {
      return this.equipo.isNuevaFoto();
   }
}

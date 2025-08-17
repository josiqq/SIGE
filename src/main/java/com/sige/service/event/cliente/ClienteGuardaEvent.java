package com.sige.service.event.cliente;

import org.springframework.util.StringUtils;

import com.sige.model.Cliente;

public class ClienteGuardaEvent {
   private Cliente cliente;

   public ClienteGuardaEvent(Cliente cliente) {
      this.cliente = cliente;
   }

   public Cliente getCliente() {
      return this.cliente;
   }

   public boolean tieneFoto() {
      return !StringUtils.isEmpty(this.cliente.getFoto());
   }

   public boolean isNuevaFoto() {
      return this.cliente.isNuevaFoto();
   }
}

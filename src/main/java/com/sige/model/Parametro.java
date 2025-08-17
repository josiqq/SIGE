package com.sige.model;

import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Parametro {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   private String Empresa;
   private String ruc;
   private String telefono;
   private boolean mostrarFondo;
   private boolean mostrarHora;
   @DateTimeFormat(
      pattern = "HH:mm"
   )
   private LocalTime horaDesde;
   @DateTimeFormat(
      pattern = "HH:mm"
   )
   private LocalTime horaHasta;
   @DateTimeFormat(
      pattern = "HH:mm"
   )
   private LocalTime interHora;
   @ManyToOne
   @JoinColumn(
      name = "id_cliente"
   )
   private Cliente cliente;
   private String actividad;
   private boolean abrePlanilla;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   private Moneda moneda;
   private boolean cotizacionAutomatica;
   private String version;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getEmpresa() {
      return this.Empresa;
   }

   public void setEmpresa(String empresa) {
      this.Empresa = empresa;
   }

   public String getRuc() {
      return this.ruc;
   }

   public void setRuc(String ruc) {
      this.ruc = ruc;
   }

   public String getTelefono() {
      return this.telefono;
   }

   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }

   public boolean isMostrarFondo() {
      return this.mostrarFondo;
   }

   public void setMostrarFondo(boolean mostrarFondo) {
      this.mostrarFondo = mostrarFondo;
   }

   public boolean isMostrarHora() {
      return this.mostrarHora;
   }

   public void setMostrarHora(boolean mostrarHora) {
      this.mostrarHora = mostrarHora;
   }

   public LocalTime getHoraDesde() {
      return this.horaDesde;
   }

   public void setHoraDesde(LocalTime horaDesde) {
      this.horaDesde = horaDesde;
   }

   public LocalTime getHoraHasta() {
      return this.horaHasta;
   }

   public void setHoraHasta(LocalTime horaHasta) {
      this.horaHasta = horaHasta;
   }

   public LocalTime getInterHora() {
      return this.interHora;
   }

   public void setInterHora(LocalTime interHora) {
      this.interHora = interHora;
   }

   public Cliente getCliente() {
      return this.cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public String getActividad() {
      return this.actividad;
   }

   public void setActividad(String actividad) {
      this.actividad = actividad;
   }

   public boolean isAbrePlanilla() {
      return this.abrePlanilla;
   }

   public void setAbrePlanilla(boolean abrePlanilla) {
      this.abrePlanilla = abrePlanilla;
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
   }

   public boolean isCotizacionAutomatica() {
      return this.cotizacionAutomatica;
   }

   public void setCotizacionAutomatica(boolean cotizacionAutomatica) {
      this.cotizacionAutomatica = cotizacionAutomatica;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.id);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         Parametro other = (Parametro)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}

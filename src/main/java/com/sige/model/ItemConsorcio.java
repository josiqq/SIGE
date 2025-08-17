package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ItemConsorcio {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_cliente"
   )
   private Cliente cliente;
   private BigDecimal monto;
   private Integer meses;
   @ManyToOne
   @JoinColumn(
      name = "id_consorcio"
   )
   private Consorcio consorcio;
   @OneToMany(
      mappedBy = "itemConsorcio",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemConsorcioImporte> itemsImporte = new ArrayList<>();
   private boolean procesado;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public BigDecimal getMonto() {
      return this.monto;
   }

   public void setMonto(BigDecimal monto) {
      this.monto = monto;
   }

   public Consorcio getConsorcio() {
      return this.consorcio;
   }

   public void setConsorcio(Consorcio consorcio) {
      this.consorcio = consorcio;
   }

   public Cliente getCliente() {
      return this.cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Integer getMeses() {
      return this.meses;
   }

   public void setMeses(Integer meses) {
      this.meses = meses;
   }

   public List<ItemConsorcioImporte> getItemsImporte() {
      return this.itemsImporte;
   }

   public void setItemsImporte(List<ItemConsorcioImporte> itemsImporte) {
      this.itemsImporte = itemsImporte;
   }

   public void agregarItemImporte(List<ItemConsorcioImporte> itemsImporte) {
      this.itemsImporte = itemsImporte;
      this.itemsImporte.forEach(i -> i.setItemConsorcio(this));
   }

   public boolean isProcesado() {
      return this.procesado;
   }

   public void setProcesado(boolean procesado) {
      this.procesado = procesado;
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
         ItemConsorcio other = (ItemConsorcio)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}

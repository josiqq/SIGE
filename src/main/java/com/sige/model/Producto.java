package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.apache.groovy.parser.antlr4.util.StringUtils;

@Entity
public class Producto {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @Column(
      unique = true
   )
   private String codigo;
   @NotBlank(
      message = "Debe Informar el nombre del producto!!"
   )
   private String nombre;
   @ManyToOne
   @JoinColumn(
      name = "id_marca"
   )
   @NotNull(
      message = "Debe informar la marca !"
   )
   private Marca marca;
   private Integer gravado = 10;
   private Integer porcGravado = 100;
   private boolean activo = true;
   private String foto;
   private String contentType;
   @Transient
   public boolean nuevaFoto;
   @Transient
   public String traeFoto;
   private BigDecimal costo = BigDecimal.ZERO;
   private CostoCalculo costoCalculo = CostoCalculo.PROMEDIO;
   private boolean pesable;
   @OneToMany(
      mappedBy = "producto",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   public List<ItemProductoCodigo> items = new ArrayList<>();
   @Transient
   private String uuid;
   private BigDecimal comision = BigDecimal.ZERO;
   private boolean servicio;
   @ManyToOne
   @JoinColumn(
      name = "id_usuario"
   )
   @JsonIgnore
   private Usuario usuario;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   @NotNull(
      message = "Debe informar la moneda!"
   )
   private Moneda moneda;
   private boolean conLote;
   private BigDecimal stockMinimo = BigDecimal.ZERO;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getCodigo() {
      return this.codigo;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public Marca getMarca() {
      return this.marca;
   }

   public void setMarca(Marca marca) {
      this.marca = marca;
   }

   public String getFoto() {
      return this.foto;
   }

   public void setFoto(String foto) {
      this.foto = foto;
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
   }

   public boolean isNuevaFoto() {
      return this.nuevaFoto;
   }

   public void setNuevaFoto(boolean nuevaFoto) {
      this.nuevaFoto = nuevaFoto;
   }

   public String fotoONada() {
      return !StringUtils.isEmpty(this.foto) ? this.foto : "sinfoto.png";
   }

   public Integer getGravado() {
      return this.gravado;
   }

   public void setGravado(Integer gravado) {
      this.gravado = gravado;
   }

   public Integer getPorcGravado() {
      return this.porcGravado;
   }

   public void setPorcGravado(Integer porcGravado) {
      this.porcGravado = porcGravado;
   }

   public boolean isActivo() {
      return this.activo;
   }

   public void setActivo(boolean activo) {
      this.activo = activo;
   }

   public String getTraeFoto() {
      return this.traeFoto;
   }

   public void setTraeFoto(String traeFoto) {
      this.traeFoto = traeFoto;
   }

   public BigDecimal getCosto() {
      return this.costo;
   }

   public void setCosto(BigDecimal costo) {
      this.costo = costo;
   }

   public CostoCalculo getCostoCalculo() {
      return this.costoCalculo;
   }

   public void setCostoCalculo(CostoCalculo costoCalculo) {
      this.costoCalculo = costoCalculo;
   }

   public boolean isPesable() {
      return this.pesable;
   }

   public void setPesable(boolean pesable) {
      this.pesable = pesable;
   }

   public List<ItemProductoCodigo> getItems() {
      return this.items;
   }

   public void setItems(List<ItemProductoCodigo> items) {
      this.items = items;
   }

   public void cargarItem(List<ItemProductoCodigo> items) {
      this.items = items;
      this.items.forEach(i -> i.setProducto(this));
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public BigDecimal getComision() {
      return this.comision;
   }

   public void setComision(BigDecimal comision) {
      this.comision = comision;
   }

   public boolean isServicio() {
      return this.servicio;
   }

   public void setServicio(boolean servicio) {
      this.servicio = servicio;
   }

   public Usuario getUsuario() {
      return this.usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
   }

   public boolean isConLote() {
      return this.conLote;
   }

   public void setConLote(boolean conLote) {
      this.conLote = conLote;
   }

   public BigDecimal getStockMinimo() {
      return this.stockMinimo;
   }

   public void setStockMinimo(BigDecimal stockMinimo) {
      this.stockMinimo = stockMinimo;
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
         Producto other = (Producto)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}

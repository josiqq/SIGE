package com.sige.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Auditoria;
import com.sige.model.Compra;
import com.sige.model.ItemCompra;
import com.sige.model.ItemPago;
import com.sige.repository.Auditorias;
import com.sige.repository.Compras;
import com.sige.repository.ItemPagos;
import com.sige.repository.filter.CompraFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.exeption.NegocioException;

@Service
public class CompraService {
   @Autowired
   private Compras compras;
   @Autowired
   private ItemPagos itemPagos;
   @Autowired
   private Auditorias auditorias;

   public void guardar(Compra compra, UsuarioSistema usuarioSistema) {
      int upd = 0;
      if (compra.getId() != null) {
         upd = 1;
      }

      if (compra.getItems().isEmpty()) {
         throw new NegocioException("Debe informar al menos un producto !");
      } else {
         if (compra.getId() != null) {
            List<ItemPago> itemPagoList = this.itemPagos.getItemPagos(compra);
            if (itemPagoList.size() > 0) {
               throw new NegocioException("No puede modificar esta compra porque ya tiene pago!");
            }
         }

         BigDecimal precioTotalItems = compra.getItems().stream().map(ItemCompra::getSubtotal).reduce(BigDecimal::add).get();
         BigDecimal precioTotal = precioTotalItems.setScale(4, RoundingMode.HALF_UP);
         compra.setTotal(precioTotal);
         this.compras.save(compra);
         this.agregarAuditoria(compra, usuarioSistema, upd);
      }
   }

   public List<Compra> buscarTodos() {
      return this.compras.findAll();
   }

   public Compra buscarPorId(Long id) {
      return (Compra)this.compras.findById(id).orElse(null);
   }

   public void eliminar(Long id, UsuarioSistema usuarioSistema) {
      Compra compra = (Compra)this.compras.findById(id).orElse(null);
      this.compras.deleteById(id);
      this.agregarAuditoria(compra, usuarioSistema, 2);
   }

   public List<Compra> buscarCompra(CompraFilter compraFilter) {
      return this.compras.buscarCompra(compraFilter);
   }

   public List<ItemCompra> getItemCompraByCompra(Compra compra) {
      return this.compras.getItemCompraByCompra(compra);
   }

   private void agregarAuditoria(Compra compra, UsuarioSistema usuarioSistema, int upd) {
      String tipo = "";
      if (upd == 0) {
         tipo = "INSERT";
      } else if (upd == 1) {
         tipo = "UPDATE";
      } else if (upd == 2) {
         tipo = "DELETE";
      }

      Auditoria auditoria = new Auditoria();
      auditoria.setRegistro(compra.getId());
      auditoria.setTabla("COMPRA");
      auditoria.setFecha(LocalDate.now());
      auditoria.setHora(LocalTime.now());
      auditoria.setTipo(tipo);
      auditoria.setUsuario(usuarioSistema.getUsername());
      this.auditorias.save(auditoria);

      for (ItemCompra item : compra.getItems()) {
         Auditoria auditoriaitems = new Auditoria();
         auditoriaitems.setRegistro(compra.getId());
         auditoriaitems.setTabla("ITEM_COMPRA");
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

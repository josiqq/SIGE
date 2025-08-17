package com.sige.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.dto.FacturaDto;
import com.sige.model.Auditoria;
import com.sige.model.CondicionVenta;
import com.sige.model.ItemCobro;
import com.sige.model.ItemVenta;
import com.sige.model.ItemVentaLote;
import com.sige.model.Timbrado;
import com.sige.model.Venta;
import com.sige.repository.Auditorias;
import com.sige.repository.ItemCobros;
import com.sige.repository.ItemVentaLotes;
import com.sige.repository.ItemVentas;
import com.sige.repository.MovimientoVendedores;
import com.sige.repository.Ventas;
import com.sige.repository.filter.VentaFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.exeption.NegocioException;
import com.sige.service.manipulacion.VentaManipular;

@Service
public class VentaService {
   @Autowired
   private Ventas ventas;
   @Autowired
   private ItemCobros itemCobros;
   @Autowired
   private Auditorias auditorias;
   @Autowired
   private MovimientoVendedores movimientoVendedores;
   @Autowired
   private ItemVentas itemVentas;
   @Autowired
   private ItemVentaLotes itemVentaLotes;
   @Autowired
   private VentaManipular ventaManipular;

   public void guardar(Venta venta, UsuarioSistema usuarioSistema) {
      int upd = 0;
      if (venta.getId() != null) {
         upd = 1;
      }

      this.manipulacionNc(venta);
      this.validaciones(venta);
      this.itemVentaLotes.eliminarItemVentaLote(venta.getId());
      this.itemVentas.eliminarItemVenta(venta.getId());
      this.ventas.save(venta);
      this.adicionarItem(venta);
      this.agregarAuditoria(venta, usuarioSistema, upd);
   }

   public List<Venta> buscarVenta(VentaFilter ventaFilter) {
      return this.ventas.buscarVenta(ventaFilter);
   }

   public Venta buscarPorId(Long id) {
      return (Venta)this.ventas.findById(id).orElse(null);
   }

   public void eliminar(Long id, UsuarioSistema usuarioSistema) {
      List<ItemCobro> itemCobroList = this.itemCobros.getByIdVenta(id);
      if (itemCobroList.size() > 0) {
         throw new NegocioException("No puede eliminar esta venta porque ya tiene cobro!");
      } else {
         Venta venta = (Venta)this.ventas.findById(id).orElse(null);

         try {
            this.ventas.deleteById(id);
            this.agregarAuditoria(venta, usuarioSistema, 2);
         } catch (PersistenceException var6) {
            throw new NegocioException("Imposible eliminar, ya tuvo movimientos");
         }
      }
   }

   public List<Object[]> getVentaPorProducto(VentaFilter ventaFilter) {
      return this.ventas.getVentaPorProducto(ventaFilter);
   }

   public List<FacturaDto> getFacturas(Long id) {
      return this.ventas.getFactura(id);
   }

   public Integer getNroFactura(Timbrado timbrado) {
      return this.ventas.getNroFactura(timbrado);
   }

   public void updateNroFactura(Venta venta, Timbrado timbrado, Integer nroFactura) {
      this.ventas.updateNroFactura(timbrado, venta, nroFactura);
   }

   public void updateImpreso(Long id) {
      this.ventas.updateImpreso(id);
   }

   public List<ItemVenta> getItemVentaByVenta(Venta venta) {
      return this.ventas.getItemVentaByVenta(venta);
   }

   private void validaciones(Venta venta) {
      if (venta.getId() != null) {
         List<ItemCobro> itemCobroList = this.itemCobros.buscarPorVenta(venta);
         if (itemCobroList.size() > 0) {
            throw new NegocioException("No puede modificar esta venta porque ya tiene cobro!");
         }
      }

      if (venta.getItems().isEmpty()) {
         throw new NegocioException("No puede guardar venta sin producto !");
      } else {
         this.ventaManipular.itemVentaManipular(venta);
      }
   }

   private void adicionarItem(Venta venta) {
      for (ItemVenta item : venta.getItems()) {
         this.itemVentas.save(item);
      }

      for (ItemVentaLote itemLote : venta.getItemsLote()) {
         this.itemVentaLotes.save(itemLote);
      }
   }

   private void agregarAuditoria(Venta venta, UsuarioSistema usuarioSistema, int upd) {
      String tipo = "";
      if (upd == 0) {
         tipo = "INSERT";
      } else if (upd == 1) {
         tipo = "UPDATE";
      } else if (upd == 2) {
         tipo = "DELETE";
      }

      Auditoria auditoria = new Auditoria();
      auditoria.setRegistro(venta.getId());
      auditoria.setTabla("VENTA");
      auditoria.setFecha(LocalDate.now());
      auditoria.setHora(LocalTime.now());
      auditoria.setTipo(tipo);
      auditoria.setUsuario(usuarioSistema.getUsername());
      this.auditorias.save(auditoria);

      for (ItemVenta item : venta.getItems()) {
         Auditoria auditoriaitems = new Auditoria();
         auditoriaitems.setRegistro(venta.getId());
         auditoriaitems.setTabla("ITEM_VENTA");
         auditoriaitems.setFecha(LocalDate.now());
         auditoriaitems.setHora(LocalTime.now());
         auditoriaitems.setTipo(tipo);
         auditoriaitems.setCodigo(item.getProducto().getCodigo());
         auditoriaitems.setProducto(item.getProducto().getNombre());
         auditoriaitems.setUsuario(usuarioSistema.getUsername());
         this.auditorias.save(auditoriaitems);
      }
   }

   private void manipulacionNc(Venta venta) {
      if (venta.isNc()) {
         venta.setCondicionVenta(CondicionVenta.CREDITO);
      }
   }
}

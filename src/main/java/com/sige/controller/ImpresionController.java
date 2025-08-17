package com.sige.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sige.model.Cobro;
import com.sige.model.ItemCobro;
import com.sige.model.ItemCobroImporte;
import com.sige.model.ItemPresupuestoVenta;
import com.sige.model.ItemVenta;
import com.sige.model.Parametro;
import com.sige.model.PresupuestoVenta;
import com.sige.model.Venta;
import com.sige.repository.Cobros;
import com.sige.repository.Parametros;
import com.sige.repository.PresupuestoVentas;
import com.sige.repository.Ventas;
import com.sige.service.PlantillaService;

@RestController
public class ImpresionController {
   @Autowired
   private PlantillaService plantillaService;
   @Autowired
   private PresupuestoVentas presupuestoVentas;
   @Autowired
   private Parametros parametros;
   @Autowired
   private Ventas ventas;
   @Autowired
   private Cobros cobros;

   @GetMapping({"/generar-impresion-presupuesto-venta"})
   public String generarImpresionPresupuestoVenta(Long id) throws IOException {
      PresupuestoVenta presupuestoVenta = (PresupuestoVenta)this.presupuestoVentas.findById(id).orElse(null);
      Parametro parametro = this.parametros.getParametro();
      List<ItemPresupuestoVenta> item = this.presupuestoVentas.getItemPresupuesto(presupuestoVenta);
      Map<String, Object> modelo = new HashMap<>();
      modelo.put("presupuesto", presupuestoVenta);
      modelo.put("items", item);
      modelo.put("parametro", parametro);
      String plantilla = this.plantillaService.cargarPlantilla("presupuesto_venta.html");
      return new TemplateEngine().process(plantilla, new Context(Locale.getDefault(), modelo));
   }

   @GetMapping({"/generar-impresion-venta"})
   public String generarImpresionVenta(Long id) throws IOException {
      Venta venta = (Venta)this.ventas.findById(id).orElse(null);
      Parametro parametro = this.parametros.getParametro();
      List<ItemVenta> item = this.ventas.getItemVentaByVenta(venta);
      Map<String, Object> modelo = new HashMap<>();
      modelo.put("venta", venta);
      modelo.put("items", item);
      modelo.put("parametro", parametro);
      String plantilla = this.plantillaService.cargarPlantilla("venta.html");
      return new TemplateEngine().process(plantilla, new Context(Locale.getDefault(), modelo));
   }

   @GetMapping({"/generar-impresion-cobro"})
   public String generarImpresionCobro(Long id) throws IOException {
      Cobro cobro = (Cobro)this.cobros.findById(id).orElse(null);
      Parametro parametro = this.parametros.getParametro();
      List<ItemCobro> itemCobro = cobro.getItemsCobros();
      List<ItemCobroImporte> itemCobroImporte = cobro.getItemsCobrosImportes();
      Map<String, Object> modelo = new HashMap<>();
      modelo.put("cobro", cobro);
      modelo.put("itemCobros", itemCobro);
      modelo.put("itemCobrosImportes", itemCobroImporte);
      modelo.put("parametro", parametro);
      String plantilla = this.plantillaService.cargarPlantilla("cobro.html");
      return new TemplateEngine().process(plantilla, new Context(Locale.getDefault(), modelo));
   }
}

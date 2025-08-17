package com.sige.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.model.ItemPresupuestoCompra;
import com.sige.model.Parametro;
import com.sige.model.ParametroCompra;
import com.sige.model.PresupuestoCompra;
import com.sige.model.Producto;
import com.sige.repository.Depositos;
import com.sige.repository.Monedas;
import com.sige.repository.ParametroCompras;
import com.sige.repository.Parametros;
import com.sige.repository.PresupuestoCompras;
import com.sige.repository.filter.PresupuestoCompraFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.PresupuestoCompraService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemPresupuestoCompraSession;

@Controller
@RequestMapping({"/presupuestoCompras"})
public class PresupuestoCompraController {
   @Autowired
   private PresupuestoCompras presupuestos;
   @Autowired
   private Depositos depositos;
   @Autowired
   private Monedas monedas;
   @Autowired
   private PresupuestoCompraService presupuestoService;
   @Autowired
   private TablaItemPresupuestoCompraSession presupuestoSession;
   @Autowired
   private ParametroCompras parametroCompras;
   @Autowired
   private Parametros parametros;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(PresupuestoCompra presupuestoCompra, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("presupuestoCompra/presupuestoCompra");
      mv.addObject(presupuestoCompra);
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("monedas", this.monedas.findAll());
      this.agregarUUID(presupuestoCompra);
      this.agregarUsuario(presupuestoCompra, usuarioSistema);
      this.agregarDatosParametrizados(presupuestoCompra);
      mv.addObject("items", this.presupuestoSession.getItems(presupuestoCompra.getUuid()));
      mv.addObject("total", this.presupuestoSession.getTotal(presupuestoCompra.getUuid()));
      return mv;
   }

   private void agregarDatosParametrizados(PresupuestoCompra presupuestoCompra) {
      ParametroCompra parametroCompra = this.parametroCompras.getParametroCompra();
      Parametro parametro = this.parametros.getParametro();
      if (presupuestoCompra.getDeposito() == null) {
         presupuestoCompra.setDeposito(parametroCompra.getDeposito());
      }

      if (presupuestoCompra.getMoneda() == null) {
         presupuestoCompra.setMoneda(parametro.getMoneda());
      }
   }

   private void agregarUsuario(PresupuestoCompra presupuestoCompra, UsuarioSistema usuarioSistema) {
      if (presupuestoCompra.getUsuario() == null) {
         presupuestoCompra.setUsuario(usuarioSistema.getUsuario());
      }
   }

   private void agregarUUID(PresupuestoCompra presupuestoCompra) {
      if (StringUtils.isEmpty(presupuestoCompra.getUuid())) {
         presupuestoCompra.setUuid(UUID.randomUUID().toString());
      }
   }

   @PostMapping
   public ModelAndView guardar(
      @Valid PresupuestoCompra presupuestoCompra, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema
   ) {
      ModelAndView mv = new ModelAndView("redirect:/presupuestoCompras");
      presupuestoCompra.agregarItem(this.presupuestoSession.getItems(presupuestoCompra.getUuid()));
      if (result.hasErrors()) {
         return this.inicio(presupuestoCompra, usuarioSistema);
      } else {
         try {
            this.presupuestoService.guardar(presupuestoCompra);
         } catch (NegocioException var7) {
            result.rejectValue("", "", var7.getMessage());
            return this.inicio(presupuestoCompra, usuarioSistema);
         }

         attributes.addFlashAttribute("mensaje", "Presupuesto guardada con éxito, Nro: " + presupuestoCompra.getId());
         attributes.addFlashAttribute("registro", presupuestoCompra.getId());
         return mv;
      }
   }

   @GetMapping({"/{id}"})
   public ModelAndView retornaPorId(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      PresupuestoCompra presupuestoCompra = (PresupuestoCompra)this.presupuestos.findById(id).orElse(null);
      this.agregarUUID(presupuestoCompra);

      for (ItemPresupuestoCompra item : presupuestoCompra.getItems()) {
         this.presupuestoSession.adicionarItem(item.getProducto(), item.getCantidad(), item.getPrecio(), presupuestoCompra.getUuid());
      }

      return this.inicio(presupuestoCompra, usuarioSistema);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.presupuestoService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarPresupuesto(PresupuestoCompraFilter presupuestoCompraFilter) {
      ModelAndView mv = new ModelAndView("presupuestoCompra/buscarPresupuestoCompra");
      mv.addObject("presupuestos", this.presupuestos.getPresupuestoCompra(presupuestoCompraFilter));
      return mv;
   }

   @PostMapping({"/item/adicionar"})
   public ModelAndView adicionarItem(Producto producto, BigDecimal cantidad, BigDecimal precio, String uuid) {
      this.presupuestoSession.adicionarItem(producto, cantidad, precio, uuid);
      return this.mVItem(uuid);
   }

   @PutMapping({"/item/modificar/cantidad"})
   public ModelAndView modificarItem(Producto producto, BigDecimal cantidad, String uuid) {
      this.presupuestoSession.modificarCanidad(producto, cantidad, uuid);
      return this.mVItem(uuid);
   }

   @DeleteMapping({"/item/eliminar"})
   public ModelAndView eliminarItem(int indice, String uuid) {
      this.presupuestoSession.eliminarItem(indice, uuid);
      return this.mVItem(uuid);
   }

   private ModelAndView mVItem(String uuid) {
      ModelAndView mv = new ModelAndView("presupuestoCompra/itemPresupuestoCompra");
      mv.addObject("items", this.presupuestoSession.getItems(uuid));
      mv.addObject("total", this.presupuestoSession.getTotal(uuid));
      return mv;
   }

   @GetMapping({"/js/get"})
   @ResponseBody
   public List<PresupuestoCompra> getPresupuestojs(
      @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaDesde, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaHasta
   ) {
      return this.presupuestos.getPresupuestoByFechas(fechaDesde, fechaHasta);
   }

   @GetMapping({"/js/get/id"})
   @ResponseBody
   public PresupuestoCompra getPresupuestoByIdjs(Long id) {
      return (PresupuestoCompra)this.presupuestos.findById(id).orElse(null);
   }
}

package com.sige.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.model.ParametroVenta;
import com.sige.repository.Condiciones;
import com.sige.repository.Depositos;
import com.sige.repository.Monedas;
import com.sige.repository.ParametroVentas;
import com.sige.repository.Precios;
import com.sige.security.UsuarioSistema;

@Controller
@RequestMapping({"/parametroVentas"})
public class ParametroVentaController {
   @Autowired
   private Precios precios;
   @Autowired
   private Depositos depositos;
   @Autowired
   private Monedas monedas;
   @Autowired
   private ParametroVentas parametroVentas;
   @Autowired
   private Condiciones condiciones;

   @GetMapping
   public ModelAndView inicio(ParametroVenta parametroVenta, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("parametroVenta/parametroVenta");
      parametroVenta = this.agregarParametro();
      mv.addObject(parametroVenta);
      mv.addObject("precios", this.precios.findAll());
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject("condiciones", this.condiciones.findAll());
      parametroVenta.setUsuario(usuarioSistema.getUsuario());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(ParametroVenta parametroVenta, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/parametroVentas");
      this.parametroVentas.save(parametroVenta);
      attributes.addFlashAttribute("mensaje", "Prametro de venta guardado con éxito");
      return mv;
   }

   private ParametroVenta agregarParametro() {
      new ParametroVenta();

      ParametroVenta parametroVenta;
      try {
         parametroVenta = this.parametroVentas.buscarParametroVenta();
      } catch (Exception e) {
         parametroVenta = new ParametroVenta();
      }

      return parametroVenta;
   }

   @GetMapping({"/js/getParametroVenta"})
   @ResponseBody
   public ParametroVenta getParametro() {
      return this.parametroVentas.buscarParametroVenta();
   }
}

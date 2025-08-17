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

import com.sige.model.ParametroCompra;
import com.sige.repository.Depositos;
import com.sige.repository.Precios;
import com.sige.security.UsuarioSistema;
import com.sige.service.ParametroCompraService;

@Controller
@RequestMapping({"/parametroCompras"})
public class ParametroCompraController {
   @Autowired
   private ParametroCompraService parametroCompraService;
   @Autowired
   private Precios precios;
   @Autowired
   private Depositos depositos;

   @GetMapping
   public ModelAndView inicio(ParametroCompra parametroCompra, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("parametroCompra/parametroCompra");
      parametroCompra = this.agregarExistente();
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("precios", this.precios.findAll());
      parametroCompra.setUsuario(usuarioSistema.getUsuario());
      mv.addObject(parametroCompra);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(ParametroCompra parametroCompra, @AuthenticationPrincipal UsuarioSistema usuarioSistema, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/parametroCompras");
      this.parametroCompraService.guardar(parametroCompra);
      attributes.addFlashAttribute("mensaje", "Parámetro grabado con éxito !");
      return mv;
   }

   public ParametroCompra agregarExistente() {
      new ParametroCompra();

      ParametroCompra parametroCompra;
      try {
         parametroCompra = this.parametroCompraService.getParametroCompra();
      } catch (Exception var3) {
         parametroCompra = new ParametroCompra();
      }

      return parametroCompra;
   }

   @GetMapping({"/getParametroCompra"})
   @ResponseBody
   public ParametroCompra getParametroCompra() {
      new ParametroCompra();

      ParametroCompra parametroCompra;
      try {
         parametroCompra = this.parametroCompraService.getParametroCompra();
      } catch (Exception var3) {
         parametroCompra = new ParametroCompra();
      }

      return parametroCompra;
   }
}

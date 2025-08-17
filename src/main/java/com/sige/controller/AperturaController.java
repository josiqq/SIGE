package com.sige.controller;

import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.Valid;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.model.Apertura;
import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.Moneda;
import com.sige.security.UsuarioSistema;
import com.sige.session.TablaItemAperturaSession;

@Controller
@RequestMapping({"/aperturas"})
public class AperturaController {
   @Autowired
   private TablaItemAperturaSession session;

   @GetMapping
   public ModelAndView inicio(Apertura apertura, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("apertura/apertura");
      this.adicionarParametros(apertura, usuarioSistema);
      mv.addObject(apertura);
      mv.addObject("items", this.session.getItems(apertura.getUuid()));
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(
      @Valid Apertura apertura, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema
   ) {
      ModelAndView mv = new ModelAndView("redirect:/aperturas");
      apertura.adicionarItem(this.session.getItems(apertura.getUuid()));
      return result.hasErrors() ? this.inicio(apertura, usuarioSistema) : mv;
   }

   @PostMapping({"/item-adicionar"})
   public ModelAndView adicionarItem(Cuenta cuenta, Condicion condicion, Moneda moneda, BigDecimal importe, String uuid) {
      this.session.adicionarItem(cuenta, condicion, moneda, importe, uuid);
      return this.getMvItem(uuid);
   }

   @PutMapping({"/item-modificar"})
   public ModelAndView modificarImporte(Cuenta cuenta, Condicion condicion, Moneda moneda, BigDecimal importe, String uuid) {
      this.session.modificarImporte(cuenta, condicion, moneda, importe, uuid);
      return this.getMvItem(uuid);
   }

   @DeleteMapping({"/item-eliminar"})
   public ModelAndView eliminarItem(int indice, String uuid) {
      this.session.eliminarItem(indice, uuid);
      return this.getMvItem(uuid);
   }

   private void adicionarParametros(Apertura apertura, UsuarioSistema usuarioSistema) {
      if (StringUtils.isEmpty(apertura.getUuid())) {
         apertura.setUuid(UUID.randomUUID().toString());
      }

      if (apertura.getUsuario() == null) {
         apertura.setUsuario(usuarioSistema.getUsuario());
      }
   }

   private ModelAndView getMvItem(String uuid) {
      ModelAndView mv = new ModelAndView("apertura/itemApertura");
      mv.addObject("items", this.session.getItems(uuid));
      return mv;
   }
}

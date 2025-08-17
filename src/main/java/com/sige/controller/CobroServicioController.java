package com.sige.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.model.CobroServicio;
import com.sige.repository.Cuentas;
import com.sige.repository.Vendedores;
import com.sige.repository.filter.CobroServicioFilter;
import com.sige.service.CobroServicioService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/cobroServicios"})
public class CobroServicioController {
   @Autowired
   private Cuentas cuentas;
   @Autowired
   private Vendedores vendedores;
   @Autowired
   private CobroServicioService cobroServicioService;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(CobroServicio cobroServicio) {
      ModelAndView mv = new ModelAndView("cobroServicio/cobroServicio");
      mv.addObject(cobroServicio);
      mv.addObject("cuentas", this.cuentas.findAll());
      mv.addObject("vendedores", this.vendedores.findAll());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid CobroServicio cobroServicio, BindingResult result, RedirectAttributes attribute) {
      ModelAndView mv = new ModelAndView("redirect:/cobroServicios");
      if (result.hasErrors()) {
         return this.inicio(cobroServicio);
      } else {
         try {
            this.cobroServicioService.guardar(cobroServicio);
            attribute.addFlashAttribute("mensaje", "Los datos se han guardado con exito!");
            this.cobroServicioService.imprimirServicio(cobroServicio);
            return mv;
         } catch (NegocioException var6) {
            result.rejectValue("valor", var6.getMessage(), var6.getMessage());
            return this.inicio(cobroServicio);
         }
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(CobroServicioFilter cobroServicioFilter) {
      ModelAndView mv = new ModelAndView("cobroServicio/buscarCobroServicio");
      mv.addObject("cobroServicios", this.cobroServicioService.buscarCobroSercicio(cobroServicioFilter));
      BigDecimal total;
      if (this.cobroServicioService.totalImporte(cobroServicioFilter) != null) {
         total = this.cobroServicioService.totalImporte(cobroServicioFilter).setScale(0, RoundingMode.HALF_UP);
      } else {
         total = BigDecimal.ZERO;
      }

      mv.addObject("total", total);
      mv.addObject("vendedores", this.vendedores.findAll());
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      CobroServicio cobroServicio = this.cobroServicioService.buscarPorId(id);
      cobroServicio.setSaldo(cobroServicio.getSaldo().setScale(0, RoundingMode.HALF_UP));
      cobroServicio.setValor(cobroServicio.getValor().setScale(0, RoundingMode.HALF_UP));
      cobroServicio.setImporte(cobroServicio.getImporte().setScale(0, RoundingMode.HALF_UP));
      return this.inicio(cobroServicio);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.cobroServicioService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}

package com.meta.controller;

import com.meta.config.BigDecimalEditor;
import com.meta.config.IntegerEditor;
import com.meta.modelo.AnticipoCliente;
import com.meta.repository.Cuentas;
import com.meta.repository.filter.AnticipoClienteFilter;
import com.meta.service.AnticipoClienteService;
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

@Controller
@RequestMapping({"/anticipoClientes"})
public class AnticipoClienteController {
   @Autowired
   private AnticipoClienteService anticipoClienteService;
   @Autowired
   private Cuentas cuentas;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(AnticipoCliente anticipoCliente) {
      ModelAndView mv = new ModelAndView("anticipoCliente/anticipoCliente");
      mv.addObject(anticipoCliente);
      mv.addObject("cuentas", this.cuentas.findAll());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid AnticipoCliente anticipoCliente, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/anticipoClientes");
      if (result.hasErrors()) {
         return this.inicio(anticipoCliente);
      } else {
         this.anticipoClienteService.guardar(anticipoCliente);
         attributes.addFlashAttribute("mensaje", "Los datos se han guardado con éxito!");
         return mv;
      }
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      AnticipoCliente anticipoCliente = this.anticipoClienteService.buscarPorid(id);
      anticipoCliente.setImporte(anticipoCliente.getImporte().setScale(0, RoundingMode.HALF_UP));
      return this.inicio(anticipoCliente);
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(AnticipoClienteFilter anticipoClienteFilter) {
      ModelAndView mv = new ModelAndView("anticipoCliente/buscarAnticipoCliente");
      mv.addObject(anticipoClienteFilter);
      mv.addObject("cuentas", this.cuentas.findAll());
      mv.addObject("total", this.anticipoClienteService.totalImporte(anticipoClienteFilter));
      mv.addObject("anticipoClientes", this.anticipoClienteService.buscarAnticpo(anticipoClienteFilter));
      return mv;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.anticipoClienteService.eliminar(id);
      } catch (Exception var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}

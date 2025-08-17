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
import com.sige.model.Cuenta;
import com.sige.repository.Monedas;
import com.sige.repository.Timbrados;
import com.sige.service.CuentaService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/cuentas"})
public class CuentaController {
   @Autowired
   private CuentaService cuentaService;
   @Autowired
   private Timbrados timbrados;
   @Autowired
   private Monedas monedas;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Cuenta cuenta) {
      ModelAndView mv = new ModelAndView("cuentas/cuentas");
      cuenta.setSaldo(this.quitarDecimal(cuenta.getSaldo()));
      mv.addObject(cuenta);
      mv.addObject("timbrados", this.timbrados.getTimbradosActivos());
      mv.addObject("monedas", this.monedas.findAll());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Cuenta cuenta, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/cuentas");
      if (result.hasErrors()) {
         return this.inicio(cuenta);
      } else {
         try {
            this.cuentaService.guardar(cuenta);
         } catch (Exception var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(cuenta);
         }

         attributes.addFlashAttribute("mensaje", "Cuenta se ha guardado con exito!!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(Cuenta cuenta) {
      ModelAndView mv = new ModelAndView("cuentas/buscarCuentas");
      mv.addObject("cuentas", this.cuentaService.buscar());
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      return this.inicio(this.cuentaService.buscarPorId(id));
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
      try {
         this.cuentaService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   private BigDecimal quitarDecimal(BigDecimal saldo) {
      return saldo.setScale(0, RoundingMode.HALF_UP);
   }
}

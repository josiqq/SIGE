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
import com.sige.model.PagoVendedor;
import com.sige.repository.Cuentas;
import com.sige.repository.Vendedores;
import com.sige.repository.filter.PagoVendedorFilter;
import com.sige.service.PagoVendedorService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/pagoVendedores"})
public class PagoVendedorController {
   @Autowired
   private PagoVendedorService pagoVendedorService;
   @Autowired
   private Vendedores vendedores;
   @Autowired
   private Cuentas cuentas;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(PagoVendedor pagoVendedor) {
      ModelAndView mv = new ModelAndView("pagoVendedor/pagoVendedor");
      mv.addObject(pagoVendedor);
      mv.addObject("vendedores", this.vendedores.findAll());
      mv.addObject("cuentas", this.cuentas.findAll());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid PagoVendedor pagoVendedor, BindingResult result, RedirectAttributes attribute) {
      ModelAndView mv = new ModelAndView("redirect:/pagoVendedores");
      if (result.hasErrors()) {
         return this.inicio(pagoVendedor);
      } else {
         try {
            this.pagoVendedorService.guardar(pagoVendedor);
         } catch (NegocioException var6) {
            result.rejectValue("importe", var6.getMessage(), var6.getMessage());
            return this.inicio(pagoVendedor);
         }

         attribute.addFlashAttribute("mensaje", "Pago registrado con exito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarPago(PagoVendedorFilter pagoVendedorFilter) {
      ModelAndView mv = new ModelAndView("pagoVendedor/buscarPagoVendedor");
      mv.addObject(pagoVendedorFilter);
      mv.addObject("vendedores", this.vendedores.findAll());
      mv.addObject("cuentas", this.cuentas.findAll());
      mv.addObject("total", this.pagoVendedorService.totalPagoVendedor(pagoVendedorFilter));
      mv.addObject("pagoVendedores", this.pagoVendedorService.buscarPagoVendedor(pagoVendedorFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      PagoVendedor pagoVendedor = this.pagoVendedorService.buscarPorId(id);
      pagoVendedor.setImporte(pagoVendedor.getImporte().setScale(0, RoundingMode.DOWN));
      return this.inicio(pagoVendedor);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> borrar(@PathVariable Long id) {
      try {
         this.pagoVendedorService.borrar(id);
      } catch (Exception var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}

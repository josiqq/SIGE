package com.sige.controller;

import java.math.BigDecimal;
import java.util.List;
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
import com.sige.model.Vendedor;
import com.sige.repository.filter.VendedorFilter;
import com.sige.service.VendedorService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/vendedores"})
public class VendedorController {
   @Autowired
   private VendedorService vendedorService;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Vendedor vendedor) {
      ModelAndView mv = new ModelAndView("vendedor/vendedor");
      mv.addObject(vendedor);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Vendedor vendedor, BindingResult result, RedirectAttributes attribute) {
      ModelAndView mv = new ModelAndView("redirect:/vendedores");
      if (result.hasErrors()) {
         return this.inicio(vendedor);
      } else {
         try {
            this.vendedorService.guardar(vendedor);
         } catch (NegocioException var6) {
            result.rejectValue("documento", var6.getMessage(), var6.getMessage());
            return this.inicio(vendedor);
         }

         attribute.addFlashAttribute("mensaje", "Vendedor guardado con éxito");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(VendedorFilter vendedorFilter) {
      ModelAndView mv = new ModelAndView("vendedor/buscarVendedor");
      mv.addObject("vendedores", this.vendedorService.buscaVendedor(vendedorFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView recuperarPorId(@PathVariable Long id) {
      Vendedor vendedor = this.vendedorService.buscarPorId(id);
      return this.inicio(vendedor);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.vendedorService.eliminar(id);
      } catch (Exception var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/buscarSaldo/{id}"})
   @ResponseBody
   public BigDecimal buscarSaldo(@PathVariable Long id) {
      return this.vendedorService.buscarSaldo(id);
   }

   @GetMapping({"/js/getVendedorSupervisor"})
   @ResponseBody
   public List<Vendedor> getVendedorSupervisor() {
      return this.vendedorService.getVendedorSupervidor();
   }
}

package com.sige.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.model.Condicion;
import com.sige.model.CondicionCobro;
import com.sige.repository.Condiciones;
import com.sige.service.CondicionService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/condiciones"})
public class CondicionController {
   @Autowired
   private CondicionService condicionService;
   @Autowired
   private Condiciones condiciones;

   @GetMapping
   public ModelAndView inicio(Condicion condicion) {
      ModelAndView mv = new ModelAndView("condicion/condicion");
      mv.addObject(condicion);
      mv.addObject("condicionesCobros", CondicionCobro.values());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Condicion condicion, BindingResult result, RedirectAttributes attribute) {
      ModelAndView mv = new ModelAndView("redirect:/condiciones");
      if (result.hasErrors()) {
         return this.inicio(condicion);
      } else {
         try {
            this.condicionService.guardar(condicion);
            attribute.addFlashAttribute("mensaje", "Condicion guardada correctamente!");
            return mv;
         } catch (Exception var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(condicion);
         }
      }
   }

   @GetMapping({"/{id}"})
   public ModelAndView getById(@PathVariable Long id) {
      Condicion condicion = this.condicionService.getById(id);
      return this.inicio(condicion);
   }

   @GetMapping({"/buscar"})
   public ModelAndView getAll() {
      ModelAndView mv = new ModelAndView("condicion/buscarCondicion");
      mv.addObject("condiciones", this.condicionService.getAllCondiciones());
      return mv;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.condicionService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/js/condiciones"})
   @ResponseBody
   public List<Condicion> getCondiciones() {
      return this.condicionService.getAllCondiciones();
   }

   @GetMapping({"/condicionPorId"})
   @ResponseBody
   public Condicion getCondicionById(Long id) {
      return this.condiciones.getCondicionById(id);
   }

   @GetMapping({"/getCondicion/distinto/efectivo"})
   @ResponseBody
   public List<Condicion> getCondicionDistintoEfectivo() {
      return this.condiciones.getCondicionDistintoEfectivo();
   }
}

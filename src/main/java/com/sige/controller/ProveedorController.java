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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.model.Proveedor;
import com.sige.service.ProveedorService;
import com.sige.service.exeption.ImposibleEliminarExeption;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/proveedores"})
public class ProveedorController {
   @Autowired
   private ProveedorService proveedorService;

   @GetMapping
   public ModelAndView inicio(Proveedor proveedor) {
      ModelAndView mv = new ModelAndView("proveedor/proveedor");
      mv.addObject(proveedor);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Proveedor proveedor, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/proveedores");
      if (result.hasErrors()) {
         return this.inicio(proveedor);
      } else {
         try {
            this.proveedorService.guardar(proveedor);
         } catch (NegocioException var6) {
            result.rejectValue("documento", var6.getMessage(), var6.getMessage());
            return this.inicio(proveedor);
         }

         attributes.addFlashAttribute("mensaje", "Los datos se han guardado con exito !");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar() {
      ModelAndView mv = new ModelAndView("proveedor/buscarProveedor");
      mv.addObject("proveedores", this.proveedorService.buscar());
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      return this.inicio(this.proveedorService.buscarPorId(id));
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable("id") long id) {
      try {
         this.proveedorService.eliminar(id);
      } catch (ImposibleEliminarExeption var4) {
         return ResponseEntity.badRequest().body(var4.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @RequestMapping(
      consumes = {"application/json"}
   )
   @ResponseBody
   public List<Proveedor> proveedoresFiltradas(String nombreDocumento) {
      return this.proveedorService.buscarPorNombreDocumento(nombreDocumento);
   }

   @PostMapping({"/cargar/modal"})
   @ResponseBody
   public ResponseEntity<?> cargaRapida(@RequestBody @Valid Proveedor proveedor, BindingResult result) {
      if (result.hasErrors()) {
         return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
      } else {
         try {
            this.proveedorService.guardar(proveedor);
         } catch (NegocioException var4) {
            return ResponseEntity.badRequest().body(var4.getMessage());
         }

         return ResponseEntity.ok(proveedor);
      }
   }
}

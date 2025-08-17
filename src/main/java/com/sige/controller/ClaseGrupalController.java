package com.sige.controller;

import java.util.UUID;
import javax.validation.Valid;
import org.apache.groovy.parser.antlr4.util.StringUtils;
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

import com.sige.model.ClaseGrupal;
import com.sige.model.Cliente;
import com.sige.model.ItemClaseGrupal;
import com.sige.repository.Instructores;
import com.sige.service.ClaseGrupalService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemClaseGrupalSession;

@Controller
@RequestMapping({"/clasegrupales"})
public class ClaseGrupalController {
   @Autowired
   private Instructores instructores;
   @Autowired
   private TablaItemClaseGrupalSession tablaItemClaseGrupalSession;
   @Autowired
   private ClaseGrupalService claseGrupalService;

   @GetMapping
   public ModelAndView inicio(ClaseGrupal claseGrupal) {
      ModelAndView mv = new ModelAndView("claseGrupal/claseGrupal");
      this.adicionarUUID(claseGrupal);
      mv.addObject(claseGrupal);
      mv.addObject("instructores", this.instructores.findAll());
      mv.addObject("items", this.tablaItemClaseGrupalSession.getItems(claseGrupal.getUuid()));
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid ClaseGrupal claseGrupal, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/clasegrupales");
      if (result.hasErrors()) {
         return this.inicio(claseGrupal);
      } else {
         claseGrupal.cargarCalseGrupal(this.tablaItemClaseGrupalSession.getItems(claseGrupal.getUuid()));

         try {
            this.claseGrupalService.guardar(claseGrupal);
         } catch (NegocioException var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(claseGrupal);
         }

         attributes.addFlashAttribute("mensaje", "Clase grupal se guardó con éxito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarClase(ClaseGrupal claseGrupal) {
      ModelAndView mv = new ModelAndView("claseGrupal/buscarClaseGrupal");
      mv.addObject("claseGrupales", this.claseGrupalService.listaClases(claseGrupal));
      mv.addObject("instructores", this.instructores.findAll());
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      ClaseGrupal claseGrupal = this.claseGrupalService.buscarPorId(id);
      this.adicionarUUID(claseGrupal);

      for (ItemClaseGrupal item : claseGrupal.getItems()) {
         this.tablaItemClaseGrupalSession.adicionarItem(item.getCliente(), claseGrupal.getUuid());
      }

      return this.inicio(claseGrupal);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.claseGrupalService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   private void adicionarUUID(ClaseGrupal claseGrupal) {
      if (StringUtils.isEmpty(claseGrupal.getUuid())) {
         claseGrupal.setUuid(UUID.randomUUID().toString());
      }
   }

   @PostMapping({"/item"})
   @ResponseBody
   public ModelAndView adicionarItem(Cliente cliente, String uuid) {
      ModelAndView mv = new ModelAndView("claseGrupal/itemClaseGrupal");
      this.tablaItemClaseGrupalSession.adicionarItem(cliente, uuid);
      mv.addObject("items", this.tablaItemClaseGrupalSession.getItems(uuid));
      return mv;
   }

   @DeleteMapping({"/item/eliminar"})
   @ResponseBody
   public ModelAndView eliminarItem(Cliente cliente, String uuid) {
      ModelAndView mv = new ModelAndView("claseGrupal/itemClaseGrupal");
      this.tablaItemClaseGrupalSession.eliminarItem(cliente, uuid);
      mv.addObject("items", this.tablaItemClaseGrupalSession.getItems(uuid));
      return mv;
   }
}

package com.sige.controller;

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

import com.sige.model.Usuario;
import com.sige.repository.Cajeros;
import com.sige.repository.Cuentas;
import com.sige.repository.Grupos;
import com.sige.repository.Instructores;
import com.sige.repository.Vendedores;
import com.sige.service.UsuarioService;

@Controller
@RequestMapping({"/usuarios"})
public class UsuarioController {
   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private Grupos grupos;
   @Autowired
   private Instructores instructores;
   @Autowired
   private Vendedores vendedores;
   @Autowired
   private Cajeros cajeros;
   @Autowired
   private Cuentas cuentas;

   @GetMapping
   public ModelAndView inicio(Usuario usuario) {
      ModelAndView mv = new ModelAndView("usuario/usuario");
      mv.addObject(usuario);
      mv.addObject("instructores", this.instructores.findAll());
      mv.addObject("grupos", this.grupos.findAll());
      mv.addObject("vendedores", this.vendedores.buscarVendedoresAactivos());
      mv.addObject("cajeros", this.cajeros.buscarCajerosActivos());
      mv.addObject("cuentas", this.cuentas.buscarCuentasActivas());
      return mv;
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(Usuario usuario) {
      ModelAndView mv = new ModelAndView("usuario/buscarUsuario");
      mv.addObject("usuarios", this.usuarioService.buscarPorNombre(usuario));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      Usuario usuario = this.usuarioService.buscarPorId(id);
      return this.inicio(usuario);
   }

   @GetMapping({"/buscar/{nombre}"})
   public ModelAndView buscarNombre(@PathVariable String nombre) {
      ModelAndView mv = new ModelAndView("usuario/cambiarPassword");
      Usuario usuario = this.usuarioService.buscarUsuario(nombre);
      mv.addObject(usuario);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/usuarios");
      if (result.hasErrors()) {
         return this.inicio(usuario);
      } else {
         try {
            this.usuarioService.guardar(usuario);
         } catch (Exception var6) {
            result.rejectValue("nombre", var6.getMessage(), var6.getMessage());
            return this.inicio(usuario);
         }

         attributes.addFlashAttribute("mensaje", "Usuario guardado con exito");
         return mv;
      }
   }

   @PostMapping({"/buscar/{nombre}"})
   public ModelAndView userModifica(Usuario usuario, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/usuarios/buscar/{nombre}");
      this.usuarioService.guardar(usuario);
      attributes.addFlashAttribute("mensaje", "Password modificada con exito!");
      return mv;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.usuarioService.eliminar(id);
      } catch (Exception var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}

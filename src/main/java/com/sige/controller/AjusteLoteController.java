package com.sige.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.model.AjusteLote;
import com.sige.model.ItemAjusteLote;
import com.sige.model.Producto;
import com.sige.repository.AjusteLotes;
import com.sige.repository.Depositos;
import com.sige.repository.ItemAjusteLotes;
import com.sige.repository.filter.AjusteLoteFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.AjusteLoteService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemAjusteLoteSession;

@Controller
@RequestMapping({"/ajuste-lote"})
public class AjusteLoteController {
   @Autowired
   private AjusteLotes ajusteLotes;
   @Autowired
   private AjusteLoteService ajusteLoteService;
   @Autowired
   private TablaItemAjusteLoteSession session;
   @Autowired
   private Depositos depositos;
   @Autowired
   private ItemAjusteLotes itemAjusteLotes;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(AjusteLote ajusteLote, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("ajusteLote/ajusteLote");
      this.agregarUUID(ajusteLote, usuarioSistema);
      mv.addObject(ajusteLote);
      mv.addObject("items", this.session.getItems(ajusteLote.getUuid()));
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(
      @Valid AjusteLote ajusteLote, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema
   ) {
      ModelAndView mv = new ModelAndView("redirect:/ajuste-lote");
      ajusteLote.agregarItem(this.session.getItems(ajusteLote.getUuid()));
      if (result.hasErrors()) {
         return this.inicio(ajusteLote, usuarioSistema);
      } else {
         try {
            this.ajusteLoteService.guardar(ajusteLote);
         } catch (NegocioException var9) {
            result.rejectValue("", "", var9.getMessage());
            return this.inicio(ajusteLote, usuarioSistema);
         } catch (DataAccessException var10) {
            Throwable rootCause = var10.getRootCause();
            String errorMessage = "Error en la base de datos: " + rootCause.getMessage();
            result.rejectValue("", "", errorMessage);
            return this.inicio(ajusteLote, usuarioSistema);
         }

         attributes.addFlashAttribute("mensaje", "Ajuste guardada con éxito! " + ajusteLote.getId());
         return mv;
      }
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      AjusteLote ajusteLote = this.ajusteLotes.findById(id).orElse(new AjusteLote());
      this.agregarUUID(ajusteLote, usuarioSistema);

      for (ItemAjusteLote item : ajusteLote.getItems()) {
         this.session.adicionarItem(item.getProducto(), item.getNroLote(), item.getCantidad(), item.getVencimiento(), ajusteLote.getUuid());
      }

      return this.inicio(ajusteLote, usuarioSistema);
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(AjusteLoteFilter filter) {
      ModelAndView mv = new ModelAndView("ajusteLote/buscarAjusteLote");
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("ajustes", this.ajusteLotes.getAjusteLotes(filter));
      mv.addObject(filter);
      return mv;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.ajusteLoteService.eliminar(id);
      } catch (NegocioException var3) {
         ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @PostMapping({"/item-adicionar"})
   public ModelAndView adicionarItem(
      Producto producto, String nroLote, BigDecimal cantidad, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate vencimiento, String uuid
   ) {
      this.session.adicionarItem(producto, nroLote, cantidad, vencimiento, uuid);
      return this.getMv(uuid);
   }

   @PutMapping({"/item-modificar-cantidad"})
   public ModelAndView modificarCantidad(Producto producto, String nroLote, BigDecimal cantidad, String uuid) {
      this.session.modificarCantidad(producto, nroLote, cantidad, uuid);
      return this.getMv(uuid);
   }

   @PutMapping({"/item-modificar-vencimiento"})
   public ModelAndView modificarVencimiento(Producto producto, String nroLote, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate vencimiento, String uuid) {
      this.session.modificarVencimiento(producto, nroLote, vencimiento, uuid);
      return this.getMv(uuid);
   }

   @DeleteMapping({"/item-eliminar"})
   public ModelAndView eliminarItem(int indice, String uuid) {
      this.session.eliminarItem(indice, uuid);
      return this.getMv(uuid);
   }

   @GetMapping({"/buscar-item-ajuste-lote-por-ajuste-id"})
   @ResponseBody
   public List<ItemAjusteLote> getItemAjusteLotesPorIdAjuste(Long idAjusteLote) {
      return this.itemAjusteLotes.getItemsLotes(idAjusteLote);
   }

   private void agregarUUID(AjusteLote ajusteLote, UsuarioSistema usuarioSistema) {
      if (StringUtils.isEmpty(ajusteLote.getUuid())) {
         ajusteLote.setUuid(UUID.randomUUID().toString());
      }

      if (ajusteLote.getUsuario() == null) {
         ajusteLote.setUsuario(usuarioSistema.getUsuario());
      }
   }

   private ModelAndView getMv(String uuid) {
      ModelAndView mv = new ModelAndView("ajusteLote/itemAjusteLote");
      mv.addObject("items", this.session.getItems(uuid));
      return mv;
   }
}

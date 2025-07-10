package com.meta.controller;

import com.meta.config.BigDecimalEditor;
import com.meta.config.IntegerEditor;
import com.meta.dto.ClienteConsorcioDTO;
import com.meta.modelo.Cliente;
import com.meta.modelo.Consorcio;
import com.meta.modelo.Cuenta;
import com.meta.modelo.ItemConsorcio;
import com.meta.modelo.ItemConsorcioImporte;
import com.meta.repository.Consorcios;
import com.meta.repository.Cuentas;
import com.meta.repository.ItemConsorcioImportes;
import com.meta.security.UsuarioSistema;
import com.meta.service.ConsorcioService;
import com.meta.service.exeption.NegocioException;
import com.meta.session.TablaItemConsorcioImporteSession;
import com.meta.session.TablaItemConsorcioSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping({"/consorcios"})
public class ConsorcioController {
   @Autowired
   private TablaItemConsorcioSession tablaItemConsorcioSession;
   @Autowired
   private TablaItemConsorcioImporteSession tablaItemConsorcioImporteSession;
   @Autowired
   private ConsorcioService consorcioService;
   @Autowired
   private Consorcios consorcios;
   @Autowired
   private ItemConsorcioImportes itemConsorcioImportes;
   @Autowired
   private Cuentas cuentas;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Consorcio consorcio, @AuthenticationPrincipal UsuarioSistema usuarioSsitema) {
      ModelAndView mv = new ModelAndView("consorcio/consorcio");
      consorcio.setMontoMensual(this.quitarDecimal(consorcio.getMontoMensual()));
      this.agregarUuid(consorcio);
      mv.addObject(consorcio);
      mv.addObject("items", this.tablaItemConsorcioSession.getItems(consorcio.getUuid()));
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(
      @Valid Consorcio consorcio, BindingResult result, @AuthenticationPrincipal UsuarioSistema usuarioSistema, RedirectAttributes attribute
   ) {
      ModelAndView mv = new ModelAndView("redirect:/consorcios");
      if (result.hasErrors()) {
         return this.inicio(consorcio, usuarioSistema);
      } else {
         consorcio.agregarItems(this.tablaItemConsorcioSession.getItems(consorcio.getUuid()));

         for (ItemConsorcio itemConsorcio : this.tablaItemConsorcioSession.getItems(consorcio.getUuid())) {
            LocalDate fecha = consorcio.getFechaInicio();
            String uuid = UUID.randomUUID().toString();

            for (int i = 0; i < itemConsorcio.getMeses(); i++) {
               if (i == 0) {
                  fecha = consorcio.getFechaInicio();
               } else {
                  fecha = fecha.plusMonths(1L);
               }

               this.tablaItemConsorcioImporteSession
                  .adicionarItem(fecha, itemConsorcio.getMonto(), BigDecimal.ZERO, itemConsorcio.getMonto().subtract(BigDecimal.ZERO), uuid);
            }

            itemConsorcio.agregarItemImporte(this.tablaItemConsorcioImporteSession.getItems(uuid));
         }

         try {
            this.consorcioService.guardar(consorcio);
         } catch (NegocioException var11) {
            result.rejectValue("", "", var11.getMessage());
            return this.inicio(consorcio, usuarioSistema);
         }

         attribute.addFlashAttribute("mensaje", "Consorcio nro:" + consorcio.getId() + " guardado con éxito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(Consorcio consorcio) {
      ModelAndView mv = new ModelAndView("consorcio/buscarConsorcio");
      mv.addObject("consorcios", this.consorcioService.getConsorcios(consorcio));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView getById(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      Consorcio consorcio = this.consorcioService.gitById(id);
      this.agregarUuid(consorcio);

      for (ItemConsorcio item : consorcio.getItems()) {
         this.tablaItemConsorcioSession.adicionarItem(item.getCliente(), this.quitarDecimal(item.getMonto()), item.getMeses(), consorcio.getUuid());
      }

      return this.inicio(consorcio, usuarioSistema);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.consorcioService.eliminar(id);
      } catch (NegocioException var3) {
         ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/cobrar"})
   public ModelAndView cobrar(@AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("consorcio/cobroConsorcio");
      mv.addObject("cuentas", this.cuentas.buscarCuentasActivas());
      mv.addObject("consorcios", this.consorcios.findAll());
      System.out.println("cuenta de usuario: " + usuarioSistema.getUsuario().getCuenta() != null ? usuarioSistema.getUsuario().getCuenta().getId() : "");
      mv.addObject("idCuenta", usuarioSistema.getUsuario().getCuenta() != null ? usuarioSistema.getUsuario().getCuenta().getId() : "");
      return mv;
   }

   @PostMapping({"/item"})
   public ModelAndView adicionarItem(Cliente cliente, BigDecimal monto, Integer meses, String uuid) {
      this.tablaItemConsorcioSession.adicionarItem(cliente, this.quitarDecimal(monto), meses, uuid);
      return this.mvItem(uuid);
   }

   @DeleteMapping({"/item/eliminar"})
   public ModelAndView eliminarItem(Cliente cliente, String uuid) {
      this.tablaItemConsorcioSession.eliminarItem(cliente, uuid);
      return this.mvItem(uuid);
   }

   @GetMapping({"/itemConsorcioImporte/lista"})
   @ResponseBody
   public List<ItemConsorcioImporte> getItemConsorcioImporteByCliente(Cliente cliente, Consorcio consorcio) {
      return this.itemConsorcioImportes.getItemConsorcioImporteByCliente(cliente, consorcio);
   }

   @PutMapping({"/cobro/consorcio/importe"})
   @ResponseBody
   public ResponseEntity<?> updateItemConsorcio(Long id_consorcio, BigDecimal montoCobrado, BigDecimal saldo, Cuenta cuenta) {
      this.itemConsorcioImportes.updateImporte(id_consorcio, montoCobrado, saldo, cuenta);
      return ResponseEntity.ok().build();
   }

   @GetMapping({"/js/clienteConsorcio"})
   @ResponseBody
   public List<ClienteConsorcioDTO> getClienteConsorcio(Long consorcio, String cliente) {
      return this.consorcios.getItemConsorcioByClienteConsorcio(consorcio, cliente);
   }

   private ModelAndView mvItem(String uuid) {
      ModelAndView mv = new ModelAndView("consorcio/itemConsorcio");
      mv.addObject("items", this.tablaItemConsorcioSession.getItems(uuid));
      return mv;
   }

   private BigDecimal quitarDecimal(BigDecimal valor) {
      return valor.setScale(0, RoundingMode.HALF_UP);
   }

   private void agregarUuid(Consorcio consorcio) {
      if (StringUtils.isEmpty(consorcio.getUuid())) {
         consorcio.setUuid(UUID.randomUUID().toString());
      }
   }
}

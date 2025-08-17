package com.sige.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.model.Cliente;
import com.sige.repository.Clientes;
import com.sige.repository.Precios;
import com.sige.repository.filter.ClienteFilter;
import com.sige.service.ClienteService;
import com.sige.service.exeption.ImposibleEliminarExeption;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/clientes"})
public class ClienteController {
   @Autowired
   private ClienteService clienteService;
   @Autowired
   private Precios precios;
   @Autowired
   private Clientes clientes;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Cliente cliente) {
      ModelAndView mv = new ModelAndView("clientes/cliente");
      mv.addObject(cliente);
      mv.addObject("precios", this.precios.findAll());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/clientes");
      if (result.hasErrors()) {
         return this.inicio(cliente);
      } else {
         try {
            this.clienteService.guardar(cliente);
         } catch (NegocioException var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(cliente);
         }

         attributes.addFlashAttribute("mensaje", "Cliente se guardó con éxito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(ClienteFilter clienteFilter) {
      ModelAndView mv = new ModelAndView("clientes/buscarCliente");
      mv.addObject("clientes", this.clienteService.buscarCliente(clienteFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      Cliente cliente = this.clienteService.buscarPorId(id);
      cliente.setTarifa(this.quitarDecimal(cliente.getTarifa()));
      cliente.setSaldo(this.quitarDecimal(cliente.getSaldo()));
      return this.inicio(cliente);
   }

   @RequestMapping(
      consumes = {"application/json"}
   )
   @ResponseBody
   public List<Cliente> clientesFiltradas(String nombreDocumento) {
      return this.clienteService.buscarPorNombreODocumento(nombreDocumento);
   }

   @GetMapping({"/js/clientes"})
   @ResponseBody
   public List<Cliente> buscarJs(String nombreDocumento) {
      return this.clienteService.buscarPorNombreODocumento(nombreDocumento);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable("id") Cliente cliente) {
      try {
         this.clienteService.eliminar(cliente);
      } catch (ImposibleEliminarExeption var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/buscarSaldo"})
   @ResponseBody
   public BigDecimal buscarSaldo(Long id) {
      System.out.println("llego el id?=>" + id);
      return this.clienteService.buscarSaldo(id);
   }

   public BigDecimal quitarDecimal(BigDecimal valor) {
      return valor.setScale(0, RoundingMode.HALF_UP);
   }

   @PostMapping({"/cargar/modal"})
   @ResponseBody
   public ResponseEntity<?> gargaRaapida(@RequestBody @Valid Cliente cliente, BindingResult result) {
      if (result.hasErrors()) {
         return ResponseEntity.badRequest().body(result.getFieldError("nombre").getDefaultMessage());
      } else {
         try {
            this.clienteService.guardar(cliente);
         } catch (NegocioException var4) {
            return ResponseEntity.badRequest().body(var4.getMessage());
         }

         return ResponseEntity.ok(cliente);
      }
   }

   @GetMapping({"/js/getClientes"})
   @ResponseBody
   public List<Cliente> getCientesByNombreDocumento(String nombreDocumento) {
      return this.clientes.getClientesByNombreDocumento(nombreDocumento);
   }

   @GetMapping({"/js/getClienteById"})
   @ResponseBody
   public Cliente getClienteById(Long id) {
      return this.clientes.findById(id).orElse(new Cliente());
   }
}

package com.sige.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.sige.model.Agenda;
import com.sige.model.Cliente;
import com.sige.model.Estado;
import com.sige.model.ItemAgenda;
import com.sige.model.Parametro;
import com.sige.model.Vendedor;
import com.sige.repository.Agendas;
import com.sige.repository.ItemAgendas;
import com.sige.repository.Parametros;
import com.sige.repository.Vendedores;
import com.sige.service.AgendaService;
import com.sige.session.TablaItemAgendaSession;

@Controller
@RequestMapping({"/agendas"})
public class AgendaController {
   @Autowired
   private Vendedores vendedores;
   @Autowired
   private AgendaService agendaService;
   @Autowired
   private TablaItemAgendaSession tablaItemAgendaSession;
   @Autowired
   private Agendas agendas;
   @Autowired
   private Parametros parametros;
   @Autowired
   private ItemAgendas itemAgendas;

   @GetMapping
   public ModelAndView inicio(Agenda agenda) {
      ModelAndView mv = new ModelAndView("agenda/agenda");
      this.agregarUUID(agenda);
      mv.addObject(agenda);
      mv.addObject("vendedores", this.vendedores.findAll());
      return mv;
   }

   @PutMapping({"/item/modificar/primero"})
   @ResponseBody
   public String modificarEstadoPrimero(
      Long itemId, @DateTimeFormat(pattern = "HH:mm") LocalTime hora, Cliente cliente, String observacion, String UUID, Estado estado
   ) {
      this.itemAgendas.modificarItem(itemId, cliente, estado, observacion);
      this.tablaItemAgendaSession.modificarItem(hora, cliente, observacion, estado, UUID);
      return "ok_modificado";
   }

   @PostMapping
   public ModelAndView guardar(Agenda agenda) {
      ModelAndView mv = new ModelAndView("redirect:/agendas");
      agenda.cargarItems(this.tablaItemAgendaSession.getIems(agenda.getUuid()));
      this.agendaService.guardar(agenda);
      return mv;
   }

   @GetMapping({"/js/agendaId"})
   @ResponseBody
   public Optional<Long> buscarIdAgenda(Vendedor vendedor, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, String uuid) {
      try {
         return this.agendas.buscarIdAgenda(vendedor, fecha);
      } catch (Exception var10) {
         Agenda agenda = new Agenda();
         agenda.setUuid(uuid);
         agenda.setFecha(fecha);
         agenda.setVendedor(vendedor);
         Parametro parametro = this.parametros.recuperarParametro();
         LocalTime iterHora = parametro.getInterHora();
         LocalTime horaContador = parametro.getHoraDesde();

         while (horaContador.getHour() <= parametro.getHoraHasta().getHour()) {
            this.tablaItemAgendaSession.adicionarItem(null, horaContador, null, null, Estado.LIBRE, uuid);
            if (iterHora.getMinute() > 0) {
               horaContador = horaContador.plusMinutes(iterHora.getMinute());
            }

            if (iterHora.getHour() > 0) {
               horaContador = horaContador.plusHours(iterHora.getHour());
            }

            if (horaContador.equals(parametro.getHoraHasta())) {
               this.tablaItemAgendaSession.adicionarItem(null, horaContador, null, null, Estado.LIBRE, uuid);
            }
         }

         agenda.cargarItems(this.tablaItemAgendaSession.getIems(agenda.getUuid()));
         return this.agendaService.cargaDirecta(agenda);
      }
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      Agenda agenda = (Agenda)this.agendas.findById(id).orElse(null);
      this.agregarUUID(agenda);

      for (ItemAgenda itemAgenda : agenda.getItems()) {
         this.tablaItemAgendaSession
            .adicionarItem(
               itemAgenda.getId(), itemAgenda.getHora(), itemAgenda.getCliente(), itemAgenda.getObservacion(), itemAgenda.getEstado(), agenda.getUuid()
            );
      }

      return this.inicio(agenda);
   }

   @GetMapping({"/item"})
   public ModelAndView mostrarItemAgenda(String uuid, Long id) {
      Agenda agenda = (Agenda)this.agendas.findById(id).orElse(null);
      agenda.setUuid(uuid);

      for (ItemAgenda itemAgenda : agenda.getItems()) {
         this.tablaItemAgendaSession
            .adicionarItem(itemAgenda.getId(), itemAgenda.getHora(), itemAgenda.getCliente(), itemAgenda.getObservacion(), itemAgenda.getEstado(), uuid);
      }

      ModelAndView mv = new ModelAndView("agenda/itemAgenda");
      mv.addObject("items", this.tablaItemAgendaSession.getIems(uuid));
      return mv;
   }

   @GetMapping({"/horas/libres"})
   @ResponseBody
   public List<ItemAgenda> buscarHorasLibres(@DateTimeFormat(pattern = "HH:mm") LocalTime hora, String uuid) {
      return this.tablaItemAgendaSession.getHorasLibres(hora, uuid);
   }

   @GetMapping({"/item/mostrarTodo"})
   public ModelAndView mostrarTodo(String uuid) {
      ModelAndView mv = new ModelAndView("agenda/itemAgenda");
      mv.addObject("items", this.tablaItemAgendaSession.getIems(uuid));
      return mv;
   }

   @PutMapping({"/item/cambiarEstado"})
   public ModelAndView cambiarEstado(
      Long itemId, @DateTimeFormat(pattern = "HH:mm") LocalTime hora, Cliente cliente, String observacion, String UUID, Estado estado
   ) {
      if (estado.getDescripcion() == "Libre") {
         this.itemAgendas.modificarItem(itemId, null, estado, observacion);
         this.tablaItemAgendaSession.modificarItem(hora, null, observacion, estado, UUID);
      } else {
         System.out.println("valor del cliente=>" + cliente.getId());
         this.itemAgendas.modificarItem(itemId, cliente, estado, observacion);
         this.tablaItemAgendaSession.modificarItem(hora, cliente, observacion, estado, UUID);
      }

      ModelAndView mv = new ModelAndView("agenda/itemAgenda");
      mv.addObject("items", this.tablaItemAgendaSession.getIems(UUID));
      return mv;
   }

   @PutMapping({"/item/dejar/libre"})
   @ResponseBody
   public String dejarLibre(@DateTimeFormat(pattern = "HH:mm") LocalTime hora, String uuid) {
      this.tablaItemAgendaSession.dejarLibre(hora, uuid);
      return "retorno correctamente";
   }

   private void agregarUUID(Agenda agenda) {
      if (StringUtils.isEmpty(agenda.getUuid())) {
         agenda.setUuid(UUID.randomUUID().toString());
      }
   }
}

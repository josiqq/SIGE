package com.sige.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sige.repository.LicenciaFechas;
import com.sige.service.LicenciaService;

@Controller
@RequestMapping({"/licencias"})
public class LicenciaController {
   @Autowired
   private LicenciaService licenciaService;
   @Autowired
   private LicenciaFechas licenciaFechas;

   @GetMapping({"/maxFecha"})
   @ResponseBody
   public String getMaxFecha() {
      return this.licenciaService.cantidadDias();
   }

   @GetMapping({"/actualizar"})
   public ModelAndView actualizar() {
      ModelAndView mv = new ModelAndView("licencia/licencia");
      String fechaFormateada = this.recuperarFechaFormateada();
      mv.addObject("fecha", fechaFormateada);
      return mv;
   }

   @PostMapping({"/validar"})
   @ResponseBody
   public String validarLicencia(Integer primero, Integer segundo, Integer tercero, Integer primeroRet, Integer segundoRet, Integer terceroRet) {
      return this.licenciaService.validarLicencia(primero, segundo, tercero, primeroRet, segundoRet, terceroRet);
   }

   @PostMapping({"/renovar"})
   @ResponseBody
   public String renovar(
      Integer primero_a,
      Integer segundo_a,
      Integer tercero_a,
      Integer primero_b,
      Integer segundo_b,
      Integer tercero_b,
      @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha
   ) {
      return this.licenciaService.validarCodigo(primero_a, segundo_a, tercero_a, primero_b, segundo_b, tercero_b, fecha);
   }

   private String recuperarFechaFormateada() {
      DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate fechaActual = this.licenciaFechas.getMaxFecha();
      LocalDate hoy = LocalDate.now();
      LocalDate fechaNueva;
      if (fechaActual != null) {
         fechaNueva = fechaActual.plusMonths(1L);
      } else {
         fechaNueva = hoy;
      }

      LocalDate primerDia = fechaNueva.withDayOfMonth(1);
      return primerDia.format(formato);
   }
}

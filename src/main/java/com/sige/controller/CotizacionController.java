package com.sige.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.dto.CotizacionesDTO;
import com.sige.model.Cotizacion;
import com.sige.model.Moneda;
import com.sige.repository.Cotizaciones;
import com.sige.repository.Monedas;
import com.sige.repository.filter.CotizacionFilter;
import com.sige.service.CotizacionService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/cotizaciones"})
public class CotizacionController {
   @Autowired
   private CotizacionService cotizacionService;
   @Autowired
   private Monedas monedas;
   @Autowired
   private Cotizaciones cotizaciones;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Cotizacion cotizacion) {
      ModelAndView mv = new ModelAndView("cotizacion/cotizacion");
      mv.addObject(cotizacion);
      mv.addObject("monedas", this.monedas.findAll());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Cotizacion cotizacion, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/cotizaciones");
      if (result.hasErrors()) {
         return this.inicio(cotizacion);
      } else {
         try {
            this.cotizacionService.guardar(cotizacion);
         } catch (NegocioException var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(cotizacion);
         }

         attributes.addFlashAttribute("mensaje", "Cotización guardada con éxito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView getCotizacion(CotizacionFilter cotizacionFilter) {
      ModelAndView mv = new ModelAndView("cotizacion/buscarCotizacion");
      mv.addObject("cotizaciones", this.cotizacionService.getAllCotizaciones(cotizacionFilter));
      mv.addObject("monedas", this.monedas.findAll());
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView getById(@PathVariable Long id) {
      Cotizacion cotizacion = this.cotizacionService.getById(id);
      return this.inicio(cotizacion);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.cotizacionService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/js/getCotizacion"})
   @ResponseBody
   public Cotizacion getByFecha(@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, @RequestParam Moneda monedaOrigen, @RequestParam Moneda monedaDestino) {
      new Cotizacion();

      try {
         return this.cotizacionService.getCotizacionByFecha(fecha, monedaOrigen, monedaDestino);
      } catch (Exception var6) {
         return new Cotizacion();
      }
   }

   @GetMapping({"/js/cotizacionesDTO"})
   @ResponseBody
   public List<CotizacionesDTO> getCotizacionesDTO(@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, Long monedaOrigen) {
      return this.cotizacionService.getCotizacionesDTO(fecha, monedaOrigen);
   }

   @GetMapping({"/js/getValorCotizacion"})
   @ResponseBody
   public BigDecimal getValor(@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, Long monedaOrigen, Long monedaDestino) {
      return this.cotizacionService.getValorCotizacion(fecha, monedaOrigen, monedaDestino);
   }

   @GetMapping({"/js/getCotizacion/curDate"})
   @ResponseBody
   public List<Cotizacion> getCotizacionCurDate() {
      return this.cotizaciones.getAllCotizacionCurDate();
   }

   @GetMapping({"/js/fCotizar"})
   @ResponseBody
   public BigDecimal fCotizar(Long monedaOrigen, Long monedaDestino, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, BigDecimal importe) {
      return this.cotizaciones.fCotizar(monedaOrigen, monedaDestino, fecha, importe, BigDecimal.ZERO);
   }
}

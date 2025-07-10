package com.meta.controller;

import com.meta.modelo.Producto;
import com.meta.repository.AjusteStocks;
import com.meta.repository.Depositos;
import com.meta.repository.Productos;
import com.meta.repository.filter.StockFilter;
import com.meta.service.StockService;
import com.meta.service.exeption.NegocioException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/stock"})
public class StockController {
   @Autowired
   private StockService stockService;
   @Autowired
   private AjusteStocks ajusteStocks;
   @Autowired
   private Depositos depositos;
   @Autowired
   private Productos productos;

   @GetMapping
   public ModelAndView inicio(StockFilter stockFilter) {
      ModelAndView mv = new ModelAndView("stock/stock");
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      return mv;
   }

   @PostMapping
   public ModelAndView procesar(StockFilter stockFilter, BindingResult result, RedirectAttributes attribute) {
      ModelAndView mv = new ModelAndView("redirect:/stock");

      try {
         this.stockService.recalcularStock(stockFilter);
      } catch (NegocioException var6) {
         result.rejectValue("", "", var6.getMessage());
         return this.inicio(stockFilter);
      }

      attribute.addFlashAttribute(
         "mensaje",
         "Recalculo ejecutado con éxito!\n cantidad actual: "
            + this.ajusteStocks.getCantidadStock(stockFilter.getProducto().getId(), stockFilter.getDeposito().getId())
      );
      return mv;
   }

   @GetMapping({"/recalcula"})
   @ResponseBody
   public ResponseEntity<?> recalculame(Long producto, Long deposito) {
      try {
         this.productos.recalcularStock(producto, deposito);
      } catch (DataAccessException var6) {
         Throwable rootCause = var6.getRootCause();
         String errorMessage = "ERROR EN LA BASE DE DATOS: " + rootCause.getMessage();
         return ResponseEntity.badRequest().body(errorMessage);
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/recalcula-uno"})
   @ResponseBody
   public ResponseEntity<?> recalculaUno(Long producto, Long deposito) {
      Producto productoM = this.productos.findById(producto).orElse(new Producto());
      BigDecimal cantidad = BigDecimal.ZERO;

      try {
         this.productos.recalcularStock(producto, deposito);
         if (productoM.isServicio()) {
            cantidad = BigDecimal.ZERO;
         } else {
            cantidad = this.ajusteStocks.getCantidadStock(producto, deposito);
         }
      } catch (DataAccessException var8) {
         Throwable rootCause = var8.getRootCause();
         String errorMessage = "ERROR EN LA BASE DE DATOS: " + rootCause.getMessage();
         return ResponseEntity.badRequest().body(errorMessage);
      }

      return ResponseEntity.ok(cantidad);
   }
}

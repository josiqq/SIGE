package com.sige.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sige.model.Deposito;
import com.sige.model.Lote;
import com.sige.model.MovimientoLote;
import com.sige.repository.Depositos;
import com.sige.repository.Lotes;
import com.sige.repository.filter.LoteFilter;
import com.sige.repository.filter.MovimientoLoteFilter;
import com.sige.service.LoteService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/lotes"})
public class LoteController {
   @Autowired
   private LoteService loteService;
   @Autowired
   private Lotes lotes;
   @Autowired
   private Depositos depositos;

   @GetMapping({"/get-by-nro"})
   @ResponseBody
   public ResponseEntity<?> getLoteByNumero(String nroLote, Long idProducto, Deposito deposito) {
      try {
         Lote lote = this.loteService.getLoteByNumero(nroLote, idProducto, deposito);
         return ResponseEntity.ok(lote);
      } catch (NegocioException var5) {
         return ResponseEntity.badRequest().body(var5.getMessage());
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView getMovimientoLote(MovimientoLoteFilter filter) {
      ModelAndView mv = new ModelAndView("movimientoLote/movimientoLote");
      List<MovimientoLote> movimientos = this.lotes.getMovimientoLote(filter);
      BigDecimal entradas = movimientos.stream().map(MovimientoLote::getEntrada).reduce(BigDecimal.ZERO, BigDecimal::add);
      BigDecimal salidas = movimientos.stream().map(MovimientoLote::getSalida).reduce(BigDecimal.ZERO, BigDecimal::add);
      BigDecimal saldo = entradas.subtract(salidas);
      mv.addObject(filter);
      mv.addObject("movimientos", this.lotes.getMovimientoLote(filter));
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("entradas", entradas);
      mv.addObject("salidas", salidas);
      mv.addObject("saldo", saldo);
      return mv;
   }

   @GetMapping({"/get-by-deposito-producto"})
   @ResponseBody
   public ResponseEntity<?> getLotesByProductoDeposito(Long idProducto, Deposito deposito) {
      new ArrayList();

      List listaLotes;
      try {
         listaLotes = this.lotes.getLotesByDepositProducto(idProducto, deposito);
      } catch (DataAccessException var7) {
         Throwable rootCause = var7.getRootCause();
         String errorMessage = rootCause.getMessage();
         return ResponseEntity.badRequest().body(errorMessage);
      }

      return ResponseEntity.ok(listaLotes);
   }

   @GetMapping({"/listado"})
   public ModelAndView listadoLotes(LoteFilter filter) {
      ModelAndView mv = new ModelAndView("lote/listadoLote");
      List<Lote> listaLotes = this.lotes.getLotesLista(filter);
      BigDecimal total = listaLotes.stream().map(Lote::getCantidad).reduce(BigDecimal.ZERO, BigDecimal::add);
      mv.addObject(filter);
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("lotes", listaLotes);
      mv.addObject("total", total);
      return mv;
   }
}

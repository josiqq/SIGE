package com.sige.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sige.model.Deposito;
import com.sige.model.Producto;
import com.sige.model.StockDeposito;
import com.sige.repository.Productos;
import com.sige.repository.StockDepositos;

@Controller
@RequestMapping({"/stockDepositos"})
public class StockDepositoController {
   @Autowired
   private StockDepositos stockDepositos;
   @Autowired
   private Productos productos;

   @GetMapping({"/{id}"})
   @ResponseBody
   public List<StockDeposito> buscarStock(@PathVariable Long id) {
      Producto producto = (Producto)this.productos.findById(id).orElse(null);
      return this.stockDepositos.stock(producto);
   }

   @GetMapping({"/js/getStock"})
   @ResponseBody
   public List<StockDeposito> getStockDepositoByProducto(Producto producto) {
      return this.stockDepositos.stock(producto);
   }

   @GetMapping({"/js/get-cantidad"})
   @ResponseBody
   public ResponseEntity<?> getCantidadByPorductoDeposito(Producto producto, Deposito deposito) {
      BigDecimal cantidad = BigDecimal.ZERO;

      try {
         cantidad = this.stockDepositos.getCantidadByProductoDeposito(producto, deposito);
      } catch (NoResultException var5) {
         return ResponseEntity.badRequest().body(var5.getMessage());
      }

      return ResponseEntity.ok(cantidad);
   }
}

package com.sige.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sige.model.CuentaPorCobrar;
import com.sige.model.Venta;
import com.sige.service.CuentaPorCobrarService;

@Controller
@RequestMapping({"/cuentaPorCobrars"})
public class CuentaPorCobrarController {
   @Autowired
   private CuentaPorCobrarService cuentaPorCobrarService;

   @GetMapping({"/buscar/cuenta"})
   @ResponseBody
   public CuentaPorCobrar buscarPorVenta(Venta venta) {
      return this.cuentaPorCobrarService.buscarPorVenta(venta);
   }

   @GetMapping({"/cuentaPorClientes"})
   @ResponseBody
   public List<Object[]> getCuentasByCliente(Long id, Long moneda) {
      return this.cuentaPorCobrarService.getCuentasByCliente(id, moneda);
   }
}

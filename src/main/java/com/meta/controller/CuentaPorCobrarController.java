package com.meta.controller;

import com.meta.modelo.CuentaPorCobrar;
import com.meta.modelo.Venta;
import com.meta.service.CuentaPorCobrarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

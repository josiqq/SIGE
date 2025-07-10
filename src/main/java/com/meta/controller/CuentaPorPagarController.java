package com.meta.controller;

import com.meta.modelo.CuentaPorPagar;
import com.meta.modelo.Moneda;
import com.meta.modelo.Proveedor;
import com.meta.repository.CuentaPorPagars;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/cuentaPorPagars"})
public class CuentaPorPagarController {
   @Autowired
   private CuentaPorPagars cuentaPorPagars;

   @GetMapping({"/js/buscar"})
   @ResponseBody
   public List<CuentaPorPagar> getCuentaPorProveedor(Proveedor proveedor, Moneda moneda) {
      System.out.println("entra en esta parte?=" + proveedor.getId());
      return this.cuentaPorPagars.getCuentaPorProveedores(proveedor, moneda);
   }
}

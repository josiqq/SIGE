package com.sige.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sige.model.CuentaPorPagar;
import com.sige.model.Moneda;
import com.sige.model.Proveedor;
import com.sige.repository.CuentaPorPagars;

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

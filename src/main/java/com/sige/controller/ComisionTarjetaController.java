package com.sige.controller;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.model.ComisionTarjeta;
import com.sige.service.ComisionTarjetaService;

@Controller
@RequestMapping({"/comisionTarjetas"})
public class ComisionTarjetaController {
   @Autowired
   private ComisionTarjetaService comisionTarjetaService;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @PostMapping({"/guardar"})
   @ResponseBody
   public ComisionTarjeta guardar(@RequestBody ComisionTarjeta comisionTarjeta) {
      System.out.println("importe: " + comisionTarjeta.getImporte());
      this.comisionTarjetaService.guardar(comisionTarjeta);
      return comisionTarjeta;
   }
}

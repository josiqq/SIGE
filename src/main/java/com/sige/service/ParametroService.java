package com.sige.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Parametro;
import com.sige.repository.Parametros;

@Service
public class ParametroService {
   @Autowired
   private Parametros parametros;

   public void esNuevo() {
      Parametro parametro = new Parametro();
      parametro.setId(1L);
      parametro.setEmpresa("EMPRESA");
      parametro.setRuc("*********");
      parametro.setTelefono("*************");
      parametro.setMostrarFondo(false);
      this.parametros.save(parametro);
   }

   public void guardar(Parametro parametro) {
      this.parametros.save(parametro);
   }
}

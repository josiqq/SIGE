package com.sige.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Marcacion;
import com.sige.repository.Marcaciones;

@Service
public class MarcacionService {
   @Autowired
   private Marcaciones marcaciones;

   public void guardar(Marcacion marcacion) {
      if (marcacion.getId() != null) {
         Optional<Marcacion> marcacionOp = this.marcaciones.findById(marcacion.getId());
         if (marcacion.isTerminadoGameUno() && !marcacion.isTerminadoGameDos() && !marcacion.isTerminadoGameTres()) {
            System.out.println("terminar game uno");
            marcacion.setGameUnoA(marcacion.getPuntoA());
            marcacion.setGameUnoB(marcacion.getPuntoB());
            marcacion.setPuntoA(0);
            marcacion.setPuntoB(0);
         }

         if (marcacion.isTerminadoGameDos() && marcacion.isTerminadoGameUno() && !marcacion.isTerminadoGameTres()) {
            System.out.println("terminar game dos");
            marcacion.setGameDosA(marcacion.getPuntoA());
            marcacion.setGameDosB(marcacion.getPuntoB());
            marcacion.setGameUnoA(marcacionOp.get().getGameUnoA());
            marcacion.setGameUnoB(marcacionOp.get().getGameUnoB());
            marcacion.setPuntoA(0);
            marcacion.setPuntoB(0);
         }

         if (marcacion.isTerminadoGameUno() && marcacion.isTerminadoGameDos() && marcacion.isTerminadoGameTres() && !marcacion.isTerminado()) {
            System.out.println("terminar game tres");
            marcacion.setGameTresA(marcacion.getPuntoA());
            marcacion.setGameTresB(marcacion.getPuntoB());
            marcacion.setGameDosA(marcacionOp.get().getGameDosA());
            marcacion.setGameDosB(marcacionOp.get().getGameDosB());
            marcacion.setGameUnoA(marcacionOp.get().getGameUnoA());
            marcacion.setGameUnoB(marcacionOp.get().getGameUnoB());
            marcacion.setPuntoA(0);
            marcacion.setPuntoB(0);
         }

         if (marcacion.isTerminado()) {
            marcacion.setGameDosA(marcacionOp.get().getGameDosA());
            marcacion.setGameDosB(marcacionOp.get().getGameDosB());
            marcacion.setGameUnoA(marcacionOp.get().getGameUnoA());
            marcacion.setGameUnoB(marcacionOp.get().getGameUnoB());
            marcacion.setGameTresA(marcacionOp.get().getGameTresA());
            marcacion.setGameTresB(marcacionOp.get().getGameTresB());
            marcacion.setPuntoA(0);
            marcacion.setPuntoB(0);
         }
      }

      this.marcaciones.save(marcacion);
      this.marcaciones.flush();
      System.out.println("Id de la marcacion=>" + marcacion.getId());
   }

   public Marcacion buscarMarcacionAbierta() {
      return this.marcaciones.buscarMarcacionAbierto();
   }

   public void modificarPuntoA(Integer puntoA) {
      this.marcaciones.ModificarPuntoA(puntoA);
   }

   public void modificarPuntoB(Integer puntoA) {
      this.marcaciones.ModificarPuntoB(puntoA);
   }

   public Long getMaximoId() {
      return this.marcaciones.getMaximoId();
   }

   public Marcacion getMarcacionTerminado(Long id) {
      return this.marcaciones.getMarcacionTerminado(id);
   }
}

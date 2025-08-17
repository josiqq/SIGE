package com.sige.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Marcacion {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_equipo_a"
   )
   @NotNull(
      message = "Debe informar los dos equipos!"
   )
   private Equipo equipoA;
   @ManyToOne
   @JoinColumn(
      name = "id_equipo_b"
   )
   @NotNull(
      message = "Debe informar los dos equipos!"
   )
   private Equipo equipoB;
   private Integer puntoA = 0;
   private Integer puntoB = 0;
   private Integer gameUnoA = 0;
   private Integer gameUnoB = 0;
   private Integer gameDosA = 0;
   private Integer gameDosB = 0;
   private Integer gameTresA = 0;
   private Integer gameTresB = 0;
   private boolean terminadoGameUno;
   private boolean terminadoGameDos;
   private boolean terminadoGameTres;
   private boolean terminado;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Equipo getEquipoA() {
      return this.equipoA;
   }

   public void setEquipoA(Equipo equipoA) {
      this.equipoA = equipoA;
   }

   public Equipo getEquipoB() {
      return this.equipoB;
   }

   public void setEquipoB(Equipo equipoB) {
      this.equipoB = equipoB;
   }

   public Integer getPuntoA() {
      return this.puntoA;
   }

   public void setPuntoA(Integer puntoA) {
      this.puntoA = puntoA;
   }

   public Integer getPuntoB() {
      return this.puntoB;
   }

   public void setPuntoB(Integer puntoB) {
      this.puntoB = puntoB;
   }

   public boolean isTerminado() {
      return this.terminado;
   }

   public void setTerminado(boolean terminado) {
      this.terminado = terminado;
   }

   public Integer getGameUnoA() {
      return this.gameUnoA;
   }

   public void setGameUnoA(Integer gameUnoA) {
      this.gameUnoA = gameUnoA;
   }

   public Integer getGameUnoB() {
      return this.gameUnoB;
   }

   public void setGameUnoB(Integer gameUnoB) {
      this.gameUnoB = gameUnoB;
   }

   public Integer getGameDosA() {
      return this.gameDosA;
   }

   public void setGameDosA(Integer gameDosA) {
      this.gameDosA = gameDosA;
   }

   public Integer getGameDosB() {
      return this.gameDosB;
   }

   public void setGameDosB(Integer gameDosB) {
      this.gameDosB = gameDosB;
   }

   public Integer getGameTresA() {
      return this.gameTresA;
   }

   public void setGameTresA(Integer gameTresA) {
      this.gameTresA = gameTresA;
   }

   public Integer getGameTresB() {
      return this.gameTresB;
   }

   public void setGameTresB(Integer gameTresB) {
      this.gameTresB = gameTresB;
   }

   public boolean isTerminadoGameUno() {
      return this.terminadoGameUno;
   }

   public void setTerminadoGameUno(boolean terminadoGameUno) {
      this.terminadoGameUno = terminadoGameUno;
   }

   public boolean isTerminadoGameDos() {
      return this.terminadoGameDos;
   }

   public void setTerminadoGameDos(boolean terminadoGameDos) {
      this.terminadoGameDos = terminadoGameDos;
   }

   public boolean isTerminadoGameTres() {
      return this.terminadoGameTres;
   }

   public void setTerminadoGameTres(boolean terminadoGameTres) {
      this.terminadoGameTres = terminadoGameTres;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.id);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         Marcacion other = (Marcacion)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}

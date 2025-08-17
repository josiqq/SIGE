package com.sige.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Licencia;
import com.sige.model.LicenciaFecha;
import com.sige.repository.LicenciaFechas;
import com.sige.repository.Licencias;

@Service
public class LicenciaService {
   @Autowired
   private Licencias licencias;
   @Autowired
   private LicenciaFechas licenciaFechas;

   public void guardar(Licencia licencia) {
      this.licencias.save(licencia);
   }

   public String cantidadDias() {
      String retorno = "b";
      Long dias = 0L;
      Licencia licencia = new Licencia();
      LocalDate ultimaFecha = this.licencias.buscarUltimaFecha();
      if (ultimaFecha == null) {
         retorno = "b";
      } else {
         Licencia licenciaEntontrada = this.licencias.getLicenciaByFecha(ultimaFecha);
         if (!licenciaEntontrada.isBloqueado()) {
            LocalDate fechaDisponible = this.licenciaFechas.getMaxFecha();
            LocalDate hoy = LocalDate.now();
            dias = ChronoUnit.DAYS.between(fechaDisponible, hoy);
            if (ultimaFecha.isAfter(hoy)) {
               licenciaEntontrada.setBloqueado(true);
               this.guardar(licenciaEntontrada);
               retorno = "b";
            } else if (ultimaFecha.isBefore(hoy)) {
               if (dias > 35L) {
                  licenciaEntontrada.setBloqueado(true);
                  this.guardar(licenciaEntontrada);
                  retorno = "b";
               } else {
                  licencia.setFecha(hoy);
                  licencia.setDias(dias);
                  this.guardar(licencia);
                  retorno = "l";
               }
            } else {
               retorno = "l";
            }
         } else {
            retorno = "b";
         }
      }

      return retorno;
   }

   public String validarLicencia(Integer primero, Integer segundo, Integer tercero, Integer primeroRet, Integer segundoRet, Integer terceroRet) {
      Integer primeroRecibido = 0;
      Integer segundoRecibido = 0;
      Integer terceroRecibido = 0;
      String resultado = "b";
      LocalDate hoy = LocalDate.now();
      Integer mes = hoy.getMonthValue();
      primeroRecibido = mes * primero + 2;
      segundoRecibido = mes * segundo + 2;
      terceroRecibido = mes * tercero + 2;
      if (primeroRecibido.equals(primeroRet) && segundoRecibido.equals(segundoRet) && terceroRecibido.equals(terceroRet)) {
         LicenciaFecha licenciaFecha = new LicenciaFecha();
         Licencia licencia = new Licencia();
         LocalDate primerDiaDelMes = LocalDate.now().withDayOfMonth(1);
         LocalDate ultimaFecha = LocalDate.now();
         licencia.setFecha(ultimaFecha);
         licencia.setDias(0L);
         this.guardar(licencia);
         licenciaFecha.setFecha(primerDiaDelMes);
         this.licenciaFechas.save(licenciaFecha);
         resultado = "l";
      } else {
         resultado = "b";
      }

      return resultado;
   }

   public String validarCodigo(
      Integer primero_a, Integer segundo_a, Integer tercero_a, Integer primero_b, Integer segundo_b, Integer tercero_b, LocalDate fechaNueva
   ) {
      Integer primeroRecibido = 0;
      Integer segundoRecibido = 0;
      Integer terceroRecibido = 0;
      String resultado = "b";
      LocalDate hoy = LocalDate.now();
      Integer mes = hoy.getMonthValue();
      primeroRecibido = mes * primero_a + 2;
      segundoRecibido = mes * segundo_a + 2;
      terceroRecibido = mes * tercero_a + 2;
      if (primeroRecibido.equals(primero_b) && segundoRecibido.equals(segundo_b) && terceroRecibido.equals(tercero_b)) {
         LicenciaFecha licenciaFecha = new LicenciaFecha();
         licenciaFecha.setFecha(fechaNueva);
         this.licenciaFechas.save(licenciaFecha);
         resultado = "l";
      } else {
         resultado = "b";
      }

      return resultado;
   }
}

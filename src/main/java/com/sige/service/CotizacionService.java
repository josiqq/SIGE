package com.sige.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.dto.CotizacionesDTO;
import com.sige.model.Cotizacion;
import com.sige.model.Moneda;
import com.sige.repository.Cotizaciones;
import com.sige.repository.Monedas;
import com.sige.repository.filter.CotizacionFilter;
import com.sige.service.exeption.NegocioException;

@Service
public class CotizacionService {
   @Autowired
   private Cotizaciones cotizaciones;
   @Autowired
   Monedas monedas;

   public void guardar(Cotizacion cotizacion) {
      cotizacion.setModificado(true);
      if (!cotizacion.isDividir() && !cotizacion.isMultiplicar()) {
         throw new NegocioException(
            "Debe seleccionar al menos una opcion, dividir o multiplicar, si elige dividir, la moneda origen se dividirá por la cotizacion para obtener la moneda destino,  si elige multiplicar, la moneda origen se multiplicara por la cotizacion para obtener la moneda destino"
         );
      } else if (cotizacion.isDividir() && cotizacion.isMultiplicar()) {
         throw new NegocioException("Solo puede elejir una operacion, elija multiplicar o dividir,pero no ambos!");
      } else if (cotizacion.getMonedaOrigen().equals(cotizacion.getMonedaDestino())) {
         throw new NegocioException("La moneda destino debe ser diferente a la moneda origen !");
      } else if (cotizacion.getValor().compareTo(BigDecimal.ZERO) == 0) {
         throw new NegocioException("La cotizacion no puede ser cero!");
      } else {
         this.cotizaciones.save(cotizacion);
      }
   }

   public Cotizacion getById(Long id) {
      return (Cotizacion)this.cotizaciones.findById(id).orElse(null);
   }

   public void eliminar(Long id) {
      try {
         this.cotizaciones.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }

   public Cotizacion getCotizacionByFecha(LocalDate fecha, Moneda monedaOrigen, Moneda monedaDestino) {
      return this.cotizaciones.getCotizacionByFecha(fecha, monedaOrigen, monedaDestino);
   }

   public List<Cotizacion> getAllCotizaciones(CotizacionFilter cotizacionFilter) {
      return this.cotizaciones.getAllCotizaciones(cotizacionFilter);
   }

   public void verificarCotizacion() {
      System.out.println("verifica cotizacion ...");

      for (Moneda moneda : this.monedas.findAll()) {
         this.cotizacionExiste(moneda);
      }
   }

   public void cotizacionExiste(Moneda monedaOrigen) {
      LocalDate fecha = LocalDate.now();

      for (Moneda monedaDestino : this.monedas.findAll()) {
         this.verificarUltimaFecha(fecha, monedaOrigen, monedaDestino);
      }
   }

   public void verificarUltimaFecha(LocalDate fecha, Moneda monedaOrigen, Moneda monedaDestino) {
      Optional<Cotizacion> cotizacion = this.cotizaciones.getCotizaciones(fecha, monedaOrigen, monedaDestino);
      if (cotizacion.isEmpty()) {
         LocalDate ultimaFecha = this.cotizaciones.maximaFecha(monedaOrigen, monedaDestino);
         if (ultimaFecha != null) {
            Cotizacion ultimoCotizacion = this.cotizaciones.getCotizacionByFecha(ultimaFecha, monedaOrigen, monedaDestino);
            Long diasDiferencia = ChronoUnit.DAYS.between(ultimaFecha, fecha);

            for (int i = 1; i <= diasDiferencia; i++) {
               Cotizacion cotizacionNuevo = new Cotizacion();
               LocalDate nuevaFecha = ultimaFecha.plusDays(i);
               cotizacionNuevo.setFecha(nuevaFecha);
               cotizacionNuevo.setMonedaOrigen(monedaOrigen);
               cotizacionNuevo.setMonedaDestino(monedaDestino);
               cotizacionNuevo.setDividir(ultimoCotizacion.isDividir());
               cotizacionNuevo.setMultiplicar(ultimoCotizacion.isMultiplicar());
               cotizacionNuevo.setValor(ultimoCotizacion.getValor());
               this.cotizaciones.save(cotizacionNuevo);
            }
         }
      }
   }

   public List<CotizacionesDTO> getCotizacionesDTO(LocalDate fecha, Long monedaOrigen) {
      return this.cotizaciones.getCotizacionesDTO(fecha, monedaOrigen);
   }

   public BigDecimal getValorCotizacion(LocalDate fecha, Long monedaOrigen, Long monedaDestino) {
      return this.cotizaciones.getValorCotizacion(fecha, monedaOrigen, monedaDestino);
   }
}

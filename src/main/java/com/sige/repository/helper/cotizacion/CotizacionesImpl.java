package com.sige.repository.helper.cotizacion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sige.dto.CotizacionesDTO;
import com.sige.dto.CotizacionesDTOMapper;
import com.sige.model.Cotizacion;
import com.sige.model.Moneda;
import com.sige.repository.filter.CotizacionFilter;

public class CotizacionesImpl implements CotizacionesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public Optional<Cotizacion> getCotizaciones(LocalDate fecha, Moneda monedaOrigen, Moneda monedaDestino) {
      String sql = "from Cotizacion where fecha = :fecha and monedaOrigen = :monedaOrigen and monedaDestino =:monedaDestino";

      try {
         Cotizacion cotizacion = (Cotizacion)this.manager
            .createQuery(sql, Cotizacion.class)
            .setParameter("fecha", fecha)
            .setParameter("monedaOrigen", monedaOrigen)
            .setParameter("monedaDestino", monedaDestino)
            .getSingleResult();
         return Optional.of(cotizacion);
      } catch (NoResultException var6) {
         return Optional.empty();
      }
   }

   @Override
   public Cotizacion getCotizacionByFecha(LocalDate fecha, Moneda monedaOrigen, Moneda monedaDestino) {
      String sql = "from Cotizacion where fecha = :fecha and monedaOrigen = :monedaOrigen and monedaDestino =:monedaDestino";
      new Cotizacion();
      return (Cotizacion)this.manager
         .createQuery(sql, Cotizacion.class)
         .setParameter("fecha", fecha)
         .setParameter("monedaOrigen", monedaOrigen)
         .setParameter("monedaDestino", monedaDestino)
         .getSingleResult();
   }

   @Override
   public LocalDate maximaFecha(Moneda monedaOrigen, Moneda monedaDestino) {
      String sql = "select max(fecha) from Cotizacion where monedaOrigen =:monedaOrigen and monedaDestino = :monedaDestino";
      return (LocalDate)this.manager
         .createQuery(sql, LocalDate.class)
         .setParameter("monedaOrigen", monedaOrigen)
         .setParameter("monedaDestino", monedaDestino)
         .getSingleResult();
   }

   @Override
   public BigDecimal getValorCotizacion(LocalDate fecha, Long monedaOrigen, Long monedaDestino) {
      String sql = "select valor from Cotizacion where fecha =:fecha and monedaOrigen.id =:monedaOrigen and monedaDestino.id =:monedaDestino";
      return (BigDecimal)this.manager
         .createQuery(sql, BigDecimal.class)
         .setParameter("fecha", fecha)
         .setParameter("monedaOrigen", monedaOrigen)
         .setParameter("monedaDestino", monedaDestino)
         .getSingleResult();
   }

   @Override
   public List<Cotizacion> getAllCotizaciones(CotizacionFilter cotizacionFilter) {
      String sql = "from Cotizacion where (fecha between(:fechaDesde)and(:fechaHasta)or :fechaDesde is null or :fechaHasta is null)  and (monedaOrigen = :monedaOrigen or :monedaOrigen is null) and (monedaDestino =:monedaDestino or :monedaDestino is null)";
      return this.manager
         .createQuery(sql, Cotizacion.class)
         .setParameter("fechaDesde", cotizacionFilter.getFechaDesde())
         .setParameter("fechaHasta", cotizacionFilter.getFechaHasta())
         .setParameter("monedaOrigen", cotizacionFilter.getMonedaOrigen())
         .setParameter("monedaDestino", cotizacionFilter.getMonedaDestino())
         .getResultList();
   }

   @Override
   public List<CotizacionesDTO> getCotizacionesDTO(LocalDate fecha, Long monedaOrigen) {
      String sql = "select \tmoneda.nombre     ,moneda.sigla     ,cotizacion.valor     ,cotizacion.multiplicar     ,cotizacion.dividir     ,moneda.decimales  from cotizacion join moneda on cotizacion.id_moneda_destino = moneda.id  where \tfecha = :fecha \tand id_moneda_origen = :monedaOrigen";
      List<Object[]> results = this.manager.createNativeQuery(sql).setParameter("fecha", fecha).setParameter("monedaOrigen", monedaOrigen).getResultList();
      CotizacionesDTOMapper mapper = new CotizacionesDTOMapper();
      return mapper.maperarResults(results);
   }

   @Override
   public BigDecimal fCotizacion(Long monedaOrigen, Long monedaDestino, LocalDate fecha) {
      try {
         String sql = "SELECT f_cotizacion(:monedaOrigen, :monedaDestino, :fecha)";
         Query query = this.manager
            .createNativeQuery(sql)
            .setParameter("monedaOrigen", monedaOrigen)
            .setParameter("monedaDestino", monedaDestino)
            .setParameter("fecha", fecha);
         return (BigDecimal)query.getSingleResult();
      } catch (NoResultException var7) {
         return BigDecimal.ZERO;
      } catch (Exception var8) {
         var8.printStackTrace();
         throw new RuntimeException("Error al obtener la cotización", var8);
      }
   }

   @Override
   public Long getCantidadCotizaciones() {
      String sql = "select count(id) from Cotizacion where modificado = false and fecha = curdate()";
      return (Long)this.manager.createQuery(sql, Long.class).getSingleResult();
   }

   @Override
   public List<Cotizacion> getAllCotizacionCurDate() {
      String sql = "from Cotizacion where fecha = curdate() and modificado = false";
      return this.manager.createQuery(sql, Cotizacion.class).getResultList();
   }

   @Override
   public BigDecimal fCotizar(Long monedaOrigen, Long monedaDestino, LocalDate fecha, BigDecimal importe, BigDecimal cotizacion) {
      try {
         String sql = "SELECT f_cotizar(:monedaOrigen, :monedaDestino, :fecha, :importe, :cotizacion)";
         Query query = this.manager
            .createNativeQuery(sql)
            .setParameter("monedaOrigen", monedaOrigen)
            .setParameter("monedaDestino", monedaDestino)
            .setParameter("fecha", fecha)
            .setParameter("importe", importe)
            .setParameter("cotizacion", cotizacion);
         return (BigDecimal)query.getSingleResult();
      } catch (NoResultException var9) {
         return BigDecimal.ZERO;
      } catch (Exception var10) {
         var10.printStackTrace();
         throw new RuntimeException("Error al obtener la cotización", var10);
      }
   }
}

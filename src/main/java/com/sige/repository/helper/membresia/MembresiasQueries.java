package com.sige.repository.helper.membresia;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.sige.model.Membresia;
import com.sige.repository.filter.MembresiaFilter;

public interface MembresiasQueries {
   BigDecimal totalImporte(MembresiaFilter memebresiaFilter);

   List<Membresia> buscarPorFecha(MembresiaFilter membresiaFilter);

   BigDecimal membresiaDelDia();

   LocalDate ultimaFecha(Long id);
}

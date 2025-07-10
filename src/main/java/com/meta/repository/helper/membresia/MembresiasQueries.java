package com.meta.repository.helper.membresia;

import com.meta.modelo.Membresia;
import com.meta.repository.filter.MembresiaFilter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface MembresiasQueries {
   BigDecimal totalImporte(MembresiaFilter memebresiaFilter);

   List<Membresia> buscarPorFecha(MembresiaFilter membresiaFilter);

   BigDecimal membresiaDelDia();

   LocalDate ultimaFecha(Long id);
}

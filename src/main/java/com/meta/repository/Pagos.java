package com.meta.repository;

import com.meta.modelo.Pago;
import com.meta.repository.helper.pago.PagosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pagos extends JpaRepository<Pago, Long>, PagosQueries {
}

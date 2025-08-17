package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Pago;
import com.sige.repository.helper.pago.PagosQueries;

public interface Pagos extends JpaRepository<Pago, Long>, PagosQueries {
}

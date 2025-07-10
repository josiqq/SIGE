package com.meta.repository;

import com.meta.modelo.PagoVendedor;
import com.meta.repository.helper.pagoVendedor.PagoVendedoresQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoVendedores extends JpaRepository<PagoVendedor, Long>, PagoVendedoresQueries {
}

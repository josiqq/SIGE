package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.PagoVendedor;
import com.sige.repository.helper.pagoVendedor.PagoVendedoresQueries;

public interface PagoVendedores extends JpaRepository<PagoVendedor, Long>, PagoVendedoresQueries {
}

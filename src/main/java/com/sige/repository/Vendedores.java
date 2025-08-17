package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Vendedor;
import com.sige.repository.helper.vendedor.VendedoresQueries;

public interface Vendedores extends JpaRepository<Vendedor, Long>, VendedoresQueries {
   Optional<Vendedor> findByDocumento(String documento);
}

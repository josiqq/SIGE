package com.meta.repository;

import com.meta.modelo.Vendedor;
import com.meta.repository.helper.vendedor.VendedoresQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Vendedores extends JpaRepository<Vendedor, Long>, VendedoresQueries {
   Optional<Vendedor> findByDocumento(String documento);
}

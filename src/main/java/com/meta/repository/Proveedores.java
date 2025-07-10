package com.meta.repository;

import com.meta.modelo.Proveedor;
import com.meta.repository.helper.proveedor.ProveedoresQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Proveedores extends JpaRepository<Proveedor, Long>, ProveedoresQueries {
   Optional<Proveedor> findByDocumento(String documento);
}

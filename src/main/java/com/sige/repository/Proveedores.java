package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Proveedor;
import com.sige.repository.helper.proveedor.ProveedoresQueries;

public interface Proveedores extends JpaRepository<Proveedor, Long>, ProveedoresQueries {
   Optional<Proveedor> findByDocumento(String documento);
}

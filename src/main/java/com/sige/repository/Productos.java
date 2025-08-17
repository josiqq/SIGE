package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Producto;
import com.sige.repository.helper.producto.ProductosQueries;

public interface Productos extends JpaRepository<Producto, Long>, ProductosQueries {
   Optional<Producto> findByCodigo(String codigo);
}

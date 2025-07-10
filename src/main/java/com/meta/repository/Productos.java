package com.meta.repository;

import com.meta.modelo.Producto;
import com.meta.repository.helper.producto.ProductosQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productos extends JpaRepository<Producto, Long>, ProductosQueries {
   Optional<Producto> findByCodigo(String codigo);
}

package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ExtractoProducto;
import com.sige.repository.helper.extractoProducto.ExtractoProductosQueries;

public interface ExtractoProductos extends JpaRepository<ExtractoProducto, Long>, ExtractoProductosQueries {
}

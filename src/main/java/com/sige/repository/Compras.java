package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Compra;
import com.sige.repository.helper.Compra.ComprasQueries;

public interface Compras extends JpaRepository<Compra, Long>, ComprasQueries {
}

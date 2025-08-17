package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Banco;
import com.sige.repository.helper.banco.BancosQueries;

public interface Bancos extends JpaRepository<Banco, Long>, BancosQueries {
   Optional<Banco> findByNombre(String nombre);
}

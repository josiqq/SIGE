package com.meta.repository;

import com.meta.modelo.Banco;
import com.meta.repository.helper.banco.BancosQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Bancos extends JpaRepository<Banco, Long>, BancosQueries {
   Optional<Banco> findByNombre(String nombre);
}

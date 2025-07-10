package com.meta.repository;

import com.meta.modelo.Deposito;
import com.meta.repository.helper.deposito.DepositosQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Depositos extends JpaRepository<Deposito, Long>, DepositosQueries {
   Optional<Deposito> findByNombre(String nombre);
}

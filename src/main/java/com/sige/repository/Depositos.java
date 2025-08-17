package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Deposito;
import com.sige.repository.helper.deposito.DepositosQueries;

public interface Depositos extends JpaRepository<Deposito, Long>, DepositosQueries {
   Optional<Deposito> findByNombre(String nombre);
}

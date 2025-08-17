package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Cliente;
import com.sige.repository.helper.clientes.ClientesQueries;

public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {
   Optional<Cliente> findByDocumento(String documento);
}

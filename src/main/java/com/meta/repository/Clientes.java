package com.meta.repository;

import com.meta.modelo.Cliente;
import com.meta.repository.helper.clientes.ClientesQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {
   Optional<Cliente> findByDocumento(String documento);
}

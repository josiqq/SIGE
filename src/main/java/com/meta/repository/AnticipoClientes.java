package com.meta.repository;

import com.meta.modelo.AnticipoCliente;
import com.meta.repository.helper.anticipoCliente.AnticipoClientesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnticipoClientes extends JpaRepository<AnticipoCliente, Long>, AnticipoClientesQueries {
}

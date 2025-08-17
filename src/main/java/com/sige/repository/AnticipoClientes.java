package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.AnticipoCliente;
import com.sige.repository.helper.anticipoCliente.AnticipoClientesQueries;

public interface AnticipoClientes extends JpaRepository<AnticipoCliente, Long>, AnticipoClientesQueries {
}

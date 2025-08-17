package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.MoraCliente;
import com.sige.repository.helper.moraCliente.MoraClientesQueries;

public interface MoraClientes extends JpaRepository<MoraCliente, Long>, MoraClientesQueries {
}

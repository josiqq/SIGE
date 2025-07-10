package com.meta.repository;

import com.meta.modelo.MoraCliente;
import com.meta.repository.helper.moraCliente.MoraClientesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoraClientes extends JpaRepository<MoraCliente, Long>, MoraClientesQueries {
}

package com.meta.repository;

import com.meta.modelo.Auditoria;
import com.meta.repository.helper.auditoria.AuditoriasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Auditorias extends JpaRepository<Auditoria, Long>, AuditoriasQueries {
}

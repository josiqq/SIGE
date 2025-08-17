package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Auditoria;
import com.sige.repository.helper.auditoria.AuditoriasQueries;

public interface Auditorias extends JpaRepository<Auditoria, Long>, AuditoriasQueries {
}

package com.sige.repository.helper.auditoria;

import java.util.List;

import com.sige.model.Auditoria;
import com.sige.repository.filter.AuditoriaFilter;

public interface AuditoriasQueries {
   List<Auditoria> getAuditoriasByFilter(AuditoriaFilter filter);
}

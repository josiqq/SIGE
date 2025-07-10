package com.meta.repository.helper.auditoria;

import com.meta.modelo.Auditoria;
import com.meta.repository.filter.AuditoriaFilter;
import java.util.List;

public interface AuditoriasQueries {
   List<Auditoria> getAuditoriasByFilter(AuditoriaFilter filter);
}

package com.meta.repository;

import com.meta.modelo.Planilla;
import com.meta.repository.helper.planilla.PlanillasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Planillas extends JpaRepository<Planilla, Long>, PlanillasQueries {
}

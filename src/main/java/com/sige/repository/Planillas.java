package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Planilla;
import com.sige.repository.helper.planilla.PlanillasQueries;

public interface Planillas extends JpaRepository<Planilla, Long>, PlanillasQueries {
}

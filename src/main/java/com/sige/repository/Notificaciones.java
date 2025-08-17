package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Notificacion;
import com.sige.repository.helper.notificacion.NotificacionesQueries;

public interface Notificaciones extends JpaRepository<Notificacion, Long>, NotificacionesQueries {
}

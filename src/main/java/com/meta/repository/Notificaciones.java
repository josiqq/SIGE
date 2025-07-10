package com.meta.repository;

import com.meta.modelo.Notificacion;
import com.meta.repository.helper.notificacion.NotificacionesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Notificaciones extends JpaRepository<Notificacion, Long>, NotificacionesQueries {
}

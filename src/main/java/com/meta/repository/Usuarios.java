package com.meta.repository;

import com.meta.modelo.Usuario;
import com.meta.repository.helper.usuario.UsuariosQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {
   Optional<Usuario> findByNombre(String nombre);
}

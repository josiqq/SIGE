package com.meta.repository.helper.usuario;

import com.meta.modelo.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuariosQueries {
   Optional<Usuario> buscarPorNombreActivo(String nombre);

   List<String> permisos(Usuario usuario);

   List<Usuario> buscarPorNombre(Usuario usuario);

   Usuario buscarUsuario(String nombre);
}

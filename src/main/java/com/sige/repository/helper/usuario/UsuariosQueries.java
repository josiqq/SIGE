package com.sige.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.sige.model.Usuario;

public interface UsuariosQueries {
   Optional<Usuario> buscarPorNombreActivo(String nombre);

   List<String> permisos(Usuario usuario);

   List<Usuario> buscarPorNombre(Usuario usuario);

   Usuario buscarUsuario(String nombre);
}

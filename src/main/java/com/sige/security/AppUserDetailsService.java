package com.sige.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sige.model.Usuario;
import com.sige.repository.Usuarios;

@Service
public class AppUserDetailsService implements UserDetailsService {
   @Autowired
   private Usuarios usuarios;

   public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
      Optional<Usuario> usuarioOptional = this.usuarios.buscarPorNombreActivo(nombre);
      Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Nombre o clave incorrecta"));
      return new UsuarioSistema(usuario, this.GetPermisos(usuario));
   }

   private Collection<? extends GrantedAuthority> GetPermisos(Usuario usuario) {
      Set<SimpleGrantedAuthority> authorities = new HashSet<>();
      List<String> permisos = this.usuarios.permisos(usuario);
      permisos.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));
      return authorities;
   }
}

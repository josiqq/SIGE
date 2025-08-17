package com.sige.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sige.model.Usuario;
import com.sige.repository.Usuarios;
import com.sige.service.exeption.NegocioException;

@Service
public class UsuarioService {
   @Autowired
   private Usuarios usuarios;
   @Autowired
   private PasswordEncoder passwordEncoder;

   public void guardar(Usuario usuario) {
      Optional<Usuario> usuarioOptional = this.usuarios.findByNombre(usuario.getNombre());
      if (usuarioOptional.isPresent() && usuarioOptional.get().getId() != usuario.getId()) {
         throw new NegocioException("Ya existe un usuario con este nombre!");
      } else {
         if (usuario.getId() == null && !usuario.getPassword().isEmpty()) {
            usuario.setPassword(this.passwordEncoder.encode(usuario.getPassword()));
         }

         if (usuario.getPassword().isEmpty() && usuario.getId() == null) {
            usuario.setPassword(this.passwordEncoder.encode("123"));
         }

         if (usuario.getId() != null) {
            if (usuario.getGrupos() == null) {
               usuario.setGrupos(usuarioOptional.get().getGrupos());
            }

            if (usuario.getInstructor() == null) {
               usuario.setInstructor(usuarioOptional.get().getInstructor());
            }

            if (usuario.getPassword().isEmpty()) {
               System.out.println("las seña al amodificar esta en blanco!!");
               usuario.setPassword(usuarioOptional.get().getPassword());
            } else {
               System.out.println("el id tiene valor y la seña tiene valor" + usuario.getPassword());
               usuario.setPassword(this.passwordEncoder.encode(usuario.getPassword()));
            }

            if (usuario.getCajero() == null) {
               usuario.setCajero(usuarioOptional.get().getCajero());
            }

            if (usuario.getCuenta() == null) {
               usuario.setCuenta(usuarioOptional.get().getCuenta());
            }

            if (usuario.getVendedor() == null) {
               usuario.setVendedor(usuarioOptional.get().getVendedor());
            }
         }

         this.usuarios.save(usuario);
      }
   }

   public List<Usuario> buscarTodos() {
      return this.usuarios.findAll();
   }

   public Usuario buscarPorId(Long id) {
      return (Usuario)this.usuarios.findById(id).orElse(null);
   }

   public List<Usuario> buscarPorNombre(Usuario usuario) {
      return this.usuarios.buscarPorNombre(usuario);
   }

   public void eliminar(Long id) {
      try {
         this.usuarios.deleteById(id);
         this.usuarios.flush();
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible Eliminar, ya tuvo movimiento!");
      }
   }

   public Usuario buscarUsuario(String nombre) {
      return this.usuarios.buscarUsuario(nombre);
   }
}

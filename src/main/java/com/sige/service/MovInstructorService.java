package com.sige.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.MovInstructor;
import com.sige.model.Usuario;
import com.sige.repository.MovInstructores;
import com.sige.repository.Usuarios;
import com.sige.repository.filter.MovInstructorFilter;

@Service
public class MovInstructorService {
   @Autowired
   private MovInstructores movInstructores;
   @Autowired
   private Usuarios usuarios;

   public List<MovInstructor> buscarMovimiento(MovInstructorFilter movInstructorFilter, String nombreUsuario) {
      Optional<Usuario> usuario = this.usuarios.findByNombre(nombreUsuario);
      if (usuario.get().isSupervisor()) {
         return this.movInstructores.buscarMovInstructor(movInstructorFilter);
      } else {
         movInstructorFilter.setInstructor(usuario.get().getInstructor());
         return this.movInstructores.buscarPorInstructor(movInstructorFilter);
      }
   }

   public BigDecimal totalesMov(MovInstructorFilter movInstructorFilter, String nombreUsuario) {
      Optional<Usuario> usuario = this.usuarios.findByNombre(nombreUsuario);
      if (usuario.get().isSupervisor()) {
         return this.movInstructores.totalMovInstructor(movInstructorFilter);
      } else {
         movInstructorFilter.setInstructor(usuario.get().getInstructor());
         return this.movInstructores.totalPorInstructor(movInstructorFilter);
      }
   }
}

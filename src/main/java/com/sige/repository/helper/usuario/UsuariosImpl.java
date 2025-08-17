package com.sige.repository.helper.usuario;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Usuario;

public class UsuariosImpl implements UsuariosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public Optional<Usuario> buscarPorNombreActivo(String nombre) {
      return this.manager
         .createQuery("from Usuario where  lower(nombre) = lower(:nombre) and activo = true", Usuario.class)
         .setParameter("nombre", nombre)
         .getResultList()
         .stream()
         .findFirst();
   }

   @Override
   public List<String> permisos(Usuario usuario) {
      return this.manager
         .createQuery("select distinct p.nombre from Usuario u inner join u.grupos g inner join g.permisos p where u = :usuario", String.class)
         .setParameter("usuario", usuario)
         .getResultList();
   }

   @Override
   public List<Usuario> buscarPorNombre(Usuario usuario) {
      return this.manager
         .createQuery("from Usuario where nombre like :nombre", Usuario.class)
         .setParameter("nombre", "%" + usuario.getNombre() + "%")
         .getResultList();
   }

   @Override
   public Usuario buscarUsuario(String nombre) {
      return (Usuario)this.manager.createQuery("from Usuario where nombre = :nombre", Usuario.class).setParameter("nombre", nombre).getSingleResult();
   }
}

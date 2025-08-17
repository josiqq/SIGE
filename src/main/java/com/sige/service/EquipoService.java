package com.sige.service;

import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.sige.model.Equipo;
import com.sige.repository.Equipos;
import com.sige.service.event.equipo.EquipoGuardaEvent;
import com.sige.service.exeption.NegocioException;
import com.sige.storage.FotoStorage;

@Service
public class EquipoService {
   @Autowired
   private Equipos equipos;
   @Autowired
   private ApplicationEventPublisher publisher;
   @Autowired
   private FotoStorage fotoStorage;

   public void guardar(Equipo equipo) {
      this.equipos.save(equipo);
      this.publisher.publishEvent(new EquipoGuardaEvent(equipo));
   }

   public List<Equipo> buscarTodos() {
      return this.equipos.findAll();
   }

   public Equipo buscarPorId(Long id) {
      return (Equipo)this.equipos.findById(id).orElse(null);
   }

   public void eliminar(Equipo equipo) {
      try {
         String foto = equipo.getFoto();
         this.equipos.delete(equipo);
         this.equipos.flush();
         this.fotoStorage.eliminar(foto);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento");
      }
   }

   public List<Equipo> buscarPorNombre(Equipo equipo) {
      return this.equipos.BuscarPorNombre(equipo);
   }

   public String buscarFoto(Long id) {
      return this.equipos.buscarFoto(id);
   }
}

package com.sige.repository.helper.equipo;

import java.util.List;

import com.sige.model.Equipo;

public interface EquiposQueries {
   List<Equipo> BuscarPorNombre(Equipo equipo);

   String buscarFoto(Long id);
}

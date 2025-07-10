package com.meta.repository.helper.equipo;

import com.meta.modelo.Equipo;
import java.util.List;

public interface EquiposQueries {
   List<Equipo> BuscarPorNombre(Equipo equipo);

   String buscarFoto(Long id);
}

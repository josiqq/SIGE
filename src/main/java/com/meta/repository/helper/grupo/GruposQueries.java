package com.meta.repository.helper.grupo;

import com.meta.modelo.Grupo;
import java.util.List;

public interface GruposQueries {
   List<Grupo> buscarPorNombre(Grupo grupo);
}

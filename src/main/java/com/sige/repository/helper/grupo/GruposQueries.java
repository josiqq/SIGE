package com.sige.repository.helper.grupo;

import java.util.List;

import com.sige.model.Grupo;

public interface GruposQueries {
   List<Grupo> buscarPorNombre(Grupo grupo);
}

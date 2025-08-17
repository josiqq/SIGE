package com.sige.repository.helper.timbrado;

import java.util.List;
import java.util.Optional;

import com.sige.model.Timbrado;

public interface TimbradosQueries {
   List<Timbrado> getTimbrado(Timbrado timbrado);

   Optional<Timbrado> optionalTimbrado(Timbrado timbrado);

   List<Timbrado> getTimbradosActivos();
}

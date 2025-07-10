package com.meta.repository.helper.timbrado;

import com.meta.modelo.Timbrado;
import java.util.List;
import java.util.Optional;

public interface TimbradosQueries {
   List<Timbrado> getTimbrado(Timbrado timbrado);

   Optional<Timbrado> optionalTimbrado(Timbrado timbrado);

   List<Timbrado> getTimbradosActivos();
}

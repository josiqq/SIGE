package com.sige.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PlantillaService {
   private final Environment env;

   public PlantillaService(Environment env) {
      this.env = env;
   }

   public String cargarPlantilla(String nombrePlantilla) throws IOException {
      String carpeta = this.env.getProperty("IMPRESION");
      String rutaCompleta = carpeta + File.separator + nombrePlantilla;
      Path plantillaPath = Path.of(rutaCompleta);
      return Files.readString(plantillaPath);
   }
}

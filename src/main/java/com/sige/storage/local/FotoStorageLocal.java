package com.sige.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.sige.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage {
   private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
   private Path local;
   private Path localTemporal;

   public FotoStorageLocal() {
      this(FileSystems.getDefault().getPath(System.getenv("HOME"), ".metafotos"));
   }

   public FotoStorageLocal(Path path) {
      this.local = path;
      this.crearCarpetas();
   }

   @Override
   public String salvarTemporalmente(MultipartFile[] files) {
      String nuevoNombre = null;
      if (files != null && files.length > 0) {
         MultipartFile archivo = files[0];
         nuevoNombre = this.renombrarArchivo(archivo.getOriginalFilename());

         try {
            archivo.transferTo(new File(this.localTemporal.toAbsolutePath().toString() + FileSystems.getDefault().getSeparator() + nuevoNombre));
         } catch (IOException var5) {
            throw new RuntimeException("Error guardando la foto temporal", var5);
         }
      }

      return nuevoNombre;
   }

   @Override
   public byte[] recuperarFotoTemporal(String nombre) {
      try {
         return Files.readAllBytes(this.localTemporal.resolve(nombre));
      } catch (IOException var3) {
         throw new RuntimeException("Error al leer la foto temporal", var3);
      }
   }

   @Override
   public void guardar(String foto) {
      System.out.println("Llego hasta fotoStorageLocal--" + foto);

      try {
         Files.move(this.localTemporal.resolve(foto), this.local.resolve(foto));
      } catch (IOException var4) {
         throw new RuntimeException("error guardando foto!", var4);
      }

      try {
         Thumbnails.of(new String[]{this.local.resolve(foto).toString()}).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
         Thumbnails.of(new String[]{this.local.resolve(foto).toString()}).size(400, 600).toFiles(Rename.NO_CHANGE);
      } catch (IOException var3) {
         throw new RuntimeException("error generando thumbnail!", var3);
      }
   }

   @Override
   public byte[] recuperarFoto(String nombre) {
      try {
         return Files.readAllBytes(this.local.resolve(nombre));
      } catch (IOException var3) {
         throw new RuntimeException("Error al leer la foto", var3);
      }
   }

   @Override
   public void eliminar(String foto) {
      try {
         Files.deleteIfExists(this.local.resolve(foto));
         Files.deleteIfExists(this.local.resolve("thumbnail." + foto));
      } catch (IOException var3) {
         logger.warn(String.format("error eliminando foto", var3.getMessage()));
      }
   }

   private void crearCarpetas() {
      try {
         Files.createDirectories(this.local);
         this.localTemporal = FileSystems.getDefault().getPath(this.local.toString(), "temp");
         Files.createDirectories(this.localTemporal);
         if (logger.isDebugEnabled()) {
            logger.debug("Caretas creadas para guardar fotos.");
            logger.debug("Carpeta default: " + this.local.toAbsolutePath());
            logger.debug("Carpeta temporal: " + this.localTemporal.toAbsolutePath());
         }
      } catch (IOException var2) {
         throw new RuntimeException("Eroor creando las carpetas para guardar fotos", var2);
      }
   }

   private String renombrarArchivo(String nombreOriginal) {
      String nuevoNombre = UUID.randomUUID().toString() + "_" + nombreOriginal;
      if (logger.isDebugEnabled()) {
         logger.debug(String.format("Nombre Original: %s, Nuevo nombre: %s", nombreOriginal, nuevoNombre));
      }

      return nuevoNombre;
   }
}

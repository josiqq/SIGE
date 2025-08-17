package com.sige.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
   String salvarTemporalmente(MultipartFile[] files);

   byte[] recuperarFotoTemporal(String nombre);

   void guardar(String foto);

   byte[] recuperarFoto(String nombre);

   void eliminar(String foto);
}

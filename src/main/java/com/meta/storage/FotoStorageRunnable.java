package com.meta.storage;

import com.meta.dto.FotoDTO;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

public class FotoStorageRunnable implements Runnable {
   private MultipartFile[] files;
   private DeferredResult<FotoDTO> resultado;
   private FotoStorage fotoStorage;

   public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage) {
      this.files = files;
      this.resultado = resultado;
      this.fotoStorage = fotoStorage;
   }

   @Override
   public void run() {
      String nombreFoto = this.fotoStorage.salvarTemporalmente(this.files);
      String contentType = this.files[0].getContentType();
      this.resultado.setResult(new FotoDTO(nombreFoto, contentType));
   }
}

package com.meta.controller;

import com.meta.dto.FotoDTO;
import com.meta.storage.FotoStorage;
import com.meta.storage.FotoStorageRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/fotos"})
public class FotosController {
   @Autowired
   private FotoStorage fotoStorage;

   @PostMapping
   public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
      DeferredResult<FotoDTO> resultado = new DeferredResult();
      Thread thread = new Thread(new FotoStorageRunnable(files, resultado, this.fotoStorage));
      thread.start();
      System.out.println("upload--->" + files[0].getSize());
      return resultado;
   }

   @GetMapping({"/temp/{nombre:.*}"})
   public byte[] recuperarFotoTemporal(@PathVariable String nombre) {
      return this.fotoStorage.recuperarFotoTemporal(nombre);
   }

   @GetMapping({"/{nombre:.*}"})
   public byte[] recuperarFoto(@PathVariable String nombre) {
      return this.fotoStorage.recuperarFoto(nombre);
   }
}

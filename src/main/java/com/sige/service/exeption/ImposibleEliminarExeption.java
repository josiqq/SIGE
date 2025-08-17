package com.sige.service.exeption;

public class ImposibleEliminarExeption extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public ImposibleEliminarExeption(String msg) {
      super(msg);
   }
}

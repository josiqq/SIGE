package com.sige.model;

public enum Tabla {
   VENTA("Venta"),
   ITEM_VENTA("item_venta"),
   COMPRA("compra"),
   ITEM_COMPRA("item_compra"),
   COBRO("cobro"),
   ITEM_COBRO("item_cobro"),
   ITEM_COBRO_IMPORTE("item_cobro_importe"),
   PRODUCTO("producto"),
   ITEM_AJUSTE_PRECIO("item_ajuste_precio"),
   PAGO("pago"),
   ITEM_PAGO("item_pago"),
   ITEM_PAGO_IMPORTE("item_pago_importe"),
   ITEM_AJUSTE_STOCK("item_ajuste_stock");

   private String descripcion;

   private Tabla(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getDescripcion() {
      return this.descripcion;
   }
}

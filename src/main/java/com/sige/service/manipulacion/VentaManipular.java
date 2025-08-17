package com.sige.service.manipulacion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.stereotype.Component;

import com.sige.model.ItemVenta;
import com.sige.model.ItemVentaLote;
import com.sige.model.Venta;
import com.sige.service.exeption.NegocioException;

@Component
public class VentaManipular {
   public void itemVentaManipular(Venta venta) {
      BigDecimal total = BigDecimal.ZERO;
      List<ItemVentaLote> itemsLotes = venta.getItemsLote();
      List<ItemVentaLote> itemsLotes2 = new ArrayList<>();
      List<ItemVenta> itemsVentas = venta.getItems();

      for (ItemVenta itemVenta : venta.getItems()) {
         if (itemVenta.getProducto().isConLote()) {
            Optional<ItemVentaLote> itemOP = itemsLotes.stream().filter(i -> i.getProducto().equals(itemVenta.getProducto())).findAny();
            if (itemOP.isEmpty()) {
               throw new NegocioException(
                  "Debe informar lote para este producto :  (" + itemVenta.getProducto().getCodigo() + ") " + itemVenta.getProducto().getNombre()
               );
            }

            total = itemsLotes.stream()
               .filter(i -> i.getProducto().equals(itemVenta.getProducto()))
               .map(ItemVentaLote::getCantidad)
               .reduce(BigDecimal.ZERO, BigDecimal::add);
            if (itemVenta.getCantidad().compareTo(total) != 0) {
               throw new NegocioException(
                  "Este producto: "
                     + itemVenta.getProducto().getCodigo()
                     + " no coincide con el lote ,cantidad item = "
                     + itemVenta.getCantidad().setScale(0, RoundingMode.HALF_UP)
                     + " ,cantidad lote = "
                     + total.setScale(0, RoundingMode.HALF_UP)
               );
            }
         }
      }

      for (ItemVentaLote itemVentaLote : itemsLotes) {
         new ItemVentaLote();
         Optional<ItemVenta> itemVentaOP = itemsVentas.stream().filter(i -> i.getProducto().equals(itemVentaLote.getProducto())).findAny();
         if (itemVentaOP.isEmpty()) {
            itemsLotes2.add(itemVentaLote);
         }
      }

      for (ItemVentaLote itemVentaLote2 : itemsLotes2) {
         int indice = IntStream.range(0, itemsLotes.size())
            .filter(i -> itemsLotes.get(i).getProducto().equals(itemVentaLote2.getProducto()))
            .findFirst()
            .orElse(-1);
         itemsLotes.remove(indice);
      }
   }
}

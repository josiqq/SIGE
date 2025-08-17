package com.sige.repository.helper.producto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sige.dto.InventarioDTO;
import com.sige.dto.ProductoCotizadoDTO;
import com.sige.dto.ProductoDTO;
import com.sige.dto.ProductoMobileDTO;
import com.sige.dto.ProductoStockDTO;
import com.sige.dto.StockValorizadoDTO;
import com.sige.model.Producto;
import com.sige.repository.filter.ProductoFilter;

public interface ProductosQueries {
   byte[] recuperaImg(Long id);

   Producto buscarPorCodigo(String codigo);

   List<ProductoDTO> buscarTodo(String nombreCodigo);

   List<Producto> buscarProductoPg(Integer pagina, Integer limit);

   List<Producto> buscaPorNombreOCodigo(ProductoFilter productoFilter);

   List<Producto> buscarTodoActivo();

   Long cantidadProducto();

   List<Producto> buscarPorNombreOCodigo(String nombreOcodigo);

   void recalcularStock(Long producto, Long deposito);

   List<ProductoMobileDTO> getProductosScroll(Integer pagina, Integer limit, String nombreCodigo, BigInteger deposito, BigInteger precio);

   List<InventarioDTO> getInventario(Long deposito, Long marca);

   List<StockValorizadoDTO> getStockValorizado(Long deposito, Long precio);

   List<ProductoStockDTO> getProductoStockDTO(String nombreCodigo, Long deposito, Long precio);

   ProductoStockDTO getProductoStockByCodigo(String codigo, Long deposito, Long precio);

   Optional<Producto> getByCodigoOrCodigoAlternativo(String codigo);

   List<ProductoCotizadoDTO> getProductosCotizados(Long monedaDestino, LocalDate fecha, String nombreCodigo);

   Producto recuperaPorCodigo(String codigo);

   List<Producto> getProductoLotes(String nombreCodigo);

   Producto getProductoLoteByCodigo(String codigo);

   List<Producto> getAllProductosActivos();
}

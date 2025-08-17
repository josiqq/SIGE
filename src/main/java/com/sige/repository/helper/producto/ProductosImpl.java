package com.sige.repository.helper.producto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.sige.dto.InventarioDTO;
import com.sige.dto.InventarioMapper;
import com.sige.dto.ProductoCotizadoDTO;
import com.sige.dto.ProductoCotizadoDTOMapper;
import com.sige.dto.ProductoDTO;
import com.sige.dto.ProductoMobileDTO;
import com.sige.dto.ProductoMobileMapper;
import com.sige.dto.ProductoStockDTO;
import com.sige.dto.ProductoStockDTOMapper;
import com.sige.dto.StockValorizadoDTO;
import com.sige.dto.StockValorizadoMapper;
import com.sige.model.Producto;
import com.sige.repository.filter.ProductoFilter;

public class ProductosImpl implements ProductosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public byte[] recuperaImg(Long id) {
      return (byte[])this.manager.createQuery("select foto from Producto where id = :id", byte[].class).setParameter("id", id).getSingleResult();
   }

   @Override
   public List<Producto> buscaPorNombreOCodigo(ProductoFilter productoFilter) {
      if (productoFilter.getLimite() == null) {
         productoFilter.setLimite(10);
      }

      return this.manager
         .createQuery(
            "select distinct p from Producto p left join p.items i where (p.nombre like :nombre or :nombre is null) or(p.codigo = :codigo or :codigo is null) or(i.codigoAlternativo = :codigo or :codigo is null)",
            Producto.class
         )
         .setParameter("nombre", "%" + productoFilter.getNombreCodigo() + "%")
         .setParameter("codigo", productoFilter.getNombreCodigo())
         .setMaxResults(productoFilter.getLimite())
         .getResultList();
   }

   @Override
   public Producto buscarPorCodigo(String codigo) {
      String jpql = " select distinct p from Producto p left join p.items i  where p.codigo = :codigo or i.codigoAlternativo = :codigo";
      return (Producto)this.manager.createQuery(jpql, Producto.class).setParameter("codigo", codigo).getSingleResult();
   }

   @Override
   public List<ProductoDTO> buscarTodo(String nombreCodigo) {
      String jpql = "SELECT DISTINCT NEW com.sige.dto.ProductoDTO(p.id, p.nombre, p.codigo, p.pesable, p.foto, p.costo, p.marca) FROM Producto p LEFT JOIN p.items i WHERE (p.nombre LIKE :nombre OR p.codigo = :codigo OR i.codigoAlternativo = :codigo) AND p.activo = true";
      return this.manager
         .createQuery(jpql, ProductoDTO.class)
         .setParameter("nombre", "%" + nombreCodigo + "%")
         .setParameter("codigo", nombreCodigo)
         .getResultList();
   }

   @Override
   public List<Producto> buscarProductoPg(Integer pagina, Integer limit) {
      return this.manager.createQuery("from Producto", Producto.class).setFirstResult(pagina).setMaxResults(limit).getResultList();
   }

   @Override
   public Long cantidadProducto() {
      return (Long)this.manager.createQuery("select count(id) from Producto", Long.class).getSingleResult();
   }

   @Override
   public List<Producto> buscarTodoActivo() {
      return this.manager.createQuery("from Producto where activo = true", Producto.class).getResultList();
   }

   @Override
   public List<Producto> buscarPorNombreOCodigo(String nombreOcodigo) {
      String jpql = "select distinct p from Producto p left join p.items i  where (p.nombre like :nombre or p.codigo = :codigo or i.codigoAlternativo = : codigo) and activo = true";
      return this.manager
         .createQuery(jpql, Producto.class)
         .setParameter("nombre", "%" + nombreOcodigo + "%")
         .setParameter("codigo", nombreOcodigo)
         .getResultList();
   }

   @Override
   public void recalcularStock(Long producto, Long deposito) {
      StoredProcedureQuery query = this.manager
         .createStoredProcedureQuery("pr_calcular_stock")
         .registerStoredProcedureParameter("a_producto", Long.class, ParameterMode.IN)
         .registerStoredProcedureParameter("a_deposito", Long.class, ParameterMode.IN)
         .setParameter("a_producto", producto)
         .setParameter("a_deposito", deposito);
      query.execute();
   }

   @Override
   public List<ProductoMobileDTO> getProductosScroll(Integer pagina, Integer limit, String nombreCodigo, BigInteger deposito, BigInteger precio) {
      String sql = "select \tdistinct producto.id as id    ,producto.pesable as pesable    ,producto.codigo as codigo\t,producto.nombre as nombre    ,case\t\twhen producto.id_marca is null then\t\t\t'sin marca'        when producto.id_marca is not null then\t\t\t(select marca.nombre from marca where id = producto.id_marca)     end as marca    ,ifnull((select cantidad from stock_deposito where id_deposito = :deposito and id_producto = producto.id ),0) as cantidad    ,ifnull((select precio_producto from item_precio where id_precio = :precio and id_producto = producto.id),0) as precio    ,producto.costo    ,ifnull((select deposito.nombre from deposito where id =:deposito),'Sin deposito asignado') as deposito    ,if(producto.foto ='','sinfoto.png',producto.foto) as foto from \tproducto LEFT JOIN     item_producto_codigo AS ipc ON producto.id = ipc.id_producto  where (producto.codigo=:codigo) or (producto.nombre like :nombre or :nombre is null) or (ipc.codigo_alternativo = :codigo)";
      List<Object[]> result = this.manager
         .createNativeQuery(sql)
         .setParameter("nombre", "%" + nombreCodigo + "%")
         .setParameter("codigo", nombreCodigo)
         .setParameter("deposito", deposito)
         .setParameter("precio", precio)
         .setFirstResult(pagina)
         .setMaxResults(limit)
         .getResultList();
      ProductoMobileMapper productoMobileMapper = new ProductoMobileMapper();
      return productoMobileMapper.mapResultDelList(result);
   }

   @Override
   public List<InventarioDTO> getInventario(Long deposito, Long marca) {
      String sql = "select \tproducto.id     ,producto.codigo     ,producto.nombre     ,ifnull(marca.nombre,'Sin marca') as marca     ,producto.foto     ,producto.pesable     ,stock_deposito.cantidad from producto left join stock_deposito on stock_deposito.id_producto = producto.id \tleft join marca on producto.id_marca = marca.id     left join deposito on stock_deposito.id_deposito = deposito.id where \t(marca.id =:marca or :marca is null)    and (deposito.id = :deposito ) order by producto.codigo asc";
      List<Object[]> results = this.manager.createNativeQuery(sql).setParameter("marca", marca).setParameter("deposito", deposito).getResultList();
      InventarioMapper inventarioMapper = new InventarioMapper();
      return inventarioMapper.mapeoResults(results);
   }

   @Override
   public List<StockValorizadoDTO> getStockValorizado(Long deposito, Long precio) {
      String sql = "select \tproducto.id     ,producto.codigo     ,producto.nombre     ,producto.costo     ,item_precio.precio_producto     ,stock_deposito.cantidad     ,(producto.costo* stock_deposito.cantidad) as total_costo     ,(item_precio.precio_producto* stock_deposito.cantidad) as total_precio     ,((item_precio.precio_producto* stock_deposito.cantidad)-(producto.costo* stock_deposito.cantidad)) as utilidad  ,producto.pesable from producto \t\tleft join item_precio on producto.id = item_precio.id_producto         left join stock_deposito on producto.id = stock_deposito.id_producto where stock_deposito.id_deposito =:deposito \tand item_precio.id_precio = :precio ";
      List<Object[]> results = this.manager.createNativeQuery(sql).setParameter("deposito", deposito).setParameter("precio", precio).getResultList();
      StockValorizadoMapper stockValorizadoMapper = new StockValorizadoMapper();
      return stockValorizadoMapper.mapperResults(results);
   }

   @Override
   public List<ProductoStockDTO> getProductoStockDTO(String nombreCodigo, Long deposito, Long precio) {
      String sql = "SELECT DISTINCT     producto.id,     producto.codigo,     producto.nombre,     producto.costo,     producto.pesable,     CASE         WHEN producto.foto = '' THEN 'sinfoto.png'         ELSE producto.foto     END AS foto,     marca.nombre AS marca,     IFNULL(item_precio.precio_minimo, 0) AS precio_minimo,     IFNULL(item_precio.precio_producto, 0) AS precio,     IFNULL(stock_deposito.cantidad, 0) AS cantidad,     IFNULL(deposito.nombre, 'Sin deposito') AS deposito   ,producto.con_lote, producto.stock_minimo FROM     producto LEFT JOIN     marca ON producto.id_marca = marca.id LEFT JOIN     item_producto_codigo AS ipc ON producto.id = ipc.id_producto LEFT JOIN     item_precio ON producto.id = item_precio.id_producto AND item_precio.id_precio = :precio LEFT JOIN     stock_deposito ON producto.id = stock_deposito.id_producto AND stock_deposito.id_deposito = :deposito LEFT JOIN     deposito ON stock_deposito.id_deposito = deposito.id WHERE     producto.activo = true     AND (producto.nombre LIKE :nombre OR producto.codigo = :codigo OR ipc.codigo_alternativo = :codigo)";
      List<Object[]> results = this.manager
         .createNativeQuery(sql)
         .setParameter("nombre", "%" + nombreCodigo + "%")
         .setParameter("codigo", nombreCodigo)
         .setParameter("precio", precio)
         .setParameter("deposito", deposito)
         .getResultList();
      ProductoStockDTOMapper mapper = new ProductoStockDTOMapper();
      return mapper.mapperResults(results);
   }

   @Override
   public ProductoStockDTO getProductoStockByCodigo(String codigo, Long deposito, Long precio) {
      String sql = "select  \t DISTINCT producto.id     ,producto.codigo     ,producto.nombre     ,producto.costo     ,producto.pesable     ,case \t\twhen producto.foto = '' then \t\t\t'sinfoto.png' \t\telse \t\t\tproducto.foto     end as foto     ,marca.nombre as marca     ,ifnull(item_precio.precio_minimo,0) as precio_minimo     ,ifnull(item_precio.precio_producto,0) as precio     ,ifnull(stock_deposito.cantidad,0) as cantidad     ,ifnull(deposito.nombre,'Sin deposito') as deposito    ,producto.con_lote ,producto.stock_minimo from producto left join marca on producto.id_marca = marca.id \tleft join item_precio on producto.id = item_precio.id_producto and item_precio.id_precio = :precio  LEFT JOIN     item_producto_codigo AS ipc ON producto.id = ipc.id_producto     left join stock_deposito on producto.id = stock_deposito.id_producto and stock_deposito.id_deposito = :deposito      left join deposito on stock_deposito.id_deposito = deposito.id  where producto.activo = true  and  (producto.codigo = :codigo or  producto.nombre = :codigo OR ipc.codigo_alternativo = :codigo) ";

      try {
         Object[] result = (Object[])this.manager
            .createNativeQuery(sql)
            .setParameter("codigo", codigo)
            .setParameter("precio", precio)
            .setParameter("deposito", deposito)
            .getSingleResult();
         ProductoStockDTOMapper mapper = new ProductoStockDTOMapper();
         return mapper.mapperObject(result);
      } catch (NoResultException var7) {
         return new ProductoStockDTO();
      } catch (Exception var8) {
         return new ProductoStockDTO();
      }
   }

   @Override
   public Optional<Producto> getByCodigoOrCodigoAlternativo(String codigo) {
      String sql = "SELECT DISTINCT p FROM Producto p LEFT JOIN p.items i WHERE p.codigo = :codigo OR i.codigoAlternativo = :codigo";

      try {
         Producto producto = (Producto)this.manager.createQuery(sql, Producto.class).setParameter("codigo", codigo).getSingleResult();
         return Optional.of(producto);
      } catch (Exception var4) {
         return Optional.empty();
      }
   }

   @Override
   public List<ProductoCotizadoDTO> getProductosCotizados(Long monedaDestino, LocalDate fecha, String nombreCodigo) {
      String sql = "select producto.id,producto.nombre,producto.codigo, producto.pesable,producto.foto,f_cotizar(producto.id_moneda,:monedaDestino,:fecha,costo,0) as costo ,marca.nombre as marca from producto left join marca on marca.id =producto.id_marca where producto.nombre like :nombreCodigo or (codigo = :codigo )";
      List<Object[]> results = this.manager
         .createNativeQuery(sql)
         .setParameter("monedaDestino", monedaDestino)
         .setParameter("fecha", fecha)
         .setParameter("nombreCodigo", "%" + nombreCodigo + "%")
         .setParameter("codigo", nombreCodigo)
         .getResultList();
      ProductoCotizadoDTOMapper mapper = new ProductoCotizadoDTOMapper();
      return mapper.mapper(results);
   }

   @Override
   public Producto recuperaPorCodigo(String codigo) {
      String sql = "from Producto where codigo = :codigo";
      return (Producto)this.manager.createQuery(sql, Producto.class).setParameter("codigo", codigo).getSingleResult();
   }

   @Override
   public List<Producto> getProductoLotes(String nombreCodigo) {
      String sql = "from Producto where conLote = true and (nombre like :nombre or codigo = :codigo)";
      return this.manager
         .createQuery(sql, Producto.class)
         .setParameter("nombre", "%" + nombreCodigo + "%")
         .setParameter("codigo", nombreCodigo)
         .getResultList();
   }

   @Override
   public Producto getProductoLoteByCodigo(String codigo) {
      String sql = "from Producto where codigo = :codigo and conLote = true";
      new Producto();

      Producto producto;
      try {
         producto = (Producto)this.manager.createQuery(sql, Producto.class).setParameter("codigo", codigo).getSingleResult();
      } catch (Exception var5) {
         producto = new Producto();
      }

      return producto;
   }

   @Override
   public List<Producto> getAllProductosActivos() {
      String sql = "from Producto where activo = true";
      return this.manager.createQuery(sql, Producto.class).getResultList();
   }
}

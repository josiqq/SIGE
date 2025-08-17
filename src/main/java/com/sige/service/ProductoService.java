package com.sige.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.sige.dto.ProductoDTO;
import com.sige.model.Auditoria;
import com.sige.model.ItemProductoCodigo;
import com.sige.model.Producto;
import com.sige.repository.Auditorias;
import com.sige.repository.Productos;
import com.sige.repository.filter.ProductoFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.event.producto.ProductoGuardaEvent;
import com.sige.service.exeption.NegocioException;
import com.sige.storage.FotoStorage;

@Service
public class ProductoService {
   @Autowired
   private Productos productos;
   @Autowired
   private ApplicationEventPublisher publisher;
   @Autowired
   private FotoStorage fotoStorage;
   @Autowired
   private Auditorias auditorias;

   public void guardar(Producto producto, UsuarioSistema usuarioSistema) {
      int upd = 0;
      if (producto.getId() != null) {
         upd = 1;
      }

      Random random = new Random();
      Optional<Producto> optionalCodigo = this.productos.getByCodigoOrCodigoAlternativo(producto.getCodigo());
      if (optionalCodigo.isPresent() && !optionalCodigo.get().getId().equals(producto.getId())) {
         throw new NegocioException("Este producto tiene el mismo codigo: " + optionalCodigo.get().getNombre());
      } else {
         for (ItemProductoCodigo item : producto.getItems()) {
            Optional<Producto> productoOP = this.productos.getByCodigoOrCodigoAlternativo(item.getCodigoAlternativo());
            if (productoOP.isPresent() && !productoOP.get().equals(producto)) {
               throw new NegocioException(
                  "Ya existe un producto con este codigo alternativo: "
                     + item.getCodigoAlternativo()
                     + " Este es el codigo principal del producto: "
                     + productoOP.get().getCodigo()
               );
            }
         }

         if (producto.getId() != null) {
            Optional<Producto> productoOptional = this.productos.findById(producto.getId());
            producto.setCodigo(productoOptional.get().getCodigo());
         }

         if (StringUtils.isEmpty(producto.getCodigo())) {
            producto.setCodigo(String.valueOf(random.nextInt(800000000) + 1));
         }

         this.productos.save(producto);
         this.agregarAuditoria(producto, usuarioSistema, upd);
         this.publisher.publishEvent(new ProductoGuardaEvent(producto));
      }
   }

   public List<Producto> buscarTodos() {
      return this.productos.findAll();
   }

   public byte[] recuperaImg(Long id) {
      return this.productos.recuperaImg(id);
   }

   public Producto buscarPorId(Long id) {
      return (Producto)this.productos.findById(id).orElse(null);
   }

   public List<Producto> buscaPorNombreOCodigo(ProductoFilter productoFilter) {
      return this.productos.buscaPorNombreOCodigo(productoFilter);
   }

   public Producto buscarPorCodigo(String codigo) {
      return this.productos.buscarPorCodigo(codigo);
   }

   public void eliminar(Producto producto, UsuarioSistema usuarioSistema) {
      String foto = producto.getFoto();
      this.productos.delete(producto);
      this.productos.flush();
      this.agregarAuditoria(producto, usuarioSistema, 2);
      if (!foto.isEmpty()) {
         this.fotoStorage.eliminar(foto);
      }
   }

   public List<ProductoDTO> buscarTodo(String nombreCodiogo) {
      return this.productos.buscarTodo(nombreCodiogo);
   }

   public List<Producto> buscarProductoPg(Integer pagina, Integer limit) {
      return this.productos.buscarProductoPg(pagina, limit);
   }

   private void agregarAuditoria(Producto producto, UsuarioSistema usuarioSistema, int upd) {
      String tipo = "";
      if (upd == 0) {
         tipo = "INSERT";
      } else if (upd == 1) {
         tipo = "UPDATE";
      } else if (upd == 2) {
         tipo = "DELETE";
      }

      Auditoria auditoria = new Auditoria();
      auditoria.setRegistro(producto.getId());
      auditoria.setTabla("PRODUCTO");
      auditoria.setFecha(LocalDate.now());
      auditoria.setHora(LocalTime.now());
      auditoria.setTipo(tipo);
      auditoria.setCodigo(producto.getCodigo());
      auditoria.setProducto(producto.getNombre());
      auditoria.setUsuario(usuarioSistema.getUsername());
      this.auditorias.save(auditoria);
   }
}

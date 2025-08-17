package com.sige.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.dto.ProductoCotizadoDTO;
import com.sige.dto.ProductoDTO;
import com.sige.dto.ProductoMobileDTO;
import com.sige.dto.ProductoStockDTO;
import com.sige.model.CostoCalculo;
import com.sige.model.ItemProductoCodigo;
import com.sige.model.Parametro;
import com.sige.model.Producto;
import com.sige.repository.Marcas;
import com.sige.repository.Monedas;
import com.sige.repository.Parametros;
import com.sige.repository.Productos;
import com.sige.repository.filter.ProductoFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.ProductoService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemProductoCodigoSession;

@Controller
@RequestMapping({"/productos"})
public class ProductoController {
   @Autowired
   private ProductoService productoService;
   @Autowired
   private Marcas marcas;
   @Autowired
   private Productos productos;
   @Autowired
   private TablaItemProductoCodigoSession tablaItemProductoCodigoSession;
   @Autowired
   private Monedas monedas;
   @Autowired
   private Parametros parametros;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @InitBinder
   protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
      binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
   }

   @GetMapping
   public ModelAndView inicio(Producto producto, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("producto/producto");
      this.agregarUUID(producto);
      mv.addObject(producto);
      mv.addObject("marcas", this.marcas.findAll());
      mv.addObject("costoCalculos", CostoCalculo.values());
      mv.addObject("monedas", this.monedas.findAll());
      this.agregarUsuario(producto, usuarioSistema);
      mv.addObject("items", this.tablaItemProductoCodigoSession.getItems(producto.getUuid()));
      this.adicionarDatosParametrizados(producto);
      return mv;
   }

   private void adicionarDatosParametrizados(Producto producto) {
      Parametro parametro = this.parametros.getParametro();
      if (producto.getMoneda() == null) {
         producto.setMoneda(parametro.getMoneda());
      }
   }

   private void agregarUsuario(Producto producto, UsuarioSistema usuarioSistema) {
      if (producto.getUsuario() == null) {
         producto.setUsuario(usuarioSistema.getUsuario());
      }
   }

   private void agregarUUID(Producto producto) {
      if (StringUtils.isEmpty(producto.getUuid())) {
         producto.setUuid(UUID.randomUUID().toString());
      }
   }

   @PostMapping
   public ModelAndView guardar(
      @Valid Producto producto, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema
   ) {
      ModelAndView mv = new ModelAndView("redirect:/productos");
      producto.cargarItem(this.tablaItemProductoCodigoSession.getItems(producto.getUuid()));
      if (result.hasErrors()) {
         return this.inicio(producto, usuarioSistema);
      } else {
         try {
            this.productoService.guardar(producto, usuarioSistema);
         } catch (NegocioException var7) {
            result.rejectValue("codigo", var7.getMessage(), var7.getMessage());
            return this.inicio(producto, usuarioSistema);
         }

         attributes.addFlashAttribute("mensaje", "Producto se guardó con éxito");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(ProductoFilter productoFilter) {
      ModelAndView mv = new ModelAndView("producto/buscarProducto");
      mv.addObject("productos", this.productoService.buscaPorNombreOCodigo(productoFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      Producto producto = this.productoService.buscarPorId(id);
      this.agregarUUID(producto);

      for (ItemProductoCodigo item : producto.getItems()) {
         this.tablaItemProductoCodigoSession.adicionarItem(item.getCodigoAlternativo(), producto.getUuid());
      }

      return this.inicio(producto, usuarioSistema);
   }

   @GetMapping({"/js/{codigo}"})
   @ResponseBody
   public Producto buscarPorCodigo(@PathVariable String codigo) {
      new Producto();

      Producto producto;
      try {
         producto = this.productoService.buscarPorCodigo(codigo);
         this.agregarUUID(producto);
      } catch (Exception var4) {
         producto = new Producto();
      }

      return producto;
   }

   @GetMapping({"/js/itemProductoCodigo"})
   public ModelAndView itemProductoCodigo(String codigo, String uuid) {
      Producto producto = this.productoService.buscarPorCodigo(codigo);

      for (ItemProductoCodigo item : producto.getItems()) {
         this.tablaItemProductoCodigoSession.adicionarItem(item.getCodigoAlternativo(), uuid);
      }

      ModelAndView mv = new ModelAndView("producto/itemProductoCodigo");
      mv.addObject("items", this.tablaItemProductoCodigoSession.getItems(uuid));
      return mv;
   }

   @RequestMapping(
      value = {"/image/{id}"},
      produces = {"image/png"}
   )
   public ResponseEntity<byte[]> mostrarImg(@PathVariable("id") Long id) {
      byte[] imageContent = this.productoService.recuperaImg(id);
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.IMAGE_PNG);
      return new ResponseEntity(imageContent, headers, HttpStatus.OK);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable("id") Producto producto, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      try {
         this.productoService.eliminar(producto, usuarioSistema);
      } catch (DataAccessException var6) {
         Throwable rootCause = var6.getRootCause();
         String messaError = "Error en la base de datos: " + rootCause.getMessage();
         return ResponseEntity.badRequest().body(messaError);
      }

      return ResponseEntity.ok().build();
   }

   @RequestMapping(
      consumes = {"application/json"}
   )
   @ResponseBody
   public List<ProductoDTO> buscarPorNombrCodigo(String nombreCodigo) {
      LocalDateTime ahora = LocalDateTime.now();
      System.out.println("--------------------------------------\nconsulta a la base de datos: " + ahora + "\n--------------------------------------");
      return this.productoService.buscarTodo(nombreCodigo);
   }

   @GetMapping({"/pagina"})
   @ResponseBody
   public List<Producto> buscarProductosPg(Integer pagina, Integer limit) {
      return this.productoService.buscarProductoPg(pagina, limit);
   }

   @GetMapping({"/pagina/cantidad"})
   @ResponseBody
   public Long cantidadProducto() {
      return this.productos.cantidadProducto();
   }

   @GetMapping({"/listaProductos"})
   public ModelAndView listaProducto() {
      return new ModelAndView("producto/listaProducto");
   }

   @GetMapping({"/buscarPorNombreOCodigo"})
   @ResponseBody
   public List<Producto> buscarPorNombreOCodigo(String nombreCodigo) {
      return this.productos.buscarPorNombreOCodigo(nombreCodigo);
   }

   @GetMapping({"/buscarPorId/{id}"})
   @ResponseBody
   public Producto buscarPorIdjs(@PathVariable Long id) {
      return (Producto)this.productos.findById(id).orElse(null);
   }

   @GetMapping({"/buscar/scroll"})
   @ResponseBody
   public List<ProductoMobileDTO> getProductoScroll(Integer pagina, Integer limite, String nombreCodigo, BigInteger deposito, BigInteger precio) {
      return this.productos.getProductosScroll(pagina, limite, nombreCodigo, deposito, precio);
   }

   @GetMapping({"/js/productoStock"})
   @ResponseBody
   public List<ProductoStockDTO> getPrtoductoStockDTO(String nombreCodigo, Long deposito, Long precio) {
      return this.productos.getProductoStockDTO(nombreCodigo, deposito, precio);
   }

   @GetMapping({"/js/productoStockByCodigo"})
   @ResponseBody
   public ProductoStockDTO getPrtoductoStockByCodigo(String codigo, Long deposito, Long precio) {
      ProductoStockDTO producto = this.productos.getProductoStockByCodigo(codigo, deposito, precio);
      return this.productos.getProductoStockByCodigo(codigo, deposito, precio);
   }

   @PostMapping({"/js/adiciona/item"})
   public ModelAndView adicionarItem(String codigoAlternativo, String uuid) {
      ModelAndView mv = new ModelAndView("producto/itemProductoCodigo");
      this.tablaItemProductoCodigoSession.adicionarItem(codigoAlternativo, uuid);
      mv.addObject("items", this.tablaItemProductoCodigoSession.getItems(uuid));
      return mv;
   }

   @DeleteMapping({"/js/eliminar/item"})
   public ModelAndView eliminarItem(int indice, String uuid) {
      ModelAndView mv = new ModelAndView("producto/itemProductoCodigo");
      this.tablaItemProductoCodigoSession.eliminarItem(indice, uuid);
      mv.addObject("items", this.tablaItemProductoCodigoSession.getItems(uuid));
      return mv;
   }

   @GetMapping({"/js/productosCotizados"})
   @ResponseBody
   public List<ProductoCotizadoDTO> getProductosCotizados(Long monedaDestino, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, String nombreCodigo) {
      return this.productos.getProductosCotizados(monedaDestino, fecha, nombreCodigo);
   }

   @GetMapping({"/js/buscarTodo"})
   @ResponseBody
   public List<ProductoDTO> buscarTodo(String nombreCodigo) {
      return this.productoService.buscarTodo(nombreCodigo);
   }

   @GetMapping({"/js/getByCodigo"})
   @ResponseBody
   public Producto getByCodigo(String codigo) {
      new Producto();

      try {
         return this.productos.recuperaPorCodigo(codigo);
      } catch (Exception var4) {
         return new Producto();
      }
   }

   @GetMapping({"/js/buscarConLote"})
   @ResponseBody
   public ResponseEntity<?> getProductoLotes(String nombreCodigo) {
      try {
         List<Producto> result = this.productos.getProductoLotes(nombreCodigo);
         return ResponseEntity.ok(result);
      } catch (Exception var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }
   }

   @GetMapping({"/js/getByCodigo-lote"})
   @ResponseBody
   public ResponseEntity<?> getProductoConLoteByCodigo(String codigo) {
      try {
         Producto producto = this.productos.getProductoLoteByCodigo(codigo);
         return ResponseEntity.ok(producto);
      } catch (Exception var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }
   }

   @GetMapping({"/get-all-activos"})
   @ResponseBody
   public ResponseEntity<?> getAllProductosActivos() {
      List<Producto> listaProducto = new ArrayList<>();

      try {
         listaProducto = this.productos.getAllProductosActivos();
      } catch (DataAccessException var5) {
         Throwable rootCause = var5.getRootCause();
         String var4 = "ERROR DE LA BASE DE DATOS: " + rootCause.getMessage();
      }

      return ResponseEntity.ok(listaProducto);
   }
}

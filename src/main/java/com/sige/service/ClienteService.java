package com.sige.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.persistence.PersistenceException;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.sige.model.Cliente;
import com.sige.repository.Clientes;
import com.sige.repository.filter.ClienteFilter;
import com.sige.service.event.cliente.ClienteGuardaEvent;
import com.sige.service.exeption.ImposibleEliminarExeption;
import com.sige.service.exeption.NegocioException;
import com.sige.storage.FotoStorage;

@Service
public class ClienteService {
   @Autowired
   private Clientes clientes;
   @Autowired
   private ApplicationEventPublisher publisher;
   @Autowired
   private FotoStorage fotoStorage;

   public void guardar(Cliente cliente) {
      Random random = new Random();
      this.validaCliente(cliente);
      if (StringUtils.isEmpty(cliente.getDocumento())) {
         cliente.setDocumento(String.valueOf(random.nextInt(8000000) + 1));
      }

      if (cliente.getFoto() == null) {
         cliente.setFoto("");
         cliente.setContentType("");
      }

      this.clientes.save(cliente);
      System.out.println("-------------------------------\ninsersion de cliente: " + cliente.getNombre() + "\n---------------------------------");
      this.publisher.publishEvent(new ClienteGuardaEvent(cliente));
   }

   private void validaCliente(Cliente cliente) {
      Optional<Cliente> documentoExiste = this.clientes.findByDocumento(cliente.getDocumento());
      if (documentoExiste.isPresent() && !documentoExiste.get().equals(cliente)) {
         throw new NegocioException("Ya existe un cliente con el mismo número de Documento Doc: " + cliente.getDocumento());
      } else if (cliente.getPrecio() == null) {
         throw new NegocioException("Debe informar el precio!!");
      }
   }

   public void eliminar(Cliente cliente) {
      try {
         String foto = cliente.getFoto();
         this.clientes.delete(cliente);
         this.clientes.flush();
         this.fotoStorage.eliminar(foto);
      } catch (PersistenceException var3) {
         throw new ImposibleEliminarExeption("No se puede eliminar cliente ya tuvo movimiento!");
      }
   }

   public List<Cliente> buscar() {
      return this.clientes.findAll();
   }

   public Cliente buscarPorId(Long id) {
      return (Cliente)this.clientes.findById(id).orElse(null);
   }

   public List<Cliente> buscarPorNombreODocumento(String nombreDocumento) {
      return this.clientes.buscarPorNombreODocumento(nombreDocumento);
   }

   public Long cantidadCliente() {
      return this.clientes.cantidadCliente();
   }

   public List<Cliente> buscarCliente(ClienteFilter clienteFilter) {
      return this.clientes.buscarCliente(clienteFilter);
   }

   public BigDecimal buscarSaldo(Long id) {
      BigDecimal saldo = BigDecimal.ZERO;

      try {
         saldo = this.clientes.buscarSaldo(id);
      } catch (Exception var4) {
         saldo = BigDecimal.ZERO;
      }

      return saldo;
   }
}

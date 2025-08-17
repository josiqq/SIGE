package com.sige.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Cuenta;
import com.sige.repository.Cuentas;
import com.sige.service.exeption.NegocioException;

@Service
public class CuentaService {
   @Autowired
   private Cuentas cuentas;

   public void guardar(Cuenta cuenta) {
      if (cuenta.getTimbrado() != null) {
         Optional<Cuenta> cuentaOp = this.cuentas.getCuentaByTimbrado(cuenta.getTimbrado());
         if (cuentaOp.isPresent() && !cuentaOp.get().equals(cuenta)) {
            throw new NegocioException(
               "Este punto de expedición: " + cuentaOp.get().getTimbrado().getExpedicion() + " ya esta en uso en esta cuenta: " + cuentaOp.get().getNombre()
            );
         }
      }

      this.cuentas.save(cuenta);
   }

   public void eliminar(Long id) {
      try {
         this.cuentas.deleteById(id);
         this.cuentas.flush();
      } catch (PersistenceException var3) {
         throw new NegocioException("No se puede eliminar, ya tuvo movimiento!");
      }
   }

   public List<Cuenta> buscar() {
      return this.cuentas.findAll();
   }

   public Cuenta buscarPorId(Long id) {
      return (Cuenta)this.cuentas.findById(id).orElse(null);
   }
}

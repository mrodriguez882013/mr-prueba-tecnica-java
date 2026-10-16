package com.example.api_productos_proveedores.repositories;

import com.example.api_productos_proveedores.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    // Método necesario para que el servicio pueda validar correos duplicados
    Optional<Proveedor> findByCorreoElectronico(String correoElectronico);
}
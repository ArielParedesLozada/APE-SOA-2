// src/main/java/com/mycompany/frontend/domain/repository/VehiculoRepository.java
package com.mycompany.frontend.domain.repository;

import java.io.IOException;
import java.util.List;
import com.mycompany.frontend.domain.entity.Vehiculo;

/**
 * Contrato para operaciones CRUD de vehículos.
 */
public interface VehiculoRepository {
    /**
     * Lista todos los vehículos.
     */
    List<Vehiculo> findAll() throws IOException;

    /**
     * Busca un vehículo por su ID.
     */
    Vehiculo findById(int id) throws IOException;

    /**
     * Crea un nuevo vehículo.
     */
    boolean create(Vehiculo vehiculo) throws IOException;

    /**
     * Actualiza un vehículo existente.
     */
    boolean update(Vehiculo vehiculo) throws IOException;

    /**
     * Elimina un vehículo por su ID.
     */
    boolean delete(int id) throws IOException;
}

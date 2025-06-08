// src/main/java/com/mycompany/frontend/domain/repository/ModeloRepository.java
package com.mycompany.frontend.domain.repository;

import java.io.IOException;
import java.util.List;
import com.mycompany.frontend.domain.entity.Modelo;

public interface ModeloRepository {
    /**
     * Recupera todos los modelos asociados a una marca.
     * @param marcaId id de la marca
     * @throws IOException fallo de red o parseo JSON
     */
    List<Modelo> findByMarcaId(int marcaId) throws IOException;
}

// src/main/java/com/mycompany/frontend/domain/repository/MarcaRepository.java
package com.mycompany.frontend.domain.repository;

import java.io.IOException;
import java.util.List;
import com.mycompany.frontend.domain.entity.Marca;

public interface MarcaRepository {
    /**
     * Recupera todas las marcas desde el backend.
     * @throws IOException fallo de red o parseo JSON
     */
    List<Marca> findAll() throws IOException;
}

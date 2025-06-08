// src/main/java/com/mycompany/frontend/domain/repository/ColorRepository.java
package com.mycompany.frontend.domain.repository;

import java.io.IOException;
import java.util.List;
import com.mycompany.frontend.domain.entity.Color;

/**
 * Contrato para acceder a los colores.
 */
public interface ColorRepository {
    /**
     * Recupera todos los colores.
     * @return lista de colores
     * @throws IOException en caso de fallo de red o parseo
     */
    List<Color> findAll() throws IOException;
}

// src/main/java/com/mycompany/frontend/data/mapper/ModeloMapper.java
package com.mycompany.frontend.data.mapper;

import com.mycompany.frontend.data.dto.ModeloDTO;
import com.mycompany.frontend.domain.entity.Modelo;

public class ModeloMapper {
    public static Modelo fromDTO(ModeloDTO dto) {
        if (dto == null) return null;
        return new Modelo(dto.getId(), dto.getName());
    }
}

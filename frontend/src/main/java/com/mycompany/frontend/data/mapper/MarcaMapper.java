// src/main/java/com/mycompany/frontend/data/mapper/MarcaMapper.java
package com.mycompany.frontend.data.mapper;

import com.mycompany.frontend.data.dto.MarcaDTO;
import com.mycompany.frontend.domain.entity.Marca;

public class MarcaMapper {
    // ¡Este método debe existir y ser público y estático!
    public static Marca fromDTO(MarcaDTO dto) {
        if (dto == null) return null;
        return new Marca(dto.getId(), dto.getNombre());
    }
}

// ColorMapper.java
package com.mycompany.frontend.data.mapper;

import com.mycompany.frontend.data.dto.ColorDTO;
import com.mycompany.frontend.domain.entity.Color;

public class ColorMapper {
    public static Color fromDTO(ColorDTO dto) {
        if (dto == null) return null;
        return new Color(dto.getId(), dto.getName());
    }
}

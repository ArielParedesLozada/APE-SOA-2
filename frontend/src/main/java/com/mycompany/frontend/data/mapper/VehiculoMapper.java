// src/main/java/com/mycompany/frontend/data/mapper/VehiculoMapper.java
package com.mycompany.frontend.data.mapper;

import com.mycompany.frontend.data.dto.VehiculoDTO;
import com.mycompany.frontend.domain.entity.Vehiculo;

public class VehiculoMapper {
    public static Vehiculo fromDTO(VehiculoDTO dto) {
        if (dto == null) return null;

        var marca  = MarcaMapper.fromDTO(dto.getMarca());
        var modelo = ModeloMapper.fromDTO(dto.getModelo());
        var color  = ColorMapper.fromDTO(dto.getColor());

        return new Vehiculo(
            dto.getId(),
            dto.getPlaca(),
            dto.getChasis(),
            dto.getAnio(),
            marca,
            modelo,
            color
        );
    }
}

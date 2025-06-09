// src/main/java/com/mycompany/frontend/data/repository/ColorRepositoryImpl.java
package com.mycompany.frontend.data.repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.mycompany.frontend.data.dto.ColorDTO;
import com.mycompany.frontend.data.mapper.ColorMapper;
import com.mycompany.frontend.data.network.ApiService;
import com.mycompany.frontend.domain.entity.Color;
import com.mycompany.frontend.domain.repository.ColorRepository;

public class ColorRepositoryImpl implements ColorRepository {
    private final ApiService api;

    public ColorRepositoryImpl(ApiService api) {
        this.api = api;
    }

    @Override
    public List<Color> findAll() throws IOException {
        // Llamada GET a /waza según tu back
        List<ColorDTO> dtos = api.getList("/color", ColorDTO[].class);
        return dtos.stream()
                   .map(ColorMapper::fromDTO)
                   .collect(Collectors.toList());
    }
}

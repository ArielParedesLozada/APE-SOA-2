// src/main/java/com/mycompany/frontend/domain/usecase/GetAllColorsUseCase.java
package com.mycompany.frontend.domain.usecase;

import java.io.IOException;
import java.util.List;

import com.mycompany.frontend.domain.entity.Color;
import com.mycompany.frontend.domain.repository.ColorRepository;

public class GetAllColorsUseCase {
    private final ColorRepository repo;

    public GetAllColorsUseCase(ColorRepository repo) {
        this.repo = repo;
    }

    public List<Color> execute() throws IOException {
        return repo.findAll();
    }
}

// src/main/java/com/mycompany/frontend/domain/usecase/GetAllVehiculosUseCase.java
package com.mycompany.frontend.domain.usecase;

import java.io.IOException;
import java.util.List;

import com.mycompany.frontend.domain.entity.Vehiculo;
import com.mycompany.frontend.domain.repository.VehiculoRepository;

public class GetAllVehiculosUseCase {
    private final VehiculoRepository repo;

    public GetAllVehiculosUseCase(VehiculoRepository repo) {
        this.repo = repo;
    }

    public List<Vehiculo> execute() throws IOException {
        return repo.findAll();
    }
}

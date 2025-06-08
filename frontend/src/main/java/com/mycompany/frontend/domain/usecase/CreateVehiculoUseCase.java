// src/main/java/com/mycompany/frontend/domain/usecase/CreateVehiculoUseCase.java
package com.mycompany.frontend.domain.usecase;

import java.io.IOException;

import com.mycompany.frontend.domain.entity.Vehiculo;
import com.mycompany.frontend.domain.repository.VehiculoRepository;

public class CreateVehiculoUseCase {
    private final VehiculoRepository repo;

    public CreateVehiculoUseCase(VehiculoRepository repo) {
        this.repo = repo;
    }

    public boolean execute(Vehiculo vehiculo) throws IOException {
        return repo.create(vehiculo);
    }
}

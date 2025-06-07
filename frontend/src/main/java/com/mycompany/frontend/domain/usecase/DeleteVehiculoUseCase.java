// src/main/java/com/mycompany/frontend/domain/usecase/DeleteVehiculoUseCase.java
package com.mycompany.frontend.domain.usecase;

import java.io.IOException;

import com.mycompany.frontend.domain.repository.VehiculoRepository;

public class DeleteVehiculoUseCase {
    private final VehiculoRepository repo;

    public DeleteVehiculoUseCase(VehiculoRepository repo) {
        this.repo = repo;
    }

    public boolean execute(int id) throws IOException {
        return repo.delete(id);
    }
}

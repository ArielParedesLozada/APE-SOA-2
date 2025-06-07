package com.mycompany.frontend.domain.usecase;

import java.io.IOException;
import com.mycompany.frontend.domain.entity.Vehiculo;
import com.mycompany.frontend.domain.repository.VehiculoRepository;

public class UpdateVehiculoUseCase {
    private final VehiculoRepository repo;

    public UpdateVehiculoUseCase(VehiculoRepository repo) {
        this.repo = repo;
    }

    public boolean execute(Vehiculo vehiculo) throws IOException {
        return repo.update(vehiculo);
    }
}

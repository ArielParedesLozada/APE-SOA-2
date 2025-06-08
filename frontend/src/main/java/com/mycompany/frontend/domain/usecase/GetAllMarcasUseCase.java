package com.mycompany.frontend.domain.usecase;

import java.io.IOException;
import java.util.List;
import com.mycompany.frontend.domain.entity.Marca;
import com.mycompany.frontend.domain.repository.MarcaRepository;

public class GetAllMarcasUseCase {
    private final MarcaRepository repo;

    public GetAllMarcasUseCase(MarcaRepository repo) {
        this.repo = repo;
    }

    public List<Marca> execute() throws IOException {
        return repo.findAll();
    }
}

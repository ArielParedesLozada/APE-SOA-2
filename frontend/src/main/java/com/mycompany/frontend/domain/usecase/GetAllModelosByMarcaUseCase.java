package com.mycompany.frontend.domain.usecase;

import java.io.IOException;
import java.util.List;
import com.mycompany.frontend.domain.entity.Modelo;
import com.mycompany.frontend.domain.repository.ModeloRepository;

public class GetAllModelosByMarcaUseCase {
    private final ModeloRepository repo;

    public GetAllModelosByMarcaUseCase(ModeloRepository repo) {
        this.repo = repo;
    }

    public List<Modelo> execute(int marcaId) throws IOException {
        return repo.findByMarcaId(marcaId);
    }
}

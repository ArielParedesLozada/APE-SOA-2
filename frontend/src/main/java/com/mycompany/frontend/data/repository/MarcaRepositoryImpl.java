// MarcaRepositoryImpl.java
package com.mycompany.frontend.data.repository;
// …imports…

import com.mycompany.frontend.data.dto.MarcaDTO;
import com.mycompany.frontend.data.mapper.MarcaMapper;
import com.mycompany.frontend.data.network.ApiService;
import com.mycompany.frontend.domain.entity.Marca;
import com.mycompany.frontend.domain.repository.MarcaRepository;
import java.io.IOException;
import java.util.List;


public class MarcaRepositoryImpl implements MarcaRepository {
    private final ApiService api;
    public MarcaRepositoryImpl(ApiService api) { this.api = api; }

    @Override
    public List<Marca> findAll() throws IOException {
        List<MarcaDTO> dtos = api.getList("/marcas", MarcaDTO[].class);
        return dtos.stream().map(MarcaMapper::fromDTO).toList();
    }
}

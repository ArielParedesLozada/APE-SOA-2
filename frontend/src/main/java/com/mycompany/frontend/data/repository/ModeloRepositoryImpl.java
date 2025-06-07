// ModeloRepositoryImpl.java
package com.mycompany.frontend.data.repository;
// …imports…

import com.mycompany.frontend.data.dto.ModeloDTO;
import com.mycompany.frontend.data.mapper.ModeloMapper;
import com.mycompany.frontend.data.network.ApiService;
import com.mycompany.frontend.domain.entity.Modelo;
import com.mycompany.frontend.domain.repository.ModeloRepository;
import java.io.IOException;
import java.util.List;


public class ModeloRepositoryImpl implements ModeloRepository {
    private final ApiService api;
    public ModeloRepositoryImpl(ApiService api) { this.api = api; }

    @Override
    public List<Modelo> findByMarcaId(int marcaId) throws IOException {
        List<ModeloDTO> dtos = api.getList("/modelos?marcaId=" + marcaId, ModeloDTO[].class);
        return dtos.stream().map(ModeloMapper::fromDTO).toList();
    }
}

package com.mycompany.frontend.data.repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.mycompany.frontend.data.dto.VehiculoDTO;
import com.mycompany.frontend.data.mapper.VehiculoMapper;
import com.mycompany.frontend.data.network.ApiService;
import com.mycompany.frontend.domain.entity.Vehiculo;
import com.mycompany.frontend.domain.repository.VehiculoRepository;

public class VehiculoRepositoryImpl implements VehiculoRepository {
    private final ApiService api;

    public VehiculoRepositoryImpl(ApiService api) {
        this.api = api;
    }

    @Override
    public List<Vehiculo> findAll() throws IOException {
        // GET /vehiculos
        List<VehiculoDTO> dtos = api.getList("/vehiculos", VehiculoDTO[].class);
        return dtos.stream()
                   .map(VehiculoMapper::fromDTO)
                   .collect(Collectors.toList());
    }

    @Override
    public Vehiculo findById(int id) throws IOException {
        // GET /vehiculos/{id}
        VehiculoDTO dto = api.getOne("/vehiculos/" + id, VehiculoDTO.class);
        return VehiculoMapper.fromDTO(dto);
    }

    @Override
    public boolean create(Vehiculo v) throws IOException {
        // POST /vehiculos
        api.post("/vehiculos", v, Void.class);
        return true;
    }

    @Override
    public boolean update(Vehiculo v) throws IOException {
        // PUT /vehiculos/{id}
        api.put("/vehiculos/" + v.getId(), v, Void.class);
        return true;
    }

    @Override
    public boolean delete(int id) throws IOException {
        // DELETE /vehiculos/{id}
        return api.delete("/vehiculos/" + id);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.frontend.domain.usecase;

import java.io.IOException;
import java.util.List;

import com.mycompany.frontend.domain.entity.Modelo;
import com.mycompany.frontend.domain.repository.ModeloRepository;

/**
 *
 * @author Usuario
 */
public class GetAllModelosUseCase {
    private final ModeloRepository repo;

    public GetAllModelosUseCase(ModeloRepository repo) {
        this.repo = repo;
    }

    public List<Modelo> execute() throws IOException {
        return repo.findAll();
    }
}

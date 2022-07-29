package com.rsecinformation.cursomc.services;

import com.rsecinformation.cursomc.entities.Estado;
import com.rsecinformation.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAllByOrderByName();
    }
}

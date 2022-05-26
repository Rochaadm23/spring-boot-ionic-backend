package com.rsecinformation.cursomc.services;

import com.rsecinformation.cursomc.entities.Cliente;
import com.rsecinformation.cursomc.repositories.ClienteRepository;
import com.rsecinformation.cursomc.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}

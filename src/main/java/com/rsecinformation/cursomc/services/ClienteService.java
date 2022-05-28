package com.rsecinformation.cursomc.services;


import com.rsecinformation.cursomc.dto.ClienteDTO;
import com.rsecinformation.cursomc.entities.Cliente;
import com.rsecinformation.cursomc.repositories.ClienteRepository;
import com.rsecinformation.cursomc.services.exceptions.DataIntegrityException;
import com.rsecinformation.cursomc.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente insert(Cliente obj) {
        return clienteRepository.save(obj);
    }

    public Cliente update(Integer id, Cliente obj) {
        try {
            Cliente entity = clienteRepository.getById(id);
            updateData(entity, obj);
            return clienteRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Integer id) {
        try {
            clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um cliente por que há entidades relacionadas");
        }
    }

    private void updateData(Cliente entity, Cliente obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());

    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }
}

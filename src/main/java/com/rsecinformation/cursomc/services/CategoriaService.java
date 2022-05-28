package com.rsecinformation.cursomc.services;

import com.rsecinformation.cursomc.dto.CategoriaDTO;
import com.rsecinformation.cursomc.entities.Categoria;
import com.rsecinformation.cursomc.repositories.CategoriaRepository;
import com.rsecinformation.cursomc.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Categoria insert(Categoria obj) {
        return categoriaRepository.save(obj);
    }

    public Categoria update(Integer id, Categoria obj){
        try {
            Categoria entity = categoriaRepository.getById(id);
            updateData(entity, obj);
            return categoriaRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Categoria entity, Categoria obj) {
        entity.setName(obj.getName());

    }
    public Categoria fromDTO(CategoriaDTO objDto){
        return new Categoria(objDto.getId(), objDto.getName());
    }
}

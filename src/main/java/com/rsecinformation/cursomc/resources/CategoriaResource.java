package com.rsecinformation.cursomc.resources;

import com.rsecinformation.cursomc.dto.CategoriaDTO;
import com.rsecinformation.cursomc.entities.Categoria;
import com.rsecinformation.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        Categoria obj = categoriaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDTO> listDto = list.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @PostMapping
    public ResponseEntity<Categoria> insert(@RequestBody CategoriaDTO objDto) {
        Categoria obj = categoriaService.fromDTO(objDto);
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody CategoriaDTO objDto) {
        Categoria obj = categoriaService.fromDTO(objDto);
        obj = categoriaService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

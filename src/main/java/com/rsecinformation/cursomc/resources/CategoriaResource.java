package com.rsecinformation.cursomc.resources;

import com.rsecinformation.cursomc.entities.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @GetMapping
    public List<Categoria> print(){
        Categoria c1 = new Categoria(1, "Informática");
        Categoria c2 = new Categoria(2, "Escritório");

        List<Categoria> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        return list;
    }
}

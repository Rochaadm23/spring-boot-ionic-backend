package com.rsecinformation.cursomc.dto;

import com.rsecinformation.cursomc.entities.Categoria;

public class CategoriaDTO {

    private Integer id;
    private String name;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

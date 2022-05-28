package com.rsecinformation.cursomc.dto;

import com.rsecinformation.cursomc.entities.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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

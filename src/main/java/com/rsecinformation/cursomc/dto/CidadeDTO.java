package com.rsecinformation.cursomc.dto;

import com.rsecinformation.cursomc.entities.Cidade;

import java.io.Serializable;

public class CidadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public CidadeDTO() {
    }

    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.name = cidade.getName();
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

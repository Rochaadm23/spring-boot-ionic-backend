package com.rsecinformation.cursomc.dto;

import com.rsecinformation.cursomc.entities.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto obj) {
        id = obj.getId();
        name = obj.getName();
        preco = obj.getPreco();
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}

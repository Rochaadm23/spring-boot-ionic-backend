package com.rsecinformation.cursomc.services;

import com.rsecinformation.cursomc.entities.Categoria;
import com.rsecinformation.cursomc.entities.Pedido;
import com.rsecinformation.cursomc.entities.Produto;
import com.rsecinformation.cursomc.repositories.CategoriaRepository;
import com.rsecinformation.cursomc.repositories.PedidoRepository;
import com.rsecinformation.cursomc.repositories.ProdutoRepository;
import com.rsecinformation.cursomc.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto findById(Integer id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Page<Produto> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findByNameContainingAndCategoriasIn(name, categorias, pageRequest);
    }
}

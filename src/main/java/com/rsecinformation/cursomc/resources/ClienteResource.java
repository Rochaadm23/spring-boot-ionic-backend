package com.rsecinformation.cursomc.resources;

import com.rsecinformation.cursomc.entities.Categoria;
import com.rsecinformation.cursomc.entities.Cliente;
import com.rsecinformation.cursomc.services.CategoriaService;
import com.rsecinformation.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
       Cliente obj = clienteService.findById(id);
       return ResponseEntity.ok().body(obj);
    }
}

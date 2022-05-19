package com.rsecinformation.cursomc.config;

import com.rsecinformation.cursomc.entities.Categoria;
import com.rsecinformation.cursomc.repositories.CategoriaRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    CategoriaRepositorie categoriaRepositorie;

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "Infomática");
        Categoria cat2 = new Categoria(null, "Escritório");

        categoriaRepositorie.saveAll(Arrays.asList(cat1, cat2));
    }
}

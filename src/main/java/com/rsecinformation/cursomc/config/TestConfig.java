package com.rsecinformation.cursomc.config;

import com.rsecinformation.cursomc.entities.*;
import com.rsecinformation.cursomc.entities.enums.EstadoPagamento;
import com.rsecinformation.cursomc.entities.enums.TipoCliente;
import com.rsecinformation.cursomc.repositories.*;
import com.rsecinformation.cursomc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;


@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

}
package br.com.senior.prova3.config;

import br.com.senior.prova3.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DBService dbService;
    @Bean
    public void instanciaDB(){
        this.dbService.iniciaDB();
    }
}

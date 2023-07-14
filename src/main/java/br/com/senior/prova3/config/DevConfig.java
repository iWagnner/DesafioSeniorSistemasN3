package br.com.senior.prova3.config;

import br.com.senior.prova3.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private DBService dbService;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;
    @Bean
    public boolean instanciaDB(){
        if(value.equals("create")) {
            this.dbService.iniciaDB();
            return true;
        }
        return false;
    }
}
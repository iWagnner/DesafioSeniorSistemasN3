package br.com.senior.prova3.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@SuppressWarnings("hiding")
@NoRepositoryBean
public interface BaseRepository<T, UUID> extends JpaRepository<T, UUID>{
	
	T findByIdMandatory(UUID id) throws IllegalArgumentException; 

}

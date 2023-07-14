package br.com.senior.prova3.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.senior.prova3.domain.Entidade;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@Repository
public interface EntidadeRepository extends BaseRepository<Entidade, UUID>{
	
	public Optional<Entidade> findbyEmail(String email);

}

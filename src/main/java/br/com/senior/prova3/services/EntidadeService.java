package br.com.senior.prova3.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.prova3.domain.Entidade;
import br.com.senior.prova3.repositories.impl.EntidadeRepositoryImpl;
import br.com.senior.prova3.services.exceptions.DataIntegrityViolationException;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@Service
public class EntidadeService {

	@Autowired
	private EntidadeRepositoryImpl repository;

	
	public void findbyEmail(UUID id, String email) {
		Optional<Entidade> objEntidade = repository.findbyEmail(email);
		if(objEntidade.isPresent() && objEntidade.get().getId() != id) {
			throw new DataIntegrityViolationException("E-MAIL já cadastrado!");
		}
	}

}

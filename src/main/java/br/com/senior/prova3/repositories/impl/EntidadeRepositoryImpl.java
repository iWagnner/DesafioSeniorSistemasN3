package br.com.senior.prova3.repositories.impl;

import java.util.Optional;
import java.util.UUID;

import br.com.senior.prova3.domain.Entidade;
import br.com.senior.prova3.repositories.EntidadeRepository;
import jakarta.persistence.EntityManager;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public class EntidadeRepositoryImpl extends BaseRepositoryImpl<Entidade, UUID> implements EntidadeRepository{

	public EntidadeRepositoryImpl(EntityManager em) {
		super(Entidade.class, em);
	}
	
	public Optional<Entidade> findbyEmail(String email) {
		return Optional.ofNullable(jpaQueryFactory.selectFrom(entidade)
				  								  .where(entidade.email.equalsIgnoreCase(email))
				  								  .fetchOne());
	}

}

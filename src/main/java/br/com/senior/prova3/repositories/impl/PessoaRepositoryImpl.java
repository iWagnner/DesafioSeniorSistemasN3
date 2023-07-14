package br.com.senior.prova3.repositories.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import br.com.senior.prova3.domain.Pessoa;
import br.com.senior.prova3.repositories.PessoaRepository;
import jakarta.persistence.EntityManager;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public class PessoaRepositoryImpl extends BaseRepositoryImpl<Pessoa, UUID> implements PessoaRepository {

	public PessoaRepositoryImpl(EntityManager em) {
		super(Pessoa.class, em);
	}

	@Override
	public Page<Pessoa> findAllOnFilter(Pageable pageable, String nome, String email, String cpfCnpj, String razaoSocial, String inscricaoEstadual) {
		 BooleanBuilder filterExpression = new BooleanBuilder(); // Inicializa o construtor de expressões booleanas

		    if (nome != null && !nome.isEmpty()) {
		        filterExpression.and(pessoa.nome.contains(nome));
		    }

		    if (email != null && !email.isEmpty()) {
		        filterExpression.or(pessoa.email.equalsIgnoreCase(email));
		    }

		    if (cpfCnpj != null && !cpfCnpj.isEmpty()) {
		        filterExpression.or(pessoa.cpfCnpj.equalsIgnoreCase(cpfCnpj));
		    }

		    if (razaoSocial != null && !razaoSocial.isEmpty()) {
		        filterExpression.or(pessoa.razaoSocial.equalsIgnoreCase(razaoSocial));
		    }
		    
		    if (inscricaoEstadual != null && !inscricaoEstadual.isEmpty()) {
		        filterExpression.or(pessoa.inscricaoEstadual.equalsIgnoreCase(inscricaoEstadual));
		    }

			@SuppressWarnings("deprecation")
			long total = jpaQueryFactory.selectFrom(pessoa)
										.where(filterExpression)
										.fetchCount();

			List<Pessoa> pessoas = jpaQueryFactory.selectFrom(pessoa)
													.where(filterExpression)
													.offset(pageable.getOffset())
													.limit(pageable.getPageSize())
													.fetch();

			return new PageImpl<>(pessoas, pageable, total);
	}
	
	@Override
	public Optional<Pessoa> findByCpfCnpj(String cpf) {
		return Optional.ofNullable(jpaQueryFactory.selectFrom(pessoa)
												  .where(pessoa.cpfCnpj.equalsIgnoreCase(cpf))
												  .fetchOne());
	}

	@Override
	public Optional<Pessoa> findByRazaoSocial(String razaoSocial) {
		return Optional.ofNullable(jpaQueryFactory.selectFrom(pessoa)
				  								  .where(pessoa.razaoSocial.equalsIgnoreCase(razaoSocial))
				  								  .fetchOne());
	}

	@Override
	public Optional<Pessoa> findByInscricaoEstadual(String inscricaoEstadual) {
		return Optional.ofNullable(jpaQueryFactory.selectFrom(pessoa)
				  						          .where(pessoa.inscricaoEstadual.equalsIgnoreCase(inscricaoEstadual))
				  						          .fetchOne());
	}
}

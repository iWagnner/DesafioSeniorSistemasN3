package br.com.senior.prova3.repositories.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import br.com.senior.prova3.domain.Usuario;
import br.com.senior.prova3.repositories.UsuarioRepository;
import jakarta.persistence.EntityManager;


/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public class UsuarioRepositoryImpl extends BaseRepositoryImpl<Usuario, UUID> implements UsuarioRepository{

	public UsuarioRepositoryImpl(EntityManager em) {
		super(Usuario.class, em);
	}

	@Override
	public Page<Usuario> findAllOnFilter(Pageable pageable, String nome, String email, String login, String cpfcnpj) {
		 BooleanBuilder filterExpression = new BooleanBuilder(); // Inicializa o construtor de expressões booleanas

		    if (nome != null && !nome.isEmpty()) {
		        filterExpression.and(usuario.nome.contains(nome));
		    }

		    if (email != null && !email.isEmpty()) {
		        filterExpression.or(usuario.email.equalsIgnoreCase(email));
		    }

		    if (login != null && !login.isEmpty()) {
		        filterExpression.or(usuario.login.equalsIgnoreCase(login));
		    }

		    if (cpfcnpj != null && !cpfcnpj.isEmpty()) {
		        filterExpression.or(usuario.cpfCnpj.equalsIgnoreCase(cpfcnpj));
		    }

			@SuppressWarnings("deprecation")
			long total = jpaQueryFactory.selectFrom(usuario)
										.where(filterExpression)
										.fetchCount();

			List<Usuario> usuarios = jpaQueryFactory.selectFrom(usuario)
													.where(filterExpression)
													.offset(pageable.getOffset())
													.limit(pageable.getPageSize())
													.fetch();

			return new PageImpl<>(usuarios, pageable, total);
		}

		@Override
		public Optional<Usuario> findByCpfCnpj(String cpf) {
			return Optional.ofNullable(jpaQueryFactory.selectFrom(usuario)
													  .where(usuario.cpfCnpj.equalsIgnoreCase(cpf))
													  .fetchOne());
		}

		public Optional<Usuario> findbyLogin(String login) {
			return Optional.ofNullable(jpaQueryFactory.selectFrom(usuario)
												      .where(usuario.login.equalsIgnoreCase(login))
												      .fetchOne());
		}

}

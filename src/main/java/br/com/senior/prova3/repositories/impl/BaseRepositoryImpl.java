package br.com.senior.prova3.repositories.impl;



import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.senior.prova3.domain.QEntidade;
import br.com.senior.prova3.domain.QPedido;
import br.com.senior.prova3.domain.QPedidoItem;
import br.com.senior.prova3.domain.QPessoa;
import br.com.senior.prova3.domain.QProduto;
import br.com.senior.prova3.domain.QUsuario;
import br.com.senior.prova3.repositories.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public abstract class BaseRepositoryImpl<T, UUID> extends SimpleJpaRepository<T, UUID> implements BaseRepository<T, UUID> {

	@PersistenceContext
	EntityManager em;
	JPAQueryFactory jpaQueryFactory;

	protected final QEntidade entidade = QEntidade.entidade;
	protected final QUsuario usuario = QUsuario.usuario;
	protected final QPessoa pessoa = QPessoa.pessoa;
	protected final QProduto produto = QProduto.produto;
	protected final QPedido pedido = QPedido.pedido;
	protected final QPedidoItem pedidoItem = QPedidoItem.pedidoItem;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
		this.jpaQueryFactory = new JPAQueryFactory(em);
	}

	@Override
	public T findByIdMandatory(UUID id) throws IllegalArgumentException {
		return findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID não encontrado."));
	}

}

package br.com.senior.prova3.repositories.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import br.com.senior.prova3.domain.Produto;
import br.com.senior.prova3.repositories.ProdutoRepository;
import jakarta.persistence.EntityManager;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public class ProdutoRepositoryImpl extends BaseRepositoryImpl<Produto, UUID> implements ProdutoRepository{

	public ProdutoRepositoryImpl(EntityManager em) {
		super(Produto.class, em);
	}

	@Override
	public Page<Produto> findAllOnFilter(Pageable pageable, UUID idfornecedor, String nome, BigDecimal preco) {
			 BooleanBuilder filterExpression = new BooleanBuilder(); // Inicializa o construtor de expressões booleanas

			    if (idfornecedor != null) {
			        filterExpression.and(produto.fornecedor.id.eq(idfornecedor));
			    }

			    if (nome != null && !nome.isEmpty()) {
 			        filterExpression.or(produto.nome.contains(nome));
			    }

			    if (preco != null) {
			        filterExpression.or(produto.preco.eq(preco));
			    }

			    
				@SuppressWarnings("deprecation")
				long total = jpaQueryFactory.selectFrom(produto)
											.where(filterExpression)
											.fetchCount();

				List<Produto> produtos = jpaQueryFactory.selectFrom(produto)
														.where(filterExpression)
														.offset(pageable.getOffset())
														.limit(pageable.getPageSize())
														.fetch();

				return new PageImpl<>(produtos, pageable, total);
		}

	@Override
	public Page<Produto> findAllProdutosIdFornecedor(Pageable pageable, UUID id) {
		@SuppressWarnings("deprecation")
		long total = jpaQueryFactory.selectFrom(produto)
									.where(produto.fornecedor.id.eq(id))
									.fetchCount();

		List<Produto> produtos = jpaQueryFactory.selectFrom(produto)
												.where(produto.fornecedor.id.eq(id))
												.offset(pageable.getOffset())
												.limit(pageable.getPageSize())
												.fetch();

		return new PageImpl<>(produtos, pageable, total);
	}

	public Optional<Produto> findByNome(String nome) {
		return Optional.ofNullable(jpaQueryFactory.selectFrom(produto)
				  								  .where(produto.nome.equalsIgnoreCase(nome))
				  								  .fetchOne());
	}

	public Optional<Produto> findByCodigo(Long codigo) {
		return Optional.ofNullable(jpaQueryFactory.selectFrom(produto)
				  								  .where(produto.codigo.eq(codigo))
				  								  .fetchOne());
	}
}

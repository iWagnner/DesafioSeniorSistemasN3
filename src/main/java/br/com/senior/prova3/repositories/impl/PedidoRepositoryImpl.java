package br.com.senior.prova3.repositories.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import br.com.senior.prova3.domain.Pedido;
import br.com.senior.prova3.repositories.PedidoRepository;
import jakarta.persistence.EntityManager;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public class PedidoRepositoryImpl extends BaseRepositoryImpl<Pedido, UUID> implements PedidoRepository{

	public PedidoRepositoryImpl(EntityManager em) {
		super(Pedido.class, em);
	}

	@Override
	public Page<Pedido> findAllOnFilter(Pageable pageable, UUID idcliente, UUID idUsuarioGerador, LocalDate dataCriacao) {
		 BooleanBuilder filterExpression = new BooleanBuilder(); // Inicializa o construtor de expressões booleanas

		    if (idcliente != null) {
		        filterExpression.and(pedido.cliente.id.eq(idcliente));
		    }

		    if (idUsuarioGerador != null) {
		        filterExpression.or(pedido.usuarioGerador.id.eq(idUsuarioGerador));
		    }

		    if (dataCriacao != null) {
		        filterExpression.or(pedido.dataCriacao.eq(dataCriacao));
		    }

		    
			@SuppressWarnings("deprecation")
			long total = jpaQueryFactory.selectFrom(pedido)
										.where(filterExpression)
										.fetchCount();

			List<Pedido> pedidos = jpaQueryFactory.selectFrom(pedido)
													.where(filterExpression)
													.offset(pageable.getOffset())
													.limit(pageable.getPageSize())
													.fetch();

			return new PageImpl<>(pedidos, pageable, total);
	}

	@Override
	public Page<Pedido> findAllPedidosByIdCliente(Pageable pageable, UUID id) {
		@SuppressWarnings("deprecation")
		long total = jpaQueryFactory.selectFrom(pedido)
									.where(pedido.cliente.id.eq(id))
									.fetchCount();

		List<Pedido> pedidos = jpaQueryFactory.selectFrom(pedido)
												.where(pedido.cliente.id.eq(id))
												.offset(pageable.getOffset())
												.limit(pageable.getPageSize())
												.fetch();

		return new PageImpl<>(pedidos, pageable, total);
	}

	@Override
	public Page<Pedido> findAllPedidosByIdUsuarioGerador(Pageable pageable, UUID id) {
		@SuppressWarnings("deprecation")
		long total = jpaQueryFactory.selectFrom(pedido)
									.where(pedido.usuarioGerador.id.eq(id))
									.fetchCount();

		List<Pedido> pedidos = jpaQueryFactory.selectFrom(pedido)
												.where(pedido.usuarioGerador.id.eq(id))
												.offset(pageable.getOffset())
												.limit(pageable.getPageSize())
												.fetch();

		return new PageImpl<>(pedidos, pageable, total);
	}
	
	@Override
	public Page<Pedido> findAllPedidosByIdProduto(Pageable pageable, UUID id) {
		@SuppressWarnings("deprecation")
		long total = jpaQueryFactory.selectFrom(pedido)
				  					.innerJoin(pedidoItem).on(pedido.id.eq(pedidoItem.pedido.id))
				  					.where(pedidoItem.produto.id.eq(id))
									.fetchCount();

		List<Pedido> pedidos = jpaQueryFactory.selectFrom(pedido)
			    							  .innerJoin(pedidoItem).on(pedido.id.eq(pedidoItem.pedido.id))
											  .where(pedidoItem.produto.id.eq(id))
											  .offset(pageable.getOffset())
											  .limit(pageable.getPageSize())
											  .fetch();

		return new PageImpl<>(pedidos, pageable, total);
	}
	
	public void deleteByIdPedidoCascade(UUID id) {
		 jpaQueryFactory.delete(pedidoItem)
		        		.where(pedidoItem.pedido.id.eq(id))
		        		.execute();
		 
		 jpaQueryFactory.delete(pedido)
	        		    .where(pedido.id.eq(id))
	                    .execute();
	}

}

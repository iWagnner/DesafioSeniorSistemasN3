package br.com.senior.prova3.repositories.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import br.com.senior.prova3.domain.PedidoItem;
import br.com.senior.prova3.repositories.PedidoItemRepository;
import jakarta.persistence.EntityManager;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public class PedidoItemRepositoryImpl extends BaseRepositoryImpl<PedidoItem, UUID> implements PedidoItemRepository{

	public PedidoItemRepositoryImpl(EntityManager em) {
		super(PedidoItem.class, em);
	}

	@Override
	public Page<PedidoItem> findAllOnFilter(Pageable pageable, UUID idpedido, UUID idproduto, LocalDate dataInclusao){
		 BooleanBuilder filterExpression = new BooleanBuilder(); // Inicializa o construtor de expressões booleanas

		    if (idpedido != null) {
		        filterExpression.and(pedidoItem.pedido.id.eq(idpedido));
		    }

		    if (idproduto != null) {
		        filterExpression.or(pedidoItem.produto.id.eq(idproduto));
		    }

		    if (dataInclusao != null) {
		        filterExpression.or(pedidoItem.dataInclusao.eq(dataInclusao));
		    }

		    
			@SuppressWarnings("deprecation")
			long total = jpaQueryFactory.selectFrom(pedidoItem)
										.where(filterExpression)
										.fetchCount();

			List<PedidoItem> pedidoItens = jpaQueryFactory.selectFrom(pedidoItem)
													.where(filterExpression)
													.offset(pageable.getOffset())
													.limit(pageable.getPageSize())
													.fetch();

			return new PageImpl<>(pedidoItens, pageable, total);
	}

	@Override
	public Page<PedidoItem> findAllPedidosItemByIdProduto(Pageable pageable, UUID id) {
		@SuppressWarnings("deprecation")
		long total = jpaQueryFactory.selectFrom(pedidoItem)
									.where(pedidoItem.produto.id.eq(id))
									.fetchCount();

		List<PedidoItem> pedidoitens = jpaQueryFactory.selectFrom(pedidoItem)
												.where(pedidoItem.produto.id.eq(id))
												.offset(pageable.getOffset())
												.limit(pageable.getPageSize())
												.fetch();

		return new PageImpl<>(pedidoitens, pageable, total);
	}
	
	@Override
	public Page<PedidoItem> findAllPedidosItemByIdPedido(Pageable pageable, UUID id) {
		@SuppressWarnings("deprecation")
		long total = jpaQueryFactory.selectFrom(pedidoItem)
									.where(pedidoItem.pedido.id.eq(id))
									.fetchCount();

		List<PedidoItem> pedidoitens = jpaQueryFactory.selectFrom(pedidoItem)
												.where(pedidoItem.pedido.id.eq(id))
												.offset(pageable.getOffset())
												.limit(pageable.getPageSize())
												.fetch();

		return new PageImpl<>(pedidoitens, pageable, total);
	}
	
	
	public List<PedidoItem> findAllPedidosItemByIdPedido(UUID id) {
		List<PedidoItem> pedidoitens = jpaQueryFactory.selectFrom(pedidoItem)
													  .where(pedidoItem.pedido.id.eq(id))
												      .fetch();

		return pedidoitens;
	}

}

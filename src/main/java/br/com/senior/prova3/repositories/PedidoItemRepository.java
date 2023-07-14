package br.com.senior.prova3.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.senior.prova3.domain.PedidoItem;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public interface PedidoItemRepository extends BaseRepository<PedidoItem, UUID>{
	
	public Page<PedidoItem> findAllOnFilter(Pageable pageable, UUID idpedido, UUID idproduto, LocalDate dataInclusao);
	
	public Page<PedidoItem> findAllPedidosItemByIdProduto(Pageable pageable, UUID id);
	
	public Page<PedidoItem> findAllPedidosItemByIdPedido(Pageable pageable, UUID id);
	
	public List<PedidoItem> findAllPedidosItemByIdPedido(UUID id);

}

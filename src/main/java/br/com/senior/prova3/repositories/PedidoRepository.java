package br.com.senior.prova3.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.senior.prova3.domain.Pedido;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public interface PedidoRepository extends BaseRepository<Pedido, UUID>{
	
	public Page<Pedido> findAllOnFilter(Pageable pageable, UUID idcliente, UUID idUsuarioGerador, LocalDate dataCriacao);
	
	public Page<Pedido> findAllPedidosByIdCliente(Pageable pageable, UUID id);
	
	public Page<Pedido> findAllPedidosByIdUsuarioGerador(Pageable pageable, UUID id);
	
	public Page<Pedido> findAllPedidosByIdProduto(Pageable pageable, UUID id);
}

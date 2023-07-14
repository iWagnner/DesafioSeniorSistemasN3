package br.com.senior.prova3.repositories;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.senior.prova3.domain.Produto;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public interface ProdutoRepository extends BaseRepository<Produto, UUID>{
	
	public Page<Produto> findAllOnFilter(Pageable pageable, UUID idfornecedor, String nome, BigDecimal preco); 
	
	public Page<Produto> findAllProdutosIdFornecedor(Pageable pageable, UUID id);

}

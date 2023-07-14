package br.com.senior.prova3.services;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senior.prova3.domain.Pessoa;
import br.com.senior.prova3.domain.Produto;
import br.com.senior.prova3.domain.dto.ProdutoDTO;
import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumPerfil;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import br.com.senior.prova3.repositories.impl.PessoaRepositoryImpl;
import br.com.senior.prova3.repositories.impl.ProdutoRepositoryImpl;
import br.com.senior.prova3.services.exceptions.DataIntegrityViolationException;
import br.com.senior.prova3.services.exceptions.IllegalArgumentException;
import br.com.senior.prova3.services.exceptions.ObjectNotFoundException;
import br.com.senior.prova3.util.Util;
import jakarta.validation.Valid;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepositoryImpl repository;
	@Autowired
	private PessoaRepositoryImpl pessoaRepositoryImpl;

	public Page<Produto> findAllOnFilter(Pageable pageable, UUID idfornecedor, String nome, BigDecimal preco) {
		Page<Produto> produtos;
		if (idfornecedor == null && Util.stringNullOrEmpty(nome) && preco == null) {
			produtos = repository.findAll(pageable);
		} else {
			produtos = repository.findAllOnFilter(pageable, idfornecedor, nome, preco);
		}
		if (produtos.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum cadastro foi encontrado");
		}
		return produtos;
	}

	public Produto findById(UUID id) {
		Optional<Produto> produto = repository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrada: " + id));
	}

	public Produto create(@Valid ProdutoDTO produtoDTO) {
		validaDadosUnicos(produtoDTO);
		return repository.save(newProduto(produtoDTO));
	}
	
	public Produto update(UUID id, @Valid ProdutoDTO produtoDTO) {
		produtoDTO.setId(id);
		Produto oldProduto = findById(id);
		oldProduto = newProduto(produtoDTO);
		return repository.save(oldProduto);
	}
	
	public void delete(UUID id) {
		Produto produto = findById(id);
		if(produto.getPedidoitem().size() > 0) {
			produto.setSituacao(EnumAtivoInativo.INATIVO);
			repository.save(produto);
		}else {
			repository.deleteById(id);
		}
	}

	public Produto newProduto(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		
		if (produtoDTO.getId() != null) {
			Produto produtoExiste = this.findById(produtoDTO.getId());
			if (produtoExiste != null) {
				produto.setId(produtoExiste.getId());
				if(produtoExiste.getPedidoitem().size() > 0) {
					produto.setPermiteExcluir(produtoExiste.getPermiteExcluir());
				}else {
					produto.setPermiteExcluir(produtoDTO.getPermiteExcluir());
				}
			}else {
				produto.setPermiteExcluir(produtoDTO.getPermiteExcluir()); 
			}
		}
		
		Pessoa fornecedor = buscarFornecedor(produtoDTO.getIdfornecedor());
		
		produto.setCodigo(produtoDTO.getCodigo()); 
		produto.setFornecedor(fornecedor); 
		produto.setNome(produtoDTO.getNome());
		produto.setTipo(produtoDTO.getTipo());
		produto.setSituacao(produtoDTO.getSituacao()); 
		produto.setPreco(produtoDTO.getPreco()); 
		produto.setSiglaUnidadeMedida(produtoDTO.getSiglaUnidadeMedida()); 
		produto.setObservacao(produtoDTO.getObservacao()); 
		produto.setEstoqueDisponivel(calculaEstoqueDisponivel(produtoDTO.getEstoqueTotal(), produtoDTO.getEstoqueBloqueado(), produtoDTO.getPermiteEstoqueNegativo()));
		produto.setEstoqueBloqueado(produtoDTO.getEstoqueBloqueado());
		produto.setPermiteEstoqueNegativo(produtoDTO.getPermiteEstoqueNegativo());
		produto.setEstoqueTotal(produtoDTO.getEstoqueTotal());
		produto.setCurrenttimemillis(System.currentTimeMillis());
		
		return produto;
	}
	
	public void validaDadosUnicos(ProdutoDTO produtoDTO) {
		if (produtoDTO.getNome() != null)
			findByNome(produtoDTO.getId(), produtoDTO.getNome());

		if (produtoDTO.getCodigo() != null)
			findByCodigo(produtoDTO.getId(), produtoDTO.getCodigo());
	}
	
	public void findByNome(UUID id, String cpfCnpj) {
		Optional<Produto> objProduto = repository.findByNome(cpfCnpj);
		if (objProduto.isPresent() && objProduto.get().getId() != id) {
			throw new DataIntegrityViolationException("NOME já cadastrado!");
		}
	}
	
	public void findByCodigo(UUID id, Long codigo) {
		Optional<Produto> objProduto = repository.findByCodigo(codigo);
		if (objProduto.isPresent() && objProduto.get().getId() != id) {
			throw new DataIntegrityViolationException("CODIGO de produto já cadastrado!");
		}
	}
	
	public BigDecimal calculaEstoqueDisponivel (BigDecimal estoqueTotal, BigDecimal estoqueBloqueado, EnumSimNao permiteEstoqueNegativo) {
		 if (estoqueBloqueado.compareTo(BigDecimal.ZERO) < 0) {
			 throw new IllegalArgumentException("Campo Estoque bloqueado não pode ter um valor negativo");
		 }

		 BigDecimal resultado = estoqueTotal.subtract(estoqueBloqueado);
		if (resultado.compareTo(BigDecimal.ZERO) < 0 && permiteEstoqueNegativo.equals(EnumSimNao.NAO)) {
			throw new DataIntegrityViolationException("Sistema configurado para não aceitar estoque negativo. "
														+ " Verifique o saldo de seu estoque bloqueado e de seu estoque total, "
														+ "pois isso resultou em um estoque disponivel de: " + resultado );
	    }
		
		return resultado;
	}
	
	public Pessoa buscarFornecedor(UUID id) {
		 Optional<Pessoa> optionalPessoa = pessoaRepositoryImpl.findById(id);
	    if (optionalPessoa.isPresent()) {
	        Pessoa pessoa = optionalPessoa.get();
	        if (pessoa.getPerfis().contains(EnumPerfil.FORNECEDOR)) {
	            return pessoa;
	        } else {
	            throw new IllegalArgumentException("ID informado não pertence a um fornecedor.");
	        }
	    } else {
	        throw new IllegalArgumentException("Fornecedor não encontrado com o ID informado.");
	    }
	}
}

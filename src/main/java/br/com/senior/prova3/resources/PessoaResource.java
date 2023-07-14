package br.com.senior.prova3.resources;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.senior.prova3.domain.Pedido;
import br.com.senior.prova3.domain.Pessoa;
import br.com.senior.prova3.domain.Produto;
import br.com.senior.prova3.domain.dto.PedidoDTO;
import br.com.senior.prova3.domain.dto.PessoaDTO;
import br.com.senior.prova3.domain.dto.ProdutoDTO;
import br.com.senior.prova3.services.PessoaService;
import br.com.senior.prova3.util.Util;
import jakarta.validation.Valid;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService service;
	
	@GetMapping
	public ResponseEntity<Page<PessoaDTO>> findAll(@PageableDefault(sort = "currenttimemillis", direction = Direction.ASC) Pageable pageable,
													@RequestParam(value = "nome", required = false) String nome,
													@RequestParam(value = "email", required = false) String email,
													@RequestParam(value = "cpfcnpj", required = false) String cpfCnpj,
													@RequestParam(value = "razaosocial", required = false) String razaoSocial,
													@RequestParam(value = "inscricaoestadual", required = false) String inscricaoEstadual){
													
		Page<Pessoa> pessoas = service.findAllPessoas(pageable, nome, email, Util.removerEspacosCaracteresEspeciais(inscricaoEstadual), Util.removerEspacosCaracteresEspeciais(razaoSocial), Util.removerEspacosCaracteresEspeciais(inscricaoEstadual));
		Page<PessoaDTO> listPessoaDTO = pessoas.map(PessoaDTO::new);
		return ResponseEntity.ok().body(listPessoaDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> findById(@PathVariable UUID id){
		Pessoa pessoa = service.findById(id);
		return ResponseEntity.ok().body(new PessoaDTO(pessoa));
	}
	
	@GetMapping(value = "/pedidos/{id}")
	public ResponseEntity<Page<PedidoDTO>> findAllPedidosIdCliente(@PathVariable UUID id, @PageableDefault(sort = "currenttimemillis", direction = Direction.ASC) Pageable pageable){
		Page<Pedido> pedidos = service.findAllPedidosIdCliente(pageable, id);
		Page<PedidoDTO> listPedidoDTO = pedidos.map(PedidoDTO::new);
		return ResponseEntity.ok().body(listPedidoDTO);
	}
	
	@GetMapping(value = "/produtos/{id}")
	public ResponseEntity<Page<ProdutoDTO>> findAllProdutosIdFornecedor(@PathVariable UUID id, @PageableDefault(sort = "currenttimemillis", direction = Direction.ASC) Pageable pageable){
		Page<Produto> produtos = service.findAllProdutosIdFornecedor(pageable, id);
		Page<ProdutoDTO> listProdutoDTO = produtos.map(ProdutoDTO::new);
		return ResponseEntity.ok().body(listProdutoDTO);
	}
	
	@PostMapping
	public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO pessoaDTO){
		Pessoa pessoa = service.create(pessoaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> update(@PathVariable UUID id , @Valid @RequestBody PessoaDTO pessoaDTO){
		Pessoa newPessoa = service.update(id, pessoaDTO);
		return ResponseEntity.ok().body(new PessoaDTO(newPessoa));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> delete (@PathVariable UUID id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}

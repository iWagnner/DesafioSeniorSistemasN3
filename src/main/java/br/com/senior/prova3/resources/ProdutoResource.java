package br.com.senior.prova3.resources;

import java.math.BigDecimal;
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

import br.com.senior.prova3.domain.Produto;
import br.com.senior.prova3.domain.dto.ProdutoDTO;
import br.com.senior.prova3.services.ProdutoService;
import jakarta.validation.Valid;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findAll(@PageableDefault(sort = "currenttimemillis", direction = Direction.ASC) Pageable pageable,
													@RequestParam(value = "idfornecedor", required = false) UUID idfornecedor,
													@RequestParam(value = "nome", required = false) String nome,
													@RequestParam(value = "preco", required = false) BigDecimal preco){
													
		Page<Produto> produtos = service.findAllOnFilter(pageable, idfornecedor,nome, preco);
		Page<ProdutoDTO> listProdutoDTO = produtos.map(ProdutoDTO::new);
		return ResponseEntity.ok().body(listProdutoDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable UUID id){
		Produto produto = service.findById(id);
		return ResponseEntity.ok().body(new ProdutoDTO(produto));
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO produtoDTO){
		Produto produto = service.create(produtoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable UUID id , @Valid @RequestBody ProdutoDTO produtoDTO){
		Produto newProduto = service.update(id, produtoDTO);
		return ResponseEntity.ok().body(new ProdutoDTO(newProduto));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> delete (@PathVariable UUID id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}

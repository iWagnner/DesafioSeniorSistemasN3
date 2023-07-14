package br.com.senior.prova3.resources;

import java.net.URI;
import java.time.LocalDate;
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
import br.com.senior.prova3.domain.PedidoItem;
import br.com.senior.prova3.domain.dto.PedidoDTO;
import br.com.senior.prova3.domain.dto.PedidoItemDTO;
import br.com.senior.prova3.services.PedidoItemService;
import br.com.senior.prova3.services.PedidoService;
import jakarta.validation.Valid;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	@Autowired
	private PedidoItemService pedidoItemService;
	
	
	@GetMapping
	public ResponseEntity<Page<PedidoDTO>> findAll(@PageableDefault(sort = "currenttimemillis", direction = Direction.ASC) Pageable pageable,
													@RequestParam(value = "idcliente", required = false) UUID idcliente,
													@RequestParam(value = "idUsuarioGerador", required = false) UUID isusuariogerador,
													@RequestParam(value = "dataCriacao", required = false) LocalDate datacriacao){
													
		Page<Pedido> pedidos = service.findAllOnFilter(pageable, idcliente, isusuariogerador, datacriacao);
		Page<PedidoDTO> listPedidoDTO = pedidos.map(PedidoDTO::new);
		return ResponseEntity.ok().body(listPedidoDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> findById(@PathVariable UUID id){
		Pedido pedido = service.findById(id);
		return ResponseEntity.ok().body(new PedidoDTO(pedido));
	}
	
	@GetMapping(value = "/item/{id}")
	public ResponseEntity<PedidoItemDTO> findItensAll(@PathVariable UUID id){
		PedidoItem itens = pedidoItemService.findById(id);
		return ResponseEntity.ok().body(new PedidoItemDTO(itens));
	}
	
	@GetMapping(value = "/itens")
	public ResponseEntity<Page<PedidoItemDTO>> pedidoItemService(@PageableDefault(sort = "currenttimemillis", direction = Direction.ASC) Pageable pageable,
														   @RequestParam(value = "idpedido", required = false) UUID idpedido,
														   @RequestParam(value = "idproduto", required = false) UUID idproduto,
														   @RequestParam(value = "datacriacao", required = false) LocalDate datacriacao){
		
		Page<PedidoItem> pedidos = pedidoItemService.findAllOnFilter(pageable, idpedido, idproduto, datacriacao);
		Page<PedidoItemDTO> listPedidoDTO = pedidos.map(PedidoItemDTO::new);
		return ResponseEntity.ok().body(listPedidoDTO);
	}
	
	@GetMapping(value = "/itens/produto/{id}")
	public ResponseEntity<Page<PedidoDTO>> findAllPedidosIdProduto(@PathVariable UUID id, @PageableDefault(sort = "currenttimemillis", direction = Direction.ASC) Pageable pageable){
		Page<Pedido> pedidos = service.findAllPedidosByIdProduto(pageable, id);
		Page<PedidoDTO> listPedidoDTO = pedidos.map(PedidoDTO::new);
		return ResponseEntity.ok().body(listPedidoDTO);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoDTO pedidoDTO){
		Pedido pedido = service.create(pedidoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> update(@PathVariable UUID id , @Valid @RequestBody PedidoDTO pedidoDTO){
		Pedido newPedido = service.create(id, pedidoDTO);
		return ResponseEntity.ok().body(new PedidoDTO(newPedido));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> delete (@PathVariable UUID id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}

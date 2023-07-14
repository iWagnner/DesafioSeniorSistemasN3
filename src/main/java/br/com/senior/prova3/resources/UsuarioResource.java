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
import br.com.senior.prova3.domain.Usuario;
import br.com.senior.prova3.domain.dto.PedidoDTO;
import br.com.senior.prova3.domain.dto.UsuarioDTO;
import br.com.senior.prova3.services.UsuarioService;
import br.com.senior.prova3.util.Util;
import jakarta.validation.Valid;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> findAll(@PageableDefault(sort = "currenttimemillis", direction = Direction.ASC) Pageable pageable,
													@RequestParam(value = "nome", required = false) String nome,
													@RequestParam(value = "email", required = false) String email,
													@RequestParam(value = "login", required = false) String login,
													@RequestParam(value = "cpfcnpj", required = false) String cpfcnpj){
		
		Page<Usuario> usuarios = service.findAllUsuarios(pageable, nome, email, login, Util.removerEspacosCaracteresEspeciais(cpfcnpj));
		Page<UsuarioDTO> listUsuarioDTO = usuarios.map(UsuarioDTO::new);
		return ResponseEntity.ok().body(listUsuarioDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable UUID id){
		Usuario usuario = service.findById(id);
		return ResponseEntity.ok().body(new UsuarioDTO(usuario));
	}
	
	@GetMapping(value = "/pedidos/{id}")
	public ResponseEntity<Page<PedidoDTO>> findAllPedidosUsuario(@PathVariable UUID id, @PageableDefault(sort = "currenttimemillis", direction = Direction.ASC) Pageable pageable){
		Page<Pedido> pedidos = service.findAllPedidosIdUsuario(pageable, id);
		Page<PedidoDTO> listPedidoDTO = pedidos.map(PedidoDTO::new);
		return ResponseEntity.ok().body(listPedidoDTO);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuarioDTO){
		Usuario usuario = service.create(usuarioDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable UUID id , @Valid @RequestBody UsuarioDTO usuarioDTO){
		Usuario newUsuario = service.update(id, usuarioDTO);
		return ResponseEntity.ok().body(new UsuarioDTO(newUsuario));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> delete (@PathVariable UUID id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

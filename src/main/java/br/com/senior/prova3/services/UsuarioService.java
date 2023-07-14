package br.com.senior.prova3.services;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senior.prova3.domain.Pedido;
import br.com.senior.prova3.domain.Usuario;
import br.com.senior.prova3.domain.dto.UsuarioDTO;
import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumEstadoIBGE;
import br.com.senior.prova3.domain.enums.EnumSexo;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import br.com.senior.prova3.domain.enums.EnumTipoPerfilUsuario;
import br.com.senior.prova3.domain.enums.EnumTipoPessoa;
import br.com.senior.prova3.repositories.impl.PedidoRepositoryImpl;
import br.com.senior.prova3.repositories.impl.UsuarioRepositoryImpl;
import br.com.senior.prova3.services.exceptions.DataIntegrityViolationException;
import br.com.senior.prova3.services.exceptions.ObjectNotFoundException;
import br.com.senior.prova3.util.Util;
import jakarta.validation.Valid;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositoryImpl repository;
	@Autowired
	private EntidadeService entidadeService;
	@Autowired
	private PedidoRepositoryImpl pedidoRepositoryImpl;

	public Page<Usuario> findAllUsuarios(Pageable pageable, String nome, String email, String login, String cpfcnpj) {
		Page<Usuario> usuarios;
		if (Util.stringNullOrEmpty(nome) && Util.stringNullOrEmpty(email) && Util.stringNullOrEmpty(login) && Util.stringNullOrEmpty(cpfcnpj)) {
			usuarios = repository.findAll(pageable);
		} else {
			usuarios = repository.findAllOnFilter(pageable, nome, email, login, cpfcnpj);
		}
		if (usuarios.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum usuário encontrado");
		}
		return usuarios;
	}

	public Usuario findById(UUID id) {
		Optional<Usuario> usuario = repository.findById(id);
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado: " + id));
	}

	public Usuario create(@Valid UsuarioDTO usuarioDTO) {
		validaDadosUnicos(usuarioDTO);
		return repository.save(newUsuario(usuarioDTO));
	}
	
	public Usuario update(UUID id, @Valid UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(id);
		Usuario oldUsuario = findById(id);
		oldUsuario = newUsuario(usuarioDTO);
		return repository.save(oldUsuario);
	}
	
	public void delete(UUID id) {
		Usuario usuario = findById(id);
		if(usuario.getPedidos().size() > 0) {
			usuario.setSituacao(EnumAtivoInativo.INATIVO);
			repository.save(usuario);
		}else {
			repository.deleteById(id);
		}
	}

	public Usuario newUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		
		if (usuarioDTO.getId() != null) {
			Usuario usuarioExiste = this.findById(usuarioDTO.getId());
			if (usuarioExiste != null) {
				usuario.setId(usuarioExiste.getId());
				usuario.setDataCriacao(usuarioExiste.getDataCriacao());
			}
		} else {
			usuario.setDataCriacao(LocalDate.now());
		}
		
		usuario.setNome(usuarioDTO.getNome());
		usuario.setLogin(usuarioDTO.getLogin());
		usuario.setSenha(usuarioDTO.getSenha());
		usuario.setTelefone(usuarioDTO.getTelefone());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setCpfCnpj(Util.removerEspacosCaracteresEspeciais(usuarioDTO.getCpf()));
		usuario.setDataNascimento(usuarioDTO.getDataNascimento());
		usuario.setLogradouro(usuarioDTO.getLogradouro());
		usuario.setComplementoLogradouro(usuarioDTO.getComplementoLogradouro());
		usuario.setNumeroLogradouro(usuarioDTO.getNumeroLogradouro());
		usuario.setPerfilUsuario(EnumTipoPerfilUsuario.toEnum(usuarioDTO.getPerfilUsuario().getId()));
		usuario.setSituacao(EnumAtivoInativo.toEnum(usuarioDTO.getSituacao().getId()));
		usuario.setNaturalidade(EnumEstadoIBGE.toEnum(usuarioDTO.getNaturalidade().getUf()));
		usuario.setSexo(EnumSexo.toEnum(usuarioDTO.getSexo().getId()));
		usuario.setTipoPessoa(EnumTipoPessoa.toEnum(usuarioDTO.getTipoPessoa().getId()));
		usuario.setPerfis(usuarioDTO.getPerfis());
		usuario.setPodeExcluir(EnumSimNao.toEnum(usuarioDTO.getPodeExcluir().getId()));
		usuario.setCurrenttimemillis(System.currentTimeMillis());
		
		return usuario;
	}
	
	public void validaDadosUnicos(UsuarioDTO usuarioDTO) {
		findByCpfCnpj(usuarioDTO.getId(), Util.removerEspacosCaracteresEspeciais(usuarioDTO.getCpf()));
		
		findbyLogin(usuarioDTO.getId(), usuarioDTO.getLogin());
		
		entidadeService.findbyEmail(usuarioDTO.getId(), usuarioDTO.getEmail());
	}
	
	public void findByCpfCnpj(UUID id, String cpf) {
		Optional<Usuario> objUsuario = repository.findByCpfCnpj(cpf);
		if (objUsuario.isPresent() && objUsuario.get().getId() != id) {
			throw new DataIntegrityViolationException("CPFCNPJ já cadastrado!");
		}
	}
	
	public void findbyLogin(UUID id, String login) {
		Optional<Usuario> objUsuario = repository.findbyLogin(login);
		if(objUsuario.isPresent() && objUsuario.get().getId() != id) {
			throw new DataIntegrityViolationException("LOGIN já cadastrado!");
		}
	}
	
	public Page<Pedido> findAllPedidosIdUsuario(Pageable pageable, UUID id) {
		Page<Pedido> pedidos = pedidoRepositoryImpl.findAllPedidosByIdUsuarioGerador(pageable, id);
		if (pedidos.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum pedido foi encontrado para esse UsuarioGerador");
		}
		return pedidos;
	}

}

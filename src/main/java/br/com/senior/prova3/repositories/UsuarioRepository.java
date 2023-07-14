package br.com.senior.prova3.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.senior.prova3.domain.Usuario;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public interface UsuarioRepository extends BaseRepository<Usuario, UUID>{
	
	public Page<Usuario> findAllOnFilter(Pageable pageable, String nome, String email, String login, String cpfcnpj);
	
	public Optional<Usuario> findByCpfCnpj(String cpf);

}

package br.com.senior.prova3.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.senior.prova3.domain.Pessoa;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */

public interface PessoaRepository extends BaseRepository<Pessoa, UUID>{
	
	public Page<Pessoa> findAllOnFilter(Pageable pageable, String nome, String email, String cpfCnpj, String razaoSocial, String inscricaoEstadual);
	
	public Optional<Pessoa> findByCpfCnpj(String cpfCnpj);
	
	public Optional<Pessoa> findByRazaoSocial(String razaoSocial);

	public Optional<Pessoa> findByInscricaoEstadual(String inscricaoEstadual);
}

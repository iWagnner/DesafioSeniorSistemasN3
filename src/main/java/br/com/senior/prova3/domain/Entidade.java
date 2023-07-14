package br.com.senior.prova3.domain;

import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumPerfil;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */

@Entity
public class Entidade implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	protected UUID id;
	protected String nome;
	protected String telefone;
	@Column(unique = true)
	protected String email;
	protected String logradouro;
	protected String complementoLogradouro;
	protected String numeroLogradouro;
	private EnumSimNao podeExcluir;
	protected EnumAtivoInativo situacao;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfis")
	protected Set<String> perfis = new HashSet<>();
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	public Entidade() {
		super();
	}

	public Entidade(UUID id, String nome, String telefone, String email, String logradouro,
			String complementoLogradouro, String numeroLogradouro, EnumAtivoInativo situacao, EnumSimNao podeExcluir,
			EnumPerfil perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.logradouro = logradouro;
		this.complementoLogradouro = complementoLogradouro;
		this.numeroLogradouro = numeroLogradouro;
		this.situacao = situacao;
		this.podeExcluir = podeExcluir;
		addPerfil(perfil);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<EnumPerfil> getPerfis() {
		return perfis.stream().map(x -> EnumPerfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(EnumPerfil perfil) {
		this.perfis.add(perfil.getId());
	}
	
	public void setPerfis(Set<EnumPerfil> perfis) {
		for(EnumPerfil p : perfis) {
			addPerfil(p);
		}
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplementoLogradouro() {
		return complementoLogradouro;
	}

	public void setComplementoLogradouro(String complementoLogradouro) {
		this.complementoLogradouro = complementoLogradouro;
	}

	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public EnumAtivoInativo getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumAtivoInativo situacao) {
		this.situacao = situacao;
	}

	public EnumSimNao getPodeExcluir() {
		return podeExcluir;
	}

	public void setPodeExcluir(EnumSimNao podeExcluir) {
		this.podeExcluir = podeExcluir;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entidade other = (Entidade) obj;
		return Objects.equals(id, other.id);
	}

}

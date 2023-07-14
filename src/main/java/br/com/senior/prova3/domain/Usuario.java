package br.com.senior.prova3.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;


import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumEstadoIBGE;
import br.com.senior.prova3.domain.enums.EnumPerfil;
import br.com.senior.prova3.domain.enums.EnumSexo;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import br.com.senior.prova3.domain.enums.EnumTipoPerfilUsuario;
import br.com.senior.prova3.domain.enums.EnumTipoPessoa;

/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */

@Entity()
public class Usuario extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String login;
	private String senha;
	 @Column(name = "perfilusuario")
	private EnumTipoPerfilUsuario perfilUsuario;
	@CPF
	@Column(unique = true, name = "cpfcnpj")
	private String cpfCnpj;
	@Column(name = "datanascimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private EnumEstadoIBGE naturalidade;
	private EnumSexo sexo;
	@Column(name = "tipopessoa")
	private EnumTipoPessoa tipoPessoa;
	@Column(name = "currenttimemillis")
	private Long currenttimemillis;

	@JsonIgnore
	@OneToMany(mappedBy = "usuarioGerador")
	private List<Pedido> pedidos = new ArrayList<>();

	public Usuario() {
		super();
		addPerfil(EnumPerfil.USUARIO);
	}

	public Usuario(UUID id, String nome, String telefone, String email, String logradouro, String complementoLogradouro,
			String numeroLogradouro, EnumAtivoInativo situacao, EnumSimNao podeExcluir, EnumPerfil perfil) {
		super(id, nome, telefone, email, logradouro, complementoLogradouro, numeroLogradouro, situacao, podeExcluir, perfil);
	}

	public Usuario(UUID id, String nome, String telefone, String email, String logradouro, String complementoLogradouro,
			String numeroLogradouro, EnumAtivoInativo situacao, EnumPerfil perfil, String login, String senha,
			EnumTipoPerfilUsuario perfilUsuario, EnumSimNao podeExcluir, String cpfCnpj, LocalDate dataNascimento,
			EnumEstadoIBGE naturalidade, EnumSexo sexo, EnumTipoPessoa tipoPessoa) {
		super(id, nome, telefone, email, logradouro, complementoLogradouro, numeroLogradouro, situacao, podeExcluir, perfil);
		this.login = login;
		this.senha = senha;
		this.perfilUsuario = perfilUsuario;
		this.cpfCnpj = cpfCnpj;
		this.dataNascimento = dataNascimento;
		this.naturalidade = naturalidade;
		this.sexo = sexo;
		this.tipoPessoa = tipoPessoa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EnumTipoPerfilUsuario getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(EnumTipoPerfilUsuario perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public EnumAtivoInativo getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumAtivoInativo situacao) {
		this.situacao = situacao;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpf) {
		this.cpfCnpj = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EnumEstadoIBGE getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(EnumEstadoIBGE naturalidade) {
		this.naturalidade = naturalidade;
	}

	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}

	public EnumTipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(EnumTipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Long getCurrenttimemillis() {
		return currenttimemillis;
	}

	public void setCurrenttimemillis(Long currenttimemillis) {
		this.currenttimemillis = currenttimemillis;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}

package br.com.senior.prova3.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumEstadoIBGE;
import br.com.senior.prova3.domain.enums.EnumPerfil;
import br.com.senior.prova3.domain.enums.EnumSexo;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import br.com.senior.prova3.domain.enums.EnumTipoPessoa;

@Entity
public class Pessoa extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "tipopessoa")
	private EnumTipoPessoa tipoPessoa;
	@Column(name = "razaosocial")
	private String razaoSocial;
	@Column(name = "cpfcpnj")
	private String cpfCnpj;
	@Column(name = "inscricaoestatual")
	private String inscricaoEstadual;
	@Column(name = "datanascimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private EnumSexo sexo;
	private EnumEstadoIBGE naturalidade;
	@Column(name = "currenttimemillis")
	private Long currenttimemillis;
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "fornecedor")
	private List<Produto> produtos = new ArrayList<>();

	public Pessoa() {
		super();
	}

	public Pessoa(UUID id, String nome, String telefone, String email, String logradouro, String complementoLogradouro,
			String numeroLogradouro, EnumAtivoInativo situacao, EnumSimNao podeExcluir, EnumPerfil perfil) {
		super(id, nome, telefone, email, logradouro, complementoLogradouro, numeroLogradouro, situacao, podeExcluir, perfil);
	}

	public Pessoa(UUID id, String nome, String telefone, String email, String logradouro, String complementoLogradouro,
			String numeroLogradouro, EnumAtivoInativo situacao, EnumSimNao podeExcluir, EnumTipoPessoa tipoPessoa,
			String razaoSocial, String cpfCnpj, String inscricaoEstadual, LocalDate dataNascimento, EnumSexo sexo,
			EnumEstadoIBGE naturalidade, EnumPerfil perfil) {
		super(id, nome, telefone, email, logradouro, complementoLogradouro, numeroLogradouro, situacao, podeExcluir, perfil);
		this.tipoPessoa = tipoPessoa;
		this.razaoSocial = razaoSocial;
		this.cpfCnpj = cpfCnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.naturalidade = naturalidade;
		this.currenttimemillis = System.currentTimeMillis();
	}

	public EnumTipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(EnumTipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String incricaoEstadual) {
		this.inscricaoEstadual = incricaoEstadual;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}

	public EnumEstadoIBGE getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(EnumEstadoIBGE naturalidade) {
		this.naturalidade = naturalidade;
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}

package br.com.senior.prova3.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.senior.prova3.domain.Pedido;
import br.com.senior.prova3.domain.Pessoa;
import br.com.senior.prova3.domain.Produto;
import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumEstadoIBGE;
import br.com.senior.prova3.domain.enums.EnumPerfil;
import br.com.senior.prova3.domain.enums.EnumSexo;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import br.com.senior.prova3.domain.enums.EnumTipoPessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID id;
	@NotBlank(message = "O campo NOME é requerido")
	private String nome;
	private String telefone;
	@Email
	@NotBlank(message = "O campo EMAIL é requerido")
	private String email;
	@NotBlank(message = "O campo LOGRADOURO é requerido")
	private String logradouro;
	private String complementoLogradouro;
	@NotBlank(message = "O campo NUMEROLOGRADOURO é requerido")
	private String numeroLogradouro;
	@NotNull(message = "O campo PODEEXCLUIR é requerido")
	private EnumSimNao podeExcluir;
	@NotNull(message = "O campo SITUACAO é requerido")
	private EnumAtivoInativo situacao;
	@NotNull(message = "O campo PERFIS é requerido")
	private Set<EnumPerfil> perfis = new HashSet<>();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao = LocalDate.now();
	@NotNull(message = "O campo TIPOPESSOA é requerido")
	private EnumTipoPessoa tipoPessoa;
	private String razaoSocial;
	@NotBlank(message = "O campo CPFCNPJ é requerido")
	private String cpfCnpj;
	private String inscricaoEstadual;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private EnumSexo sexo;
	private EnumEstadoIBGE naturalidade;
	private Long currenttimemillis;
	private List<Pedido> pedidos = new ArrayList<>();
	private List<Produto> produtos = new ArrayList<>();

	public PessoaDTO() {
		super();
	}

	public PessoaDTO(Pessoa pessoa) {
		super();
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.telefone = pessoa.getTelefone();
		this.email = pessoa.getEmail();
		this.logradouro = pessoa.getLogradouro();
		this.complementoLogradouro = pessoa.getComplementoLogradouro();
		this.numeroLogradouro = pessoa.getNumeroLogradouro();
		this.podeExcluir = pessoa.getPodeExcluir();
		this.situacao = pessoa.getSituacao();
		this.perfis = pessoa.getPerfis();
		this.dataCriacao = pessoa.getDataCriacao();
		this.tipoPessoa = pessoa.getTipoPessoa();
		this.razaoSocial = pessoa.getRazaoSocial();
		this.cpfCnpj = pessoa.getCpfCnpj();
		this.inscricaoEstadual = pessoa.getInscricaoEstadual();
		this.dataNascimento = pessoa.getDataNascimento();
		this.sexo = pessoa.getSexo();
		this.naturalidade = pessoa.getNaturalidade();
		this.currenttimemillis = pessoa.getCurrenttimemillis();
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

	public EnumSimNao getPodeExcluir() {
		return podeExcluir;
	}

	public void setPodeExcluir(EnumSimNao podeExcluir) {
		this.podeExcluir = podeExcluir;
	}

	public EnumAtivoInativo getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumAtivoInativo situacao) {
		this.situacao = situacao;
	}

	public Set<EnumPerfil> getPerfis() {
		return this.perfis;
	}

	public void addPerfil(EnumPerfil perfil) {
		this.perfis.add(perfil);
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
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

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
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

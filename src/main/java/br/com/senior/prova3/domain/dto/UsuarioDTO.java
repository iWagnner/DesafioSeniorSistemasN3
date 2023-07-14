package br.com.senior.prova3.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.senior.prova3.domain.Usuario;
import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumEstadoIBGE;
import br.com.senior.prova3.domain.enums.EnumPerfil;
import br.com.senior.prova3.domain.enums.EnumSexo;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import br.com.senior.prova3.domain.enums.EnumTipoPerfilUsuario;
import br.com.senior.prova3.domain.enums.EnumTipoPessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID id;
	@NotBlank(message = "O campo NOME é requerido")
	private String nome;
	@NotBlank(message = "O campo LOGIN é requerido")
	private String login;
	@Pattern(regexp = "^(?=.*\\d).{4,8}$", message = "O campo SENHA deve ter de 4 a 6 digitos contendo pelo menos um digito númerico.")
	@NotBlank(message = "O campo SENHA é requerido")
	private String senha;
	private String telefone;
	@Email
	@NotBlank(message = "O campo EMAIL é requerido")
	private String email;
	@NotBlank(message = "O campo CPF é requerido")
	@CPF
	private String cpf;
	@NotNull(message = "O campo DATANASCIMENTO é requerido")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	@NotNull(message = "O campo LOGRADOURO é requerido")
	private String logradouro;
	private String complementoLogradouro;
	@NotBlank(message = "O campo NUMEROLOGRADOURO é requerido")
	private String numeroLogradouro;
	@NotNull(message = "O campo PERFILUSUARIO é requerido")
	private EnumTipoPerfilUsuario perfilUsuario;
	@NotNull(message = "O campo SITUACAO é requerido")
	private EnumAtivoInativo situacao;
	@NotNull(message = "O campo PERFIS é requerido")
	private Set<EnumPerfil> perfis = new HashSet<>();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao = LocalDate.now();
	private EnumEstadoIBGE naturalidade;
	@NotNull(message = "O campo SEXO é requerido")
	private EnumSexo sexo;
	private EnumTipoPessoa tipoPessoa;
	@NotNull(message = "O campo PODEEXCLUIR é requerido")
	private EnumSimNao podeExcluir;
	private Long currenttimemillis;

	public UsuarioDTO() {
		super();

	}

	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.login = usuario.getLogin();
		this.senha = usuario.getSenha();
		this.telefone = usuario.getTelefone();
		this.email = usuario.getEmail();
		this.cpf = usuario.getCpfCnpj();
		this.dataNascimento = usuario.getDataNascimento();
		this.logradouro = usuario.getLogradouro();
		this.complementoLogradouro = usuario.getComplementoLogradouro();
		this.numeroLogradouro = usuario.getNumeroLogradouro();
		this.perfilUsuario = usuario.getPerfilUsuario();
		this.situacao = usuario.getSituacao();
		this.podeExcluir = usuario.getPodeExcluir();
		this.perfis = usuario.getPerfis();
		this.dataCriacao = usuario.getDataCriacao();
		this.naturalidade = usuario.getNaturalidade();
		this.sexo = usuario.getSexo();
		this.tipoPessoa = usuario.getTipoPessoa();
		this.currenttimemillis = usuario.getCurrenttimemillis();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public EnumSimNao getPodeExcluir() {
		return podeExcluir;
	}

	public void setPodeExcluir(EnumSimNao podeExcluir) {
		this.podeExcluir = podeExcluir;
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

}

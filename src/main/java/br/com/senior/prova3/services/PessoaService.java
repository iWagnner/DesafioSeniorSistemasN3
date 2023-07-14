package br.com.senior.prova3.services;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senior.prova3.domain.Pedido;
import br.com.senior.prova3.domain.Pessoa;
import br.com.senior.prova3.domain.Produto;
import br.com.senior.prova3.domain.dto.PessoaDTO;
import br.com.senior.prova3.domain.enums.EnumAtivoInativo;
import br.com.senior.prova3.domain.enums.EnumEstadoIBGE;
import br.com.senior.prova3.domain.enums.EnumSexo;
import br.com.senior.prova3.domain.enums.EnumSimNao;
import br.com.senior.prova3.domain.enums.EnumTipoPessoa;
import br.com.senior.prova3.repositories.impl.PedidoRepositoryImpl;
import br.com.senior.prova3.repositories.impl.PessoaRepositoryImpl;
import br.com.senior.prova3.repositories.impl.ProdutoRepositoryImpl;
import br.com.senior.prova3.services.exceptions.DataIntegrityViolationException;
import br.com.senior.prova3.services.exceptions.ObjectNotFoundException;
import br.com.senior.prova3.util.Util;
import jakarta.validation.Valid;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
@Service
public class PessoaService {

	@Autowired
	private PessoaRepositoryImpl repository;
	@Autowired
	private EntidadeService entidadeService;
	@Autowired
	private ProdutoRepositoryImpl produtoRepositoryImpl;
	@Autowired
	private PedidoRepositoryImpl pedidoRepositoryImpl;
	

	public Page<Pessoa> findAllPessoas(Pageable pageable, String nome, String email, String cpfCnpj, String razaoSocial, String inscricaoEstadual) {
		Page<Pessoa> pessoas;
		if (Util.stringNullOrEmpty(nome) && Util.stringNullOrEmpty(email) && Util.stringNullOrEmpty(cpfCnpj) && Util.stringNullOrEmpty(razaoSocial) && Util.stringNullOrEmpty(inscricaoEstadual)) {
			pessoas = repository.findAll(pageable);
		} else {
			pessoas = repository.findAllOnFilter(pageable, nome, email, Util.removerEspacosCaracteresEspeciais(cpfCnpj), Util.removerEspacosCaracteresEspeciais(razaoSocial), Util.removerEspacosCaracteresEspeciais(inscricaoEstadual));
		}
		if (pessoas.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum cadastro foi encontrado");
		}
		return pessoas;
	}

	public Pessoa findById(UUID id) {
		Optional<Pessoa> pessoa = repository.findById(id);
		return pessoa.orElseThrow(() -> new ObjectNotFoundException("Entidade não encontrada: " + id));
	}

	public Pessoa create(@Valid PessoaDTO pessoaDTO) {
		validaDadosUnicos(pessoaDTO);
		return repository.save(newPessoa(pessoaDTO));
	}
	
	public Pessoa update(UUID id, @Valid PessoaDTO pessoaDTO) {
		pessoaDTO.setId(id);
		Pessoa oldPessoa = findById(id);
		oldPessoa = newPessoa(pessoaDTO);
		return repository.save(oldPessoa);
	}
	
	public void delete(UUID id) {
		Pessoa pessoa = findById(id);
		if(pessoa.getPedidos().size() > 0 || pessoa.getProdutos().size() > 0) {
			pessoa.setSituacao(EnumAtivoInativo.INATIVO);
			repository.save(pessoa);
		}else {
			repository.deleteById(id);
		}
	}

	public Pessoa newPessoa(PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		
		if (pessoaDTO.getId() != null) {
			Pessoa pessoaExiste = this.findById(pessoaDTO.getId());
			if (pessoaExiste != null) {
				pessoa.setId(pessoaExiste.getId());
				pessoa.setDataCriacao(pessoaExiste.getDataCriacao());
			}
		} else {
			pessoa.setDataCriacao(LocalDate.now());
		}
		
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setTelefone(pessoaDTO.getTelefone());
		pessoa.setEmail(pessoaDTO.getEmail());
		pessoa.setCpfCnpj(Util.removerEspacosCaracteresEspeciais(pessoaDTO.getCpfCnpj()));
		pessoa.setRazaoSocial(Util.removerEspacosCaracteresEspeciais(pessoa.getRazaoSocial()));
		pessoa.setInscricaoEstadual(Util.removerEspacosCaracteresEspeciais(pessoa.getInscricaoEstadual()));
		pessoa.setLogradouro(pessoaDTO.getLogradouro());
		pessoa.setComplementoLogradouro(pessoaDTO.getComplementoLogradouro());
		pessoa.setNumeroLogradouro(pessoaDTO.getNumeroLogradouro());
		pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
		pessoa.setSituacao(EnumAtivoInativo.toEnum(pessoaDTO.getSituacao().getId()));
		pessoa.setNaturalidade(EnumEstadoIBGE.toEnum(pessoaDTO.getNaturalidade().getUf()));
		pessoa.setPerfis(pessoaDTO.getPerfis());
		pessoa.setSexo(EnumSexo.toEnum(pessoaDTO.getSexo().getId()));
		pessoa.setTipoPessoa(EnumTipoPessoa.toEnum(pessoaDTO.getTipoPessoa().getId()));
		pessoa.setPerfis(pessoaDTO.getPerfis());
		pessoa.setPodeExcluir(EnumSimNao.toEnum(pessoaDTO.getPodeExcluir().getId()));
		pessoa.setCurrenttimemillis(System.currentTimeMillis());
		
		return pessoa;
	}
	
	public void validaDadosUnicos(PessoaDTO pessoaDTO) {
		if(pessoaDTO.getCpfCnpj() != null)
		findByCpfCnpj(pessoaDTO.getId(), Util.removerEspacosCaracteresEspeciais(pessoaDTO.getCpfCnpj()));
		
		if(pessoaDTO.getRazaoSocial() != null)
		findByRazaoSocial(pessoaDTO.getId(), Util.removerEspacosCaracteresEspeciais(pessoaDTO.getRazaoSocial()));

		if(pessoaDTO.getInscricaoEstadual() != null)
		findByInscricaoEstadual(pessoaDTO.getId(), Util.removerEspacosCaracteresEspeciais(pessoaDTO.getInscricaoEstadual()));

		if(pessoaDTO.getEmail() != null)
		entidadeService.findbyEmail(pessoaDTO.getId(), pessoaDTO.getEmail());
	}
	
	public void findByCpfCnpj(UUID id, String cpfCnpj) {
		Optional<Pessoa> objPessoa = repository.findByCpfCnpj(cpfCnpj);
		if (objPessoa.isPresent() && objPessoa.get().getId() != id) {
			throw new DataIntegrityViolationException("CPFCNPJ já cadastrado!");
		}
	}
	
	public void findByRazaoSocial(UUID id, String razaoSocial) {
		Optional<Pessoa> objPessoa = repository.findByRazaoSocial(razaoSocial);
		if (objPessoa.isPresent() && objPessoa.get().getId() != id) {
			throw new DataIntegrityViolationException("RAZÂO SOCIAL já cadastrado!");
		}
	}
	
	public void findByInscricaoEstadual(UUID id, String inscricaoEstadual) {
		Optional<Pessoa> objPessoa = repository.findByInscricaoEstadual(inscricaoEstadual);
		if (objPessoa.isPresent() && objPessoa.get().getId() != id) {
			throw new DataIntegrityViolationException("INSCRIÇÂO ESTADUAL já cadastrado!");
		}
	}
	
	public Page<Produto> findAllProdutosIdFornecedor(Pageable pageable, UUID id) {
		Page<Produto> produtos = produtoRepositoryImpl.findAllProdutosIdFornecedor(pageable, id);
		if (produtos.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum produto foi encontrado para esse fornecedor");
		}
		return produtos;
	}
	
	public Page<Pedido> findAllPedidosIdCliente(Pageable pageable, UUID id) {
		Page<Pedido> pedidos = pedidoRepositoryImpl.findAllPedidosByIdCliente(pageable, id);
		if (pedidos.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum pedido foi encontrado para esse cliente");
		}
		return pedidos;
	}
	
}

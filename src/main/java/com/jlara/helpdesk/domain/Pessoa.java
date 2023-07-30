package com.jlara.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.jlara.helpdesk.domain.enums.Perfil;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;




@Entity
public abstract class Pessoa implements Serializable {

	// Número de versão para garantir a consistência da serialização/desserialização
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id; // Identificador único da pessoa

	protected String nome; // Nome da pessoa
	
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
	@Column(unique = true)
	protected String cpf; // CPF da pessoa (deve ser único)

	@Column(unique = true)
	protected String email; // Endereço de e-mail da pessoa (deve ser único)

	protected String senha; // Senha da pessoa

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	protected Set<Integer> perfis = new HashSet<>(); // Conjunto de perfis associados à pessoa

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now(); // Data de criação do registro da pessoa

	public Pessoa() {
		super();
		addPerfil(Perfil.CLIENTE); // Inicializa a pessoa com o perfil de CLIENTE por padrão
	}

	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE); // Inicializa a pessoa com o perfil de CLIENTE por padrão
	}

	// Getters e setters para os atributos da classe

	// Retorna o conjunto de perfis associados à pessoa como um conjunto de objetos do tipo Perfil
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	// Adiciona um perfil ao conjunto de perfis associados à pessoa
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id); // Calcula o hash da pessoa baseado no CPF e ID
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id); // Compara se duas pessoas são iguais com base no CPF e ID
	}

	public Integer getId() {
		return id;
		
	}
}

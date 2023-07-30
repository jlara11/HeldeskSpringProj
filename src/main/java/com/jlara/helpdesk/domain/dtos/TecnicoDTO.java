package com.jlara.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jlara.helpdesk.domain.Tecnico;
import com.jlara.helpdesk.domain.enums.Perfil;

import jakarta.validation.constraints.NotNull;


public class TecnicoDTO implements Serializable {
		private static final long serialVersionUID =1L;
		
		protected Integer id; // Identificador único da pessoa
		@NotNull(message ="O campo NOME é requerido")
		protected String nome; // Nome da pessoa

		@NotNull(message ="O campo CPF é requerido")
		protected String cpf; // CPF da pessoa (deve ser único)

		@NotNull(message ="O campo EMAIL é requerido")
		protected String email; // Endereço de e-mail da pessoa (deve ser único)
		@NotNull(message ="O campo SENHA é requerido")
		protected String senha; // Senha da pessoa

	
		protected Set<Integer> perfis = new HashSet<>(); // Conjunto de perfis associados à pessoa

		@JsonFormat(pattern = "dd/MM/yyyy")
		protected LocalDate dataCriacao = LocalDate.now(); // Data de criação do registro da pessoa

		public TecnicoDTO() {
			super();
			addPerfil(Perfil.CLIENTE);
		}

		public TecnicoDTO(Tecnico obj) {
			super();
			this.id = obj.getId();
			this.nome = obj.getNome();
			this.cpf = obj.getCpf();
			this.email = obj.getEmail();
			this.senha = obj.getSenha();
			this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
			this.dataCriacao = obj.getDataCriacao();
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public Set<Perfil> getPerfis() {
			return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
		}

		public void addPerfil(Perfil perfil) {
			this.perfis.add(perfil.getCodigo());
		}

		public LocalDate getDataCriacao() {
			return dataCriacao;
		}

		public void setDataCriacao(LocalDate dataCriacao) {
			this.dataCriacao = dataCriacao;
		}

		
	}



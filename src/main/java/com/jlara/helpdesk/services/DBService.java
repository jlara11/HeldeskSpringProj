package com.jlara.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlara.helpdesk.domain.Chamado;
import com.jlara.helpdesk.domain.Cliente;
import com.jlara.helpdesk.domain.Tecnico;
import com.jlara.helpdesk.domain.enums.Perfil;
import com.jlara.helpdesk.domain.enums.Prioridade;
import com.jlara.helpdesk.domain.enums.Status;
import com.jlara.helpdesk.repositories.ChamadoRepository;
import com.jlara.helpdesk.repositories.ClienteRepository;
import com.jlara.helpdesk.repositories.PessoaRepository;

@Service
public class DBService {
		@Autowired
		private ChamadoRepository chamadoRepository;
		@Autowired
		private PessoaRepository pessoaRepository;

	
	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Valdir Cesar","83376682887", "valdir@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Lucas Santos ","83379999887", "lucas@mail.com", "123");
		tec2.addPerfil(Perfil.ADMIN);
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "48785873772", "torvalds@mail.com", "123");
		
		 Chamado c1 = new Chamado(null,Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
		
		 pessoaRepository.saveAll(Arrays.asList(tec1, tec2, cli1));
		 chamadoRepository.saveAll(Arrays.asList(c1));
	}

}

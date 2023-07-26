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
import com.jlara.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	//injetandoDependencias
		@Autowired
		private TecnicoRepository tecnicoRepository;
		@Autowired
		private ClienteRepository clienteRepository;
		@Autowired
		private ChamadoRepository chamadoRepository;

	
	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Valdir Cesar","83376682887", "valdir@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "48785873772", "torvalds@mail.com", "123");
		
		 Chamado c1 = new Chamado(null,Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
		
		 tecnicoRepository.saveAll(Arrays.asList(tec1));
		 clienteRepository.saveAll(Arrays.asList(cli1));
		 chamadoRepository.saveAll(Arrays.asList(c1));
	}

}

package com.jlara.helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlara.helpdesk.domain.Chamado;
import com.jlara.helpdesk.repositories.ChamadoRepository;
import com.jlara.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
	public Chamado findById(Integer id) {
		java.util.Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " +id));
	}

	public List<Chamado> findAll() {
		
		return repository.findAll();
	}

}

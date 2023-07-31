package com.jlara.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlara.helpdesk.domain.Pessoa;
import com.jlara.helpdesk.domain.Cliente;
import com.jlara.helpdesk.domain.dtos.ClienteDTO;
import com.jlara.helpdesk.repositories.PessoaRepository;
import com.jlara.helpdesk.repositories.ClienteRepository;
import com.jlara.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.jlara.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +id));
	}

	public List<Cliente> findAll() {
		
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente();
		return repository.save(newObj);
	}
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente();
		return repository.save(oldObj);
	}
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0 ){
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado !");
		}
		repository.deleteById(id);
		
	}



	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent()&& obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Cpf já cadastrado no sistema !");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent()&& obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema !");
		}
		
		
	}



}

package com.jlara.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlara.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}

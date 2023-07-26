package com.jlara.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlara.helpdesk.domain.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

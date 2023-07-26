package com.jlara.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jlara.helpdesk.domain.Chamado;


public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}

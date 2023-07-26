package com.jlara.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jlara.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}

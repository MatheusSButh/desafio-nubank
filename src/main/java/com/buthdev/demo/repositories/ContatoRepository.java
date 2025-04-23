package com.buthdev.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buthdev.demo.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}

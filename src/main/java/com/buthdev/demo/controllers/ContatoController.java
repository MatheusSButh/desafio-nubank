package com.buthdev.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buthdev.demo.dto.ContatoDTO;
import com.buthdev.demo.dto.ContatoResponseDTO;
import com.buthdev.demo.model.Cliente;
import com.buthdev.demo.model.Contato;
import com.buthdev.demo.repositories.ClienteRepository;
import com.buthdev.demo.repositories.ContatoRepository;

@RestController
@RequestMapping(value = "/contatos")
public class ContatoController {

	@Autowired
	ContatoRepository contatoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@PostMapping
	public ResponseEntity<ContatoResponseDTO> createContato(@RequestBody ContatoDTO contatoDto) {
		Contato contato = new Contato();
		contato.setTelefone(contatoDto.getTelefone());
		
		Cliente cliente = clienteRepository.findById(contatoDto.getClienteId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
		contato.setCliente(cliente);
		
		contatoRepository.save(contato);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}

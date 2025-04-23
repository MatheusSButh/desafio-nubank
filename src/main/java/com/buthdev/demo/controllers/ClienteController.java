package com.buthdev.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buthdev.demo.dto.ClienteDTO;
import com.buthdev.demo.dto.ClienteResponseDTO;
import com.buthdev.demo.dto.ContatoResponseDTO;
import com.buthdev.demo.model.Cliente;
import com.buthdev.demo.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody ClienteDTO clienteDto) {
		clienteService.createCliente(clienteDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteResponseDTO>> findAll() {
		return ResponseEntity.ok().body(clienteService.findAll());
	}
	
	@GetMapping(value = "/{id}/contatos")
	public ResponseEntity<List<ContatoResponseDTO>> findContactsByCliente(@PathVariable Long id) {
		List<ContatoResponseDTO> contatos = clienteService.findContactsByCliente(id);
		
		if(contatos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.ok().body(contatos);
	}
	
}

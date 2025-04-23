package com.buthdev.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buthdev.demo.dto.ClienteDTO;
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
		return ResponseEntity.ok().build();
	}
}

package com.buthdev.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buthdev.demo.dto.ClienteDTO;
import com.buthdev.demo.dto.ClienteResponseDTO;
import com.buthdev.demo.dto.ContatoResponseDTO;
import com.buthdev.demo.exceptions.ClienteNotFoundException;
import com.buthdev.demo.model.Cliente;
import com.buthdev.demo.model.Contato;
import com.buthdev.demo.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public Cliente createCliente(ClienteDTO clienteDto) {
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDto.getNome());
		
		if(clienteDto.getContatos() != null) {
			List<Contato> contatos = clienteDto.getContatos().stream().map(x -> {
				Contato contato = new Contato();
				contato.setTelefone(x.getTelefone());
				contato.setCliente(cliente);
				return contato;
			}).collect(Collectors.toList());
			cliente.setContatos(contatos);
		}
		return clienteRepository.save(cliente);
	}
	
	public List<ClienteResponseDTO> findAll() {
		return clienteRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public List<ContatoResponseDTO> findContactsByCliente(Long id) {
		Cliente cliente =  clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		
		List<ContatoResponseDTO> contatosDto = cliente.getContatos().stream().map(x -> {
			ContatoResponseDTO contatoDto = new ContatoResponseDTO();
			contatoDto.setId(x.getId());
			contatoDto.setTelefone(x.getTelefone());
			contatoDto.setClienteId(x.getCliente() != null ? x.getCliente().getId() : null);
			return contatoDto;
		}).collect(Collectors.toList());
		
		return contatosDto;
	}
	
	
	private ClienteResponseDTO toDto(Cliente cliente) {
		ClienteResponseDTO dto = new ClienteResponseDTO();
		
		dto.setNome(cliente.getNome());
		dto.setId(cliente.getId());
		
		List<ContatoResponseDTO> contatos = cliente.getContatos().stream().map(x -> {
			ContatoResponseDTO contatoDto = new ContatoResponseDTO();
			contatoDto.setId(x.getId());
			contatoDto.setTelefone(x.getTelefone());
			contatoDto.setClienteId(x.getCliente() != null ? x.getCliente().getId() : null);
			return contatoDto;
		}).collect(Collectors.toList());
		dto.setContatos(contatos);
		
		return dto;
	}
}

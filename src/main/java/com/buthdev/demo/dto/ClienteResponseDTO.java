package com.buthdev.demo.dto;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
	
	private Long id;
	private String nome;
	private List<ContatoResponseDTO> contatos;
}

package br.edu.infnet.AppElberth.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.infnet.AppElberth.model.domain.Cliente;

public class ClienteDTO {
	private String nome;
	private String email;
	private List<VendaDTO> vendas;

	public ClienteDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		
		if(cliente.getVendas() != null && !cliente.getVendas().isEmpty()) {
			this.vendas = cliente.getVendas().stream().map(VendaDTO::new).collect(Collectors.toList());
		} else {
			this.vendas = List.of();
		}
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public List<VendaDTO> getVendas() {
		return vendas;
	}
}
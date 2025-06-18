package br.edu.infnet.AppElberth.model.dto;

import java.time.LocalDateTime;

import br.edu.infnet.AppElberth.model.domain.Venda;

public class VendaDTO {
	private LocalDateTime data;
	private int quantidade;
	private Integer clienteId;
	
	public VendaDTO(Venda venda) {
		this.data = venda.getData();
		this.quantidade = venda.getQuantidade();
		if(venda.getCliente() != null) {
			this.clienteId = venda.getCliente().getId();
		}
	}

	public LocalDateTime getData() {
		return data;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Integer getClienteId() {
		return clienteId;
	}
}
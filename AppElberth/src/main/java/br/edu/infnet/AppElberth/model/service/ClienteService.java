package br.edu.infnet.AppElberth.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.AppElberth.model.domain.Cliente;
import br.edu.infnet.AppElberth.model.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente incluir(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void excluir(Integer id) {
		
		if(!clienteRepository.existsById(id)) {
			throw new RuntimeException("Cliente não encontrado com o id: " + id);
		}

		clienteRepository.deleteById(id);
	}
	
	public Cliente alterar(Integer id, Cliente cliente) {
		
		if(!clienteRepository.existsById(id)) {
			throw new RuntimeException("Cliente não encontrado com o id: " + id);
		}

		cliente.id = id;
		
		return clienteRepository.save(cliente);
	}
	
	public Iterable<Cliente> obterLista(){
		return clienteRepository.findAll();
	}

	public Cliente obterPorId(Integer id) {		
		return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id: " + id));
	}
}
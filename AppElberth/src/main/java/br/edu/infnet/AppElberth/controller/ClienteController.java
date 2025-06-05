package br.edu.infnet.AppElberth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.AppElberth.model.domain.Cliente;
import br.edu.infnet.AppElberth.model.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/lista")
	public Iterable<Cliente> obterLista(){
		return clienteService.obterLista();
	}
	
	@GetMapping("/{id}")
	public Cliente obterPorId(@PathVariable Integer id) {
		return clienteService.obterPorId(id);
	}
	
	@PostMapping("/incluir")
	public Cliente incluir(@RequestBody Cliente cliente) {
		return clienteService.incluir(cliente);
	}

	@PutMapping("/{id}")
	public Cliente alterar(@PathVariable Integer id, @RequestBody Cliente cliente) {
		return clienteService.alterar(id, cliente);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		clienteService.excluir(id);
	}
}
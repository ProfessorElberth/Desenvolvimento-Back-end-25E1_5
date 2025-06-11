package br.edu.infnet.AppElberth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Iterable<Cliente>> obterLista(){
		
		Iterable<Cliente> lista = clienteService.obterLista();
		
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obterPorId(@PathVariable Integer id) {
		
		Cliente cliente = clienteService.obterPorId(id);
		
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping("/incluir")
	public ResponseEntity<Cliente> incluir(@RequestBody Cliente cliente) {
		
		Cliente newCliente = clienteService.incluir(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newCliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterar(@PathVariable Integer id, @RequestBody Cliente cliente) {
		
		Cliente newCliente = clienteService.alterar(id, cliente);
		
		return ResponseEntity.ok(newCliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		clienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
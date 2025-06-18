package br.edu.infnet.AppElberth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import br.edu.infnet.AppElberth.model.dto.ClienteDTO;
import br.edu.infnet.AppElberth.model.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/lista")
	public ResponseEntity<Iterable<ClienteDTO>> obterLista(){
		
		Iterable<Cliente> lista = clienteService.obterLista();
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		lista.forEach(clientes::add);
		
		List<ClienteDTO> listaClientesDTO = clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
				
		return ResponseEntity.ok(listaClientesDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> obterPorId(@PathVariable Integer id) {
		
		Cliente cliente = clienteService.obterPorId(id);

		return ResponseEntity.ok(new ClienteDTO(cliente));
	}
	
	@PostMapping("/incluir")
	public ResponseEntity<ClienteDTO> incluir(@RequestBody Cliente cliente) {
		
		Cliente newCliente = clienteService.incluir(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ClienteDTO(newCliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> alterar(@PathVariable Integer id, @RequestBody Cliente cliente) {
		
		Cliente newCliente = clienteService.alterar(id, cliente);
		
		return ResponseEntity.ok(new ClienteDTO(newCliente));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		clienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
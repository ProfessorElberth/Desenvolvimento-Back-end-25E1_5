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

import br.edu.infnet.AppElberth.model.domain.Venda;
import br.edu.infnet.AppElberth.model.service.VendaService;

@RestController
@RequestMapping("/venda")
public class VendaController {

	@Autowired
	private VendaService vendaService;
	
	@GetMapping("/lista")
	public Iterable<Venda> obterLista(){
		return vendaService.obterLista();
	}
	
	@GetMapping("/{id}")
	public Venda obterPorId(@PathVariable Integer id) {
		return vendaService.obterPorId(id);
	}
	
	@PostMapping("/incluir")
	public Venda incluir(@RequestBody Venda venda) {
		return vendaService.incluir(venda);
	}

	@PutMapping("/{id}")
	public Venda alterar(@PathVariable Integer id, @RequestBody Venda venda) {
		return vendaService.alterar(id, venda);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		vendaService.excluir(id);
	}
}
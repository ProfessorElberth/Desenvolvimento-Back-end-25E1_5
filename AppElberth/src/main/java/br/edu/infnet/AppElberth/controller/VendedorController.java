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

import br.edu.infnet.AppElberth.model.domain.Vendedor;
import br.edu.infnet.AppElberth.model.service.VendedorService;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

	@Autowired
	private VendedorService vendedorService;
	
	@GetMapping("/lista")
	public Iterable<Vendedor> obterLista(){
		return vendedorService.obterLista();
	}	
	
	@GetMapping("/{id}")
	public Vendedor obterPorId(@PathVariable Integer id) {
		return vendedorService.obterPorId(id);
	}
	
	@PostMapping("/incluir")
	public Vendedor incluir(@RequestBody Vendedor vendedor) {
		return vendedorService.incluir(vendedor);
	}

	@PutMapping("/{id}")
	public Vendedor alterar(@PathVariable Integer id, @RequestBody Vendedor vendedor) {
		return vendedorService.alterar(id, vendedor);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		vendedorService.excluir(id);
	}
}
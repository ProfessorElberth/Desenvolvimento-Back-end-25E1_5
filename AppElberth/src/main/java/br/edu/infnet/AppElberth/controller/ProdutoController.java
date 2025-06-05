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
import br.edu.infnet.AppElberth.model.domain.Produto;
import br.edu.infnet.AppElberth.model.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/lista")
	public Iterable<Produto> obterLista(){
		return produtoService.obterLista();
	}
	
	@GetMapping("/{id}")
	public Produto obterPorId(@PathVariable Integer id) {
		return produtoService.obterPorId(id);
	}
	
	@PostMapping("/incluir")
	public Produto incluir(@RequestBody Produto produto) {
		return produtoService.incluir(produto);
	}

	@PutMapping("/{id}")
	public Produto alterar(@PathVariable Integer id, @RequestBody Produto produto) {
		return produtoService.alterar(id, produto);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		produtoService.excluir(id);
	}
}
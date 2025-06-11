package br.edu.infnet.AppElberth.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.AppElberth.model.domain.Produto;
import br.edu.infnet.AppElberth.model.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto incluir(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Iterable<Produto> obterLista(){
		return produtoRepository.findAll();
	}
	
	public void excluir(Integer id) {
		
		if(!produtoRepository.existsById(id)) {
			throw new RuntimeException("Produto não encontrado com o id: " + id);
		}

		produtoRepository.deleteById(id);
	}
	
	public Produto alterar(Integer id, Produto produto) {
		
		if(!produtoRepository.existsById(id)) {
			throw new RuntimeException("Produto não encontrado com o id: " + id);
		}

		produto.setId(id);
		
		return produtoRepository.save(produto);
	}

	public Produto obterPorId(Integer id) {		
		return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado com o id: " + id));
	}

	public Produto obterPorCodigo(int codigo) {
		return produtoRepository.findByCodigo(codigo).orElseThrow(() -> new RuntimeException("Produto não encontrado com o código: " + codigo));
	}
}
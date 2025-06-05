package br.edu.infnet.AppElberth.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.AppElberth.model.domain.Venda;
import br.edu.infnet.AppElberth.model.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendaRepository; 
	
	public Venda incluir(Venda venda) {
		return vendaRepository.save(venda);
	}
	
	public Iterable<Venda> obterLista(){
		return vendaRepository.findAll();
	}
	
	public void excluir(Integer id) {
		
		if(!vendaRepository.existsById(id)) {
			throw new RuntimeException("Venda não encontrada com o id: " + id);
		}

		vendaRepository.deleteById(id);
	}
	
	public Venda alterar(Integer id, Venda venda) {
		
		if(!vendaRepository.existsById(id)) {
			throw new RuntimeException("Venda não encontrada com o id: " + id);
		}

		venda.id = id;
		
		return vendaRepository.save(venda);
	}

	public Venda obterPorId(Integer id) {		
		return vendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venda não encontrada com o id: " + id));
	}
}

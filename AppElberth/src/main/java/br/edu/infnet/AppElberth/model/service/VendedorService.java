package br.edu.infnet.AppElberth.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.AppElberth.model.domain.Vendedor;
import br.edu.infnet.AppElberth.model.repository.VendedorRepository;

@Service
public class VendedorService {
	
	@Autowired
	private VendedorRepository vendedorRepository;

	public Vendedor incluir(Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}
	
	public Iterable<Vendedor> obterLista(){
		return vendedorRepository.findAll();
	}

	public void excluir(Integer id) {
		
		if(!vendedorRepository.existsById(id)) {
			throw new RuntimeException("Vendedor não encontrado com o id: " + id);
		}

		vendedorRepository.deleteById(id);
	}

	public Vendedor alterar(Integer id, Vendedor vendedor) {
		
		if(!vendedorRepository.existsById(id)) {
			throw new RuntimeException("Vendedor não encontrado com o id: " + id);
		}

		vendedor.setId(id);
		
		return vendedorRepository.save(vendedor);
	}
	
	public Vendedor obterPorId(Integer id) {		
		return vendedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendedor não encontrado com o id: " + id));
	}

	public Vendedor obterPorCPF(String cpf) {
		return vendedorRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Vendedor não encontrado com o CPF: " + cpf));		
	}
}

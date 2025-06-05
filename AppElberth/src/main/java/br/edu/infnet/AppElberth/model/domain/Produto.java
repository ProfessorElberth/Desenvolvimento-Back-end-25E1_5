package br.edu.infnet.AppElberth.model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TProduto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	public int codigo;
	public String nome;
	public String tipo;
	public String categoria;
	public String descricao;
	public float preco;
	public int estoque;
	
	@JsonIgnore
	@ManyToOne
	public Vendedor vendedor;
	
	@ManyToMany(mappedBy = "produtos")
	public List<Venda> vendas;
	
	@Override
	public String toString() {
		
		String mensagem = String.format("O produto %s: %s (%s - %s) - Quantidade: %d; Preço: %f - foi incluído com sucesso!", 
							nome, 
							descricao, 
							tipo, 
							categoria, 
							estoque, 
							preco
						);
		
		return mensagem; 
	}
}
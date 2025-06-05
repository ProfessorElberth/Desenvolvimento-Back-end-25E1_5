package br.edu.infnet.AppElberth.model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TCliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	public String nome;
	public String email;
	public String telefone;
	
	@OneToMany(mappedBy = "cliente")
	public List<Venda> vendas;
	
	@Override
	public String toString() {
		
		return String.format("O cliente %s - %s e %s - foi incluído com sucesso!", nome, email, telefone);
	}
}
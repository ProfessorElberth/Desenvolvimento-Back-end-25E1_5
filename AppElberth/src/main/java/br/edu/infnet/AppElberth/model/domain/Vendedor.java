package br.edu.infnet.AppElberth.model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TVendedor")
public class Vendedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	public String cpf;
	public String nome;
	public String email;
	public String telefone;
	
	@OneToMany(mappedBy = "vendedor")
	public List<Produto> produtos;
	
	@Override
	public String toString() {
		return "O vendedor " + nome + " - " + email + " e " + telefone + " - foi incluído com sucesso!";
	}
}
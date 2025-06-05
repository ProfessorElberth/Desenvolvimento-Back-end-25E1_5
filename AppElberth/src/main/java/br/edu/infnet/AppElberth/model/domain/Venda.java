package br.edu.infnet.AppElberth.model.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TVenda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	public LocalDateTime data;
	public int quantidade;
	
	@ManyToMany
	public List<Produto> produtos;

	@ManyToOne
	public Cliente cliente;

	@Override
	public String toString() {

		return String.format("A venda registrada em %s possui %d produtos!", data, quantidade);
	}
}
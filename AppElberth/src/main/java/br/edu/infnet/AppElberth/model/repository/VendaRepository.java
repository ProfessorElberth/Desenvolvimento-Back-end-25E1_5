package br.edu.infnet.AppElberth.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.AppElberth.model.domain.Venda;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Integer> {

}

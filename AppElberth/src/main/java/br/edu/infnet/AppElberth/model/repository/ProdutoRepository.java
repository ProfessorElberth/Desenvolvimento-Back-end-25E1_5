package br.edu.infnet.AppElberth.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.AppElberth.model.domain.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

	Optional<Produto> findByCodigo(int codigo);
}
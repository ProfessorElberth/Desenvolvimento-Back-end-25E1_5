package br.edu.infnet.AppElberth.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.AppElberth.model.domain.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	Optional<Cliente> findByCpf(String cpf);
}
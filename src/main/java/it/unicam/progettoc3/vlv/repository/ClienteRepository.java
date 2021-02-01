package it.unicam.progettoc3.vlv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.unicam.progettoc3.vlv.entity.utenti.Cliente;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
 public boolean existsByEmail(String email);
}

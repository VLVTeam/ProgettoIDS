package it.unicam.progettoc3.vlv.repository;

import org.springframework.data.repository.CrudRepository;

import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;

public interface CommercianteRepository extends CrudRepository<Commerciante, Long>{
	 public boolean existsByEmail(String email);
}

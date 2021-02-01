package it.unicam.progettoc3.vlv.repository;



import org.springframework.data.repository.CrudRepository;

import it.unicam.progettoc3.vlv.entity.utenti.Corriere;

public interface CorriereRepository  extends CrudRepository<Corriere, Long>{
	 public boolean existsByEmail(String email);
}

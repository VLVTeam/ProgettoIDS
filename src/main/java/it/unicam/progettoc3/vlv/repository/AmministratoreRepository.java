package it.unicam.progettoc3.vlv.repository;

import org.springframework.data.repository.CrudRepository;

import it.unicam.progettoc3.vlv.entity.utenti.Amministratore;

public interface AmministratoreRepository extends CrudRepository<Amministratore, Long>{
	 public boolean existsByEmail(String email);
}

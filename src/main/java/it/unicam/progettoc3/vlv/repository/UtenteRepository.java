package it.unicam.progettoc3.vlv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unicam.progettoc3.vlv.entity.utenti.Utente;

@Repository
/**
 * questa interfaccia permette l'interazione con il database per la gestione di un utente generico
 */
public interface UtenteRepository  extends JpaRepository<Utente, Long>{
	/** metodo per cercare un utente tramite email, restituisce l'eventuale utente trovato */
	Optional<Utente> findByEmail(String email);
	/** metodo per verificare l'esistenza di un utente tramite email, se l'esistenza e' verificata restituisce true, altrimenti false */
	boolean existsByEmail(String email);
}

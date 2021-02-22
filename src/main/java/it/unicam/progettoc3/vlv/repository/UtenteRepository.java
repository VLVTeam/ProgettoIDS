package it.unicam.progettoc3.vlv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unicam.progettoc3.vlv.entity.utenti.Utente;
/**
 * questa interfaccia permette l'interazione con il database per la gestione di un utente generico
 */
@Repository
public interface UtenteRepository  extends JpaRepository<Utente, Long>{
Optional<Utente> findByEmail(String email);
boolean existsByEmail(String email);
}

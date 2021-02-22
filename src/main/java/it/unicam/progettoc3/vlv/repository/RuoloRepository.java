package it.unicam.progettoc3.vlv.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.progettoc3.vlv.entity.utenti.Ruolo;

/**
 * questa interfaccia permette l'interazione con il database per la gestione di un ruolo
 */
public interface RuoloRepository extends JpaRepository<Ruolo, Long> {

}

package it.unicam.progettoc3.vlv.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import it.unicam.progettoc3.vlv.entity.utenti.Corriere;

/**
 * questa interfaccia permette l'interazione con il database per la gestione di un corriere
 */
public interface CorriereRepository extends JpaRepository<Corriere, Long> {

}

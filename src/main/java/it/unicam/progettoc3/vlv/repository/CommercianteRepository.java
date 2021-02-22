package it.unicam.progettoc3.vlv.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import it.unicam.progettoc3.vlv.entity.utenti.Commerciante;

/**
 * questa interfaccia permette l'interazione con il database per la gestione di un commerciante
 */
public interface CommercianteRepository extends JpaRepository<Commerciante, Long> {
	
}

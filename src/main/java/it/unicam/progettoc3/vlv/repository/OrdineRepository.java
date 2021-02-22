package it.unicam.progettoc3.vlv.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import it.unicam.progettoc3.vlv.entity.elementi.Ordine;


/**
 * questa interfaccia permette l'interazione con il database per la gestione di un ordine
 */
public interface OrdineRepository extends JpaRepository<Ordine, Long>{
	
}

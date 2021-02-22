package it.unicam.progettoc3.vlv.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import it.unicam.progettoc3.vlv.entity.utenti.Cliente;

/**
 * questa interfaccia permette l'interazione con il database per la gestione di un cliente
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}

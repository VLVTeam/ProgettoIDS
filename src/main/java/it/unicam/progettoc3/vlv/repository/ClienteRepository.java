package it.unicam.progettoc3.vlv.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import it.unicam.progettoc3.vlv.entity.utenti.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
	
}

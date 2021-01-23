package it.unicam.progettoc3.vlv.entity.elementi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class PuntoDiRitiro {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)	
private Long ID;

@NotBlank
@NotNull
@Column(nullable = false)
private String indirizzo;

public PuntoDiRitiro(){}
public PuntoDiRitiro(String indirizzo) {
	
	this.indirizzo = indirizzo;
}


public Long getID() {
	return ID;
}




public String getIndirizzo() {
	return indirizzo;
}




}

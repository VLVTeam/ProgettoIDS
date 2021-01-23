package it.unicam.progettoc3.vlv.entity.utenti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity
public class Corriere {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long ID;

@Column(nullable = false)
@NotNull
@NotBlank
private String nomeDitta;


private boolean statoIscrizione;


public Corriere( String nomeDitta) {
	
	this.nomeDitta = nomeDitta;
	this.statoIscrizione=false;
}

public Long getID() {
	return ID;
}



public String getNomeDitta() {
	return nomeDitta;
}


public boolean isStatoIscrizione() {
	return statoIscrizione;
}

public void setStatoIscrizione(boolean statoIscrizione) {
	this.statoIscrizione = statoIscrizione;
}




}

package it.unicam.progettoc3.vlv.entity.utenti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
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





@Column(nullable = false )
@NotNull
@NotBlank
private String password;




@Column(nullable = false , unique=true )
@NotNull
@NotBlank
@Email
private String email;


private boolean statoIscrizione;


public Corriere(){}

public Corriere( String nomeDitta ,String email , String password) {
	
	this.nomeDitta = nomeDitta;
	this.statoIscrizione=false;
	this.email=email;
	this.password=password;

}

public Long getID() {
	return ID;
}


public String getNomeDitta() {
	return nomeDitta;
}


public String getPassword() {
	return password;
}

public String getEmail() {
	return email;
}

public boolean isStatoIscrizione() {
	return statoIscrizione;
}

public void setStatoIscrizione(boolean statoIscrizione) {
	this.statoIscrizione = statoIscrizione;
}




}

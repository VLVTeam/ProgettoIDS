package it.unicam.progettoc3.vlv.entity.elementi;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity(name = "punto_di_ritiro")
@Table(name = "punto_di_ritiro")
public class PuntoDiRitiro {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)	
private Long ID;

@NotBlank
@NotNull
@Column(nullable = false)
private String indirizzo;

@JsonIgnore
@JsonManagedReference(value="punto-ordine")
@OnDelete(action = OnDeleteAction.CASCADE)
@OneToMany(  fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy="puntoDiRitiro"  , orphanRemoval=true)
List<Ordine> ordini ;


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

@JsonIgnore
public List<Ordine> getOrdini() {
	return ordini;
}

public void setOrdini(List<Ordine> ordini) {
	this.ordini = ordini;
}
public void setID(Long iD) {
	ID = iD;
}
public void setIndirizzo(String indirizzo) {
	this.indirizzo = indirizzo;
}




}

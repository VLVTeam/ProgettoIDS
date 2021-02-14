package it.unicam.progettoc3.vlv.entity.dto;

import javax.validation.constraints.NotNull;

public class NuovoCorriere extends NuovoUtente {

	
	@NotNull
	private String nomeDitta;

	public String getNomeDitta() {
		return nomeDitta;
	}

	public void setNomeDitta(String nomeDitta) {
		this.nomeDitta = nomeDitta;
	}
	
	public NuovoCorriere(){}
}

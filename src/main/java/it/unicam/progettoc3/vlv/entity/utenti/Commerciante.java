package it.unicam.progettoc3.vlv.entity.utenti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;

@Entity
public class Commerciante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	
	
	@Enumerated(EnumType.STRING)
	private CategorieMerceologiche categoriaMerceologica;
	
	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String indirizzoPuntoVendita;
	
	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String nomePuntoVendita;
	
	
	
private boolean statoIscrizione;

	
	
	@Column(nullable = false )
	@NotNull
	@NotBlank
	private String password;





	@Column(nullable = false , unique=true )
	@NotNull
	@NotBlank
	@Email
	private String email;
	
	
	
	public Commerciante(){}
	public Commerciante(CategorieMerceologiche categoriaMerceologica, String indirizzoPuntoVendita,String nomePuntoVendita , String email , String password) {
		
		this.categoriaMerceologica = categoriaMerceologica;
		this.indirizzoPuntoVendita = indirizzoPuntoVendita;
		this.nomePuntoVendita = nomePuntoVendita;
		this.statoIscrizione = false;
		this.email=email;
		this.password=password;

	}

	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Long getID() {
		return ID;
	}


	


	public CategorieMerceologiche getCategoriaMerceologica() {
		return categoriaMerceologica;
	}




	public String getIndirizzoPuntoVendita() {
		return indirizzoPuntoVendita;
	}


	public void setIndirizzoPuntoVendita(String indirizzoPuntoVendita) {
		this.indirizzoPuntoVendita = indirizzoPuntoVendita;
	}


	public String getNomePuntoVendita() {
		return nomePuntoVendita;
	}
	public boolean isStatoIscrizione() {
		return statoIscrizione;
	}
	public void setStatoIscrizione(boolean statoIscrizione) {
		this.statoIscrizione = statoIscrizione;
	}



	
	
	
	
	
	

}

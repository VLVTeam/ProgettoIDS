package it.unicam.progettoc3.vlv.entity.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NuovoUtente {

	@Email
	public String email;
	@NotBlank
	public String password;
	
	//private Set<String> ruoli = new HashSet<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
/*
	public Set<String> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<String> ruoli) {
		this.ruoli = ruoli;
	}
	
	*/
	
	public NuovoUtente(){}
}

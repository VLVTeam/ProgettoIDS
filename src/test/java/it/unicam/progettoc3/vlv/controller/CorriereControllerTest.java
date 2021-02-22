package it.unicam.progettoc3.vlv.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import it.unicam.progettoc3.vlv.App;
import it.unicam.progettoc3.vlv.C3Test;
import it.unicam.progettoc3.vlv.entity.dto.NuovoCorriere;
import it.unicam.progettoc3.vlv.repository.CommercianteRepository;
import it.unicam.progettoc3.vlv.security.dto.LoginUtente;
import it.unicam.progettoc3.vlv.service.AmministratoreService;
import it.unicam.progettoc3.vlv.service.CorriereService;
import it.unicam.progettoc3.vlv.service.OrdiniService;
import it.unicam.progettoc3.vlv.service.UtenteService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
		"spring.datasource.url = jdbc:mysql://db-mysql-test-do-user-8590225-0.b.db.ondigitalocean.com:25060/defaultdb?",
		"spring.datasource.username=doadmin",
		"spring.datasource.password=vqsdgdimsg9ta9bl",
		"spring.jpa.hibernate.ddl-auto = create-drop" 
	},
	classes = {App.class}
)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CorriereControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	AmministratoreService amministratoreService;
	
	@Autowired
	CorriereService corriereService;
	
	@Autowired
	OrdiniService ordiniService;
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	CommercianteRepository commercianteRepository;

	@Test
	void creaCorriereTest() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCorriere").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCorriere("corriere@gmail.com", "password", "dittaFranco"))))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.messaggio").value("Corriere AGGIUNTO")).andReturn();
		
		corriereService.accettaIscrizioneCorriere(utenteService.getByEmail("corriere@gmail.com").get().getId());
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new LoginUtente("corriere@gmail.com", "password"))))
				.andExpect(status().isOk()).andReturn();

		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCorriere").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCorriere("corriere@gmail.com", "password2", "dittaFranco"))))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.messaggio").value("EMAIL GIA UTILIZZATA")).andReturn();
		
	}
	
	
	
}

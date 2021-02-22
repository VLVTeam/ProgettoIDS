package it.unicam.progettoc3.vlv.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import it.unicam.progettoc3.vlv.entity.dto.NuovoCommerciante;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.repository.CommercianteRepository;
import it.unicam.progettoc3.vlv.security.dto.LoginUtente;
import it.unicam.progettoc3.vlv.service.AmministratoreService;
import it.unicam.progettoc3.vlv.service.CommercianteService;
import it.unicam.progettoc3.vlv.service.OrdiniService;
import it.unicam.progettoc3.vlv.service.UtenteService;

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

public class CommercianteControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	AmministratoreService amministratoreService;
	
	@Autowired
	CommercianteService commercianteService;
	
	@Autowired
	OrdiniService ordiniService;
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	CommercianteRepository commercianteRepository;
	
	@Test
	void creaCommercianteTest() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCommerciante").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCommerciante("commerciante@gmail.com", "password", CategorieMerceologiche.ALIMENTARI, "Via pacco vuoto", "Pippo"))))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.messaggio").value("Commerciante AGGIUNTO")).andReturn();
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new LoginUtente("commerciante@gmail.com", "password"))))
				.andExpect(status().isUnauthorized()).andReturn();

		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCommerciante").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCommerciante("commerciante@gmail.com", "password", CategorieMerceologiche.ALIMENTARI, "Via pacco vuoto", "Pippo"))))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.messaggio").value("EMAIL GIA UTILIZZATA")).andReturn();
		
	}
	
	@Test
	void accettaIscrizioneTest() throws Exception {
		
		commercianteService.accettaIscrizioneCommerciante(utenteService.getByEmail("commerciante@gmail.com").get().getId());
		assertTrue(utenteService.getByEmail("commerciante@gmail.com").get().isStato());
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new LoginUtente("commerciante@gmail.com", "password"))))
				.andExpect(status().isOk()).andReturn();
		
	}
	
}
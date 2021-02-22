package it.unicam.progettoc3.vlv.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

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
import it.unicam.progettoc3.vlv.entity.dto.NuovaPromozione;
import it.unicam.progettoc3.vlv.entity.dto.NuovoCommerciante;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.service.ClienteService;
import it.unicam.progettoc3.vlv.service.CommercianteService;
import it.unicam.progettoc3.vlv.service.CorriereService;
import it.unicam.progettoc3.vlv.service.OrdiniService;
import it.unicam.progettoc3.vlv.service.PromozioniService;
import it.unicam.progettoc3.vlv.service.PuntiDiRitiroService;
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
public class PromozioniControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	CommercianteService commercianteService;
	
	@Autowired
	OrdiniService ordiniService;
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	PuntiDiRitiroService puntoDiRitiro;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	CorriereService corriereService;
	
	@Autowired
	PromozioniService promozioniService;
	
	@Test
	void creaRimuoviFiltraPromozioneTest() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCommerciante").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCommerciante("commerciante2@gmail.com", "password", CategorieMerceologiche.ALIMENTARI, "Via pacco vuoto", "Pippo"))))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.messaggio").value("Commerciante AGGIUNTO")).andReturn();
		
		commercianteService.accettaIscrizioneCommerciante(utenteService.getByEmail("commerciante2@gmail.com").get().getId());
		
		NuovaPromozione nuovaPromozione = new NuovaPromozione("descrizione", java.util.Calendar.getInstance().getTime(), Date.valueOf("2021-10-10"));
		
		promozioniService.addPromozione(nuovaPromozione, "commerciante2@gmail.com");
		
		assertFalse(promozioniService.getPromozioni().get(0).equals(null));
		
		assertFalse(promozioniService.getPromozioniFiltrate(CategorieMerceologiche.ALIMENTARI).get(0).equals(null));
		
		assertFalse(promozioniService.getPromozioniNonScadute().get(0).equals(null));
		
		promozioniService.deletePromozione(promozioniService.getPromozioni().get(0).getID());
		
		Throwable exception = assertThrows(
	            NullPointerException.class, () -> {
	            	promozioniService.getPromozioni().get(0);
	            }
	    );
		assertEquals("Cannot invoke \"java.util.List.get(int)\" because the return value of \"it.unicam.progettoc3.vlv.service.PromozioniService.getPromozioni()\" is null", exception.getMessage());
		
	}
	
	
}

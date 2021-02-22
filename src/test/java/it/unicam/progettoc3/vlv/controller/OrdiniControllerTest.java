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
import it.unicam.progettoc3.vlv.entity.dto.NuovoCliente;
import it.unicam.progettoc3.vlv.entity.dto.NuovoCommerciante;
import it.unicam.progettoc3.vlv.entity.dto.NuovoCorriere;
import it.unicam.progettoc3.vlv.entity.dto.NuovoOrdine;
import it.unicam.progettoc3.vlv.entity.elementi.PuntoDiRitiro;
import it.unicam.progettoc3.vlv.entity.enumeratori.CategorieMerceologiche;
import it.unicam.progettoc3.vlv.service.ClienteService;
import it.unicam.progettoc3.vlv.service.CommercianteService;
import it.unicam.progettoc3.vlv.service.CorriereService;
import it.unicam.progettoc3.vlv.service.OrdiniService;
import it.unicam.progettoc3.vlv.service.PuntiDiRitiroService;
import it.unicam.progettoc3.vlv.service.UtenteService;
import javassist.NotFoundException;

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
public class OrdiniControllerTest {

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

	@Test
	void creaClienteTest() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCliente").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCliente("cliente@gmail.com", "password", "Franco", "Pippo", "Via pacco vuoto"))))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.messaggio").value("Cliente AGGIUNTO")).andReturn();

		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCliente").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCliente("cliente@gmail.com", "password", "Franco", "Pippo", "Via pacco vuoto"))))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.messaggio").value("EMAIL GIA UTILIZZATA")).andReturn();
		
	}
	
	@Test
	void addOrdineTest() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCommerciante").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCommerciante("commerciante@gmail.com", "password", CategorieMerceologiche.ALIMENTARI, "Via pacco vuoto", "Pippo"))))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.messaggio").value("Commerciante AGGIUNTO")).andReturn();
		
		PuntoDiRitiro punto = new PuntoDiRitiro("viaa");
		puntoDiRitiro.addPuntoDiRitiro(punto);
		commercianteService.accettaIscrizioneCommerciante(utenteService.getByEmail("commerciante@gmail.com").get().getId());
		
		NuovoOrdine nuovoOrdine = new NuovoOrdine("codiceRitiro","descrizione",clienteService.getClienti().get(0).getId(),puntoDiRitiro.getPuntiDiRitiro().get(0).getID());
		
		ordiniService.addOrdine(nuovoOrdine, "commerciante@gmail.com");
		
		assertFalse(ordiniService.getOrdiniLiberi().get(0).equals(null));
		
		
		Throwable exception = assertThrows(
	            NotFoundException.class, () -> {
	            	ordiniService.addOrdine(nuovoOrdine, "commerciantez@gmail.com");
	            }
	    );
		assertEquals("commerciante non trovato", exception.getMessage());
		
	}
	
	@Test
	void iterCorriereOrdineTest() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCorriere").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCorriere("corriere@gmail.com", "password","dittaz"))))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.messaggio").value("Corriere AGGIUNTO")).andReturn();
		
		corriereService.accettaIscrizioneCorriere(utenteService.getByEmail("corriere@gmail.com").get().getId());
		
		ordiniService.setPresaInCaricoOrdine(ordiniService.getOrdiniLiberi().get(0).getID(), java.util.Calendar.getInstance().getTime(), "corriere@gmail.com");
		
		assertFalse(ordiniService.getOrdiniDaRitirare("corriere@gmail.com").equals(null));
		
		ordiniService.setDataConsegnaPrevista(ordiniService.getOrdiniDaRitirare("corriere@gmail.com").get(0).getID(), Date.valueOf("2021-10-10"));
		
		assertFalse(ordiniService.getOrdiniInTransito("corriere@gmail.com").equals(null));
		
		Throwable exception = assertThrows(
	            IllegalArgumentException.class, () -> {
	            	ordiniService.setOrdineConsegnato(ordiniService.getOrdiniInTransito("corriere@gmail.com").get(0).getID());
	            }
	    );
		assertEquals("impossibile consegnare ordine , l'ordine deve essere depositato presso un punto di ritiro", exception.getMessage());
		
		ordiniService.setOrdineProntoPerIlRitiro(ordiniService.getOrdiniInTransito("corriere@gmail.com").get(0).getID());
		
		assertFalse(ordiniService.getOrdiniConsegnatiInPuntoRitiro("corriere@gmail.com").equals(null));
		
		Throwable exception2 = assertThrows(
	            IllegalArgumentException.class, () -> {
	            	ordiniService.ritiraOrdine(ordiniService.getOrdiniConsegnatiInPuntoRitiro("corriere@gmail.com").get(0).getID(), "codiceRitiroSbagliato");
	            }
	    );
		assertEquals("CODICE RITIRO ERRATO , IMPOSSIBILE RITIRARE", exception2.getMessage()); 
		
		ordiniService.ritiraOrdine(ordiniService.getOrdiniConsegnatiInPuntoRitiro("corriere@gmail.com").get(0).getID(), "codiceRitiro");
		
		assertFalse(ordiniService.getOrdiniConsegnati("corriere@gmail.com").get(0).equals(null));
		
	}
	
}

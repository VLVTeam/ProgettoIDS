package it.unicam.progettoc3.vlv.controller;

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
import it.unicam.progettoc3.vlv.entity.dto.NuovoCliente;
import it.unicam.progettoc3.vlv.security.dto.LoginUtente;

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

public class ClienteControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void creaClienteTest() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCliente").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCliente("cliente@gmail.com", "password", "Franco", "Pippo", "Via pacco vuoto"))))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.messaggio").value("Cliente AGGIUNTO")).andReturn();

		mvc.perform(MockMvcRequestBuilders.post("/auth/nuovoCliente").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new NuovoCliente("cliente@gmail.com", "password", "Franco", "Pippo", "Via pacco vuoto"))))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.messaggio").value("EMAIL GIA UTILIZZATA")).andReturn();
		
		mvc.perform(MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON)
				.content(C3Test.asJsonString(new LoginUtente("cliente@gmail.com", "password"))))
				.andExpect(status().isOk()).andReturn();
		
	}
	
	/*@Test
	void testValutaProgetto() throws Exception {
		startDataTest();
		jwtCliente = C3Test.getTokenAccesso(mvc, "esperto","pass");
		jwtCliente = C3Test.getTokenAccesso(mvc, "esperto","pass");
		jwtCliente2 = C3Test.getTokenAccesso(mvc, "esperto2","pass");
		
		//richiestaValutazione(mvc, "esperto", 1);
		mvc.perform(MockMvcRequestBuilders.post("/esperto/progetto/valuta/{id_invito}", "proponente1")
				.contentType(MediaType.APPLICATION_JSON)
				.header(JwtTokenFilter.AUTHORIZATION_HEADER_NAME,
						JwtTokenFilter.TOKEN_BEARER_HEADER_NAME + jwtCliente.getToken())
				.content(DoitTest
						.asJsonString(new ValutazioneDto("recensione", new HashSet<ValutazioneProgettistaDto>(), 1))))
				.andExpect(status().isOk());
		
		mvc.perform(MockMvcRequestBuilders.post("/esperto/progetto/valuta/{id_invito}", "proponente1")
				.contentType(MediaType.APPLICATION_JSON)
				.header(JwtTokenFilter.AUTHORIZATION_HEADER_NAME,
						JwtTokenFilter.TOKEN_BEARER_HEADER_NAME + jwtCliente2.getToken())
				.content(DoitTest
						.asJsonString(new ValutazioneDto("recensione", new HashSet<ValutazioneProgettistaDto>(), 1))))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.messaggio").exists()).andReturn();

		mvc.perform(MockMvcRequestBuilders.post("/esperto/progetto/valuta/{id_invito}", "proponente1")
				.contentType(MediaType.APPLICATION_JSON)
				.header(JwtTokenFilter.AUTHORIZATION_HEADER_NAME,
						JwtTokenFilter.TOKEN_BEARER_HEADER_NAME + jwtCliente.getToken())
				.content(DoitTest
						.asJsonString(new ValutazioneDto("recensione", new HashSet<ValutazioneProgettistaDto>(), 1))))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.messaggio").exists()).andReturn();

	}
	
	
	private void startDataTest() throws Exception {
		
		C3Test.generateCliente(mvc, "cliente1@mail.it");
		C3Test.generateCliente(mvc, "cliente2@mail.it");
		C3Test.generateCommerciante(mvc, "commerciante1@mail.it");
		jwtCommerciante = C3Test.getTokenAccesso(mvc, "commerciante1@mail.it", "pass");
		C3Test.generateOrdine(mvc, jwtCommerciante);

	}
	
	
	private void richiestaValutazione(MockMvc mvc, String identificativoEsperto, int idProgetto) throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/proponente/permetti_valutazione").contentType(MediaType.APPLICATION_JSON)
				.header(JwtTokenFilter.AUTHORIZATION_HEADER_NAME,
						JwtTokenFilter.TOKEN_BEARER_HEADER_NAME + jwtProponente.getToken())
				.content(DoitTest.asJsonString(new InvitoDto("contenuto invito", TipologiaInvito.VALUTAZIONE,
						List.of(identificativoEsperto), idProgetto))))
				.andExpect(status().isOk());
	} */
	
	
}

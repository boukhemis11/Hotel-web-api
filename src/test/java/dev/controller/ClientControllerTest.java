/**
 * 
 */
package dev.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.controllers.ClientController;
import dev.entite.Client;
import dev.repositories.ClientRepository;
import dev.service.ClientService;
import net.minidev.json.JSONObject;


/**
 * @author boukh
 *
 */


@WebMvcTest(ClientController.class)
public class ClientControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	@MockBean
	ClientRepository clientRepo; 

	@Test
	void getClientsTest() throws Exception {

		Mockito.when(clientRepo.findAll(PageRequest.of(0, 10)))
				.thenReturn(new PageImpl<>(Arrays.asList(new Client("Dib", "Boukh"))));

		mockMvc.perform(MockMvcRequestBuilders.get("/clients?start=0&size=10"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").isNotEmpty());

	}

	@Test
	void getclientTest() throws Exception {
		Client client = new Client("Dib", "Boukh");
		UUID uuid = client.getUuid();

		Mockito.when(clientRepo.findById(uuid)).thenReturn(Optional.of(client));

		mockMvc.perform(MockMvcRequestBuilders.get("/clients/"+uuid))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Dib"));;

	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	@Test
	void testPostClient() throws Exception {

		Client client = new Client("Boukhemis","Dib");
		JSONObject payload = new JSONObject();
		payload.put("Boukhemis", "Dib");

		when(this.clientRepo.save(client)).thenReturn(client);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/clients/")
		        .content(asJsonString(new Client("Dib","Boukhemis")))
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Dib"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.prenoms").value("Boukhemis"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.uuid").exists());
	}

	
}


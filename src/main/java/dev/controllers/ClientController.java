/**
 * 
 */
package dev.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Client;
import dev.exception.UuidNonTrouveException;
import dev.repositories.ClientRepository;
import dev.service.ClientService;

/**
 * @author boukh
 *
 */

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	
	private ClientRepository clientRepository;
	
	public ClientController(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}
	



	@GetMapping
	public List<Client> getClients(@RequestParam Integer start, @RequestParam Integer size) {
		return clientRepository.findAll(PageRequest.of(start, size)).toList();
	}
  

	@GetMapping("{uuid}")
	public ResponseEntity<?> getClient(@PathVariable UUID uuid) {
		
		Optional<Client> clientOpt = clientRepository.findById(uuid);
		
		if (clientOpt.isPresent()) {
			return ResponseEntity.ok(clientOpt.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("uuid non valide");
		}
	}
	
	@GetMapping("nom/{nom}")
	public List<?> getClientNom(@PathVariable String nom) {
		
		List<Client> client = clientRepository.findByNom(nom);
		return client;
		
	}
	
	@PostMapping
	public ResponseEntity<?> postClient(@RequestBody Client client) {
		
		
		if (client.getNom().length() < 2 || client.getPrenoms().length() < 2) {
			return ResponseEntity.status(400).body("Erreur : nom et prenoms doivent avoir au moins 2 caracteres");
		} else {
			clientRepository.save(client);
			return ResponseEntity.status(200).body(client);
		}

	}


}

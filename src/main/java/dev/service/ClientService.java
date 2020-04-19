/**
 * 
 */
package dev.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.entite.Client;
import dev.repositories.ClientRepository;

/**
 * @author boukh
 *
 */
@Service
public class ClientService {
	private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> listerClients(Integer numPage, Integer taillePage) {
        return clientRepository.findAll(PageRequest.of(numPage, taillePage)).toList();
    }

    public Optional<Client> trouverClient(UUID uuidClient) {
        return clientRepository.findById(uuidClient);
    }

    @Transactional
    public Client creerClient(String nom, String prenoms) {
        return clientRepository.save(new Client(nom, prenoms));
    }
}

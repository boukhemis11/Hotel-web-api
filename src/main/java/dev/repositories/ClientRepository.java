package dev.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.entite.Client;

/**
 * @author boukh
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>{
	


}

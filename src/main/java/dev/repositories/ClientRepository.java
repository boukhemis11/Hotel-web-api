package dev.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.entite.Client;

/**
 * @author boukh
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>{

	List<Client> findByNom(String nom);

}

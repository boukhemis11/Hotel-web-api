/**
 * 
 */
package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Reservation;

/**
 * @author boukh
 *
 */
public interface reservationRepository extends JpaRepository<Reservation, Integer> {

}

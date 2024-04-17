package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // JPA IMPLEMENTA EL MÉTODO
    Client findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);

}

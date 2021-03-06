package com.coffeelovers.pcu.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,Long>{

	Optional<Actor> findByEmail(String actorEmail);

}
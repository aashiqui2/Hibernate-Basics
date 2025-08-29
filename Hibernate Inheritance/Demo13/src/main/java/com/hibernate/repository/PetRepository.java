package com.hibernate.repository;

import com.hibernate.entity.Pet;

/**
 * @author abhishekvermaa10
 *
 */
public interface PetRepository {
	Pet findPet(int petId);
}

package com.hibernate.repository.impl;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import com.hibernate.config.DatabaseConfig;
import com.hibernate.entity.Pet;
import com.hibernate.repository.PetRepository;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;

public class PetRepositoryImpl implements PetRepository {
	private SessionFactory sessionFactory = DatabaseConfig.getSessionFactory();

	@Override
	public Double findAverageAgeOfPet() {
		try (Session session = sessionFactory.openSession()) {
			HibernateCriteriaBuilder hibernateCriteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Double> criteriaQuery = hibernateCriteriaBuilder.createQuery(Double.class);
			Root<Pet> root = criteriaQuery.from(Pet.class);
			Selection<Double> selection = hibernateCriteriaBuilder
					        .avg(hibernateCriteriaBuilder.diff(LocalDate.now().getYear(),
							hibernateCriteriaBuilder.year(root.get("birthDate"))));
			criteriaQuery.select(selection);
			return session.createSelectionQuery(criteriaQuery).getSingleResultOrNull();
		}
	}

}



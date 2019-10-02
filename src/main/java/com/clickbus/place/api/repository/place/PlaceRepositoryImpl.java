package com.clickbus.place.api.repository.place;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.clickbus.place.api.model.Place;
import com.clickbus.place.api.repository.filter.PlaceFilter;

public class PlaceRepositoryImpl implements PlaceRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Place> filter(PlaceFilter filter) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Place> criteriaQuery = criteriaBuilder.createQuery(Place.class);

		Root<Place> root = criteriaQuery.from(Place.class);

		Predicate[] predicates = createRestrictions(filter, criteriaBuilder, root);
		criteriaQuery.where(predicates);

		TypedQuery<Place> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	private Predicate[] createRestrictions(PlaceFilter filter, CriteriaBuilder criteriaBuilder, Root<Place> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filter.getName())) {
			// where name like '%text%'
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
					"%" + filter.getName().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
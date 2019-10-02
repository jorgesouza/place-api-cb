package com.clickbus.place.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickbus.place.api.model.Place;
import com.clickbus.place.api.repository.place.PlaceRepositoryQuery;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryQuery {

}
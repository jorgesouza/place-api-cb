package com.clickbus.place.api.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clickbus.place.api.model.Place;
import com.clickbus.place.api.repository.PlaceRepository;
import com.clickbus.place.api.repository.filter.PlaceFilter;

@Service
public class PlaceService {

	@Autowired
	private PlaceRepository placeRepository;

	public List<Place> filter(PlaceFilter filter) {
		return placeRepository.filter(filter);
	}

	public ResponseEntity<Place> findById(Long id) {
		Optional<Place> place = placeRepository.findById(id);
		return place.isPresent() ? ResponseEntity.ok(place.get()) : ResponseEntity.notFound().build();
	}

	public ResponseEntity<Place> save(@Valid Place request) {
		request.setCreatedAt(LocalDateTime.now());
		Place place = placeRepository.save(request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(place.getId()).toUri();
		return ResponseEntity.created(uri).body(place);
	}

	public void deleteById(Long id) {
		placeRepository.deleteById(id);
	}

	public Place update(Long id, Place request) {
		Place place = placeRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(request, place, "id", "createdAt");
		place.setUpdatedAt(LocalDateTime.now());
		return placeRepository.save(place);
	}
}

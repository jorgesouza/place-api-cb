package com.clickbus.place.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clickbus.place.api.model.Place;
import com.clickbus.place.api.repository.filter.PlaceFilter;
import com.clickbus.place.api.service.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceResource {

	@Autowired
	private PlaceService placeService;

	@GetMapping
	public List<Place> filter(PlaceFilter filter) {
		return placeService.filter(filter);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Place> findById(@PathVariable Long id) {
		return placeService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Place> save(@Valid @RequestBody Place request) {
		return placeService.save(request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		placeService.deleteById(id);
	}

	@PutMapping("/{id}")
	public Place update(@PathVariable Long id, @Valid @RequestBody Place request) {
		return placeService.update(id, request);
	}
}

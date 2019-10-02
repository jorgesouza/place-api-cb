package com.clickbus.place.api.repository.place;

import java.util.List;

import com.clickbus.place.api.model.Place;
import com.clickbus.place.api.repository.filter.PlaceFilter;

public interface PlaceRepositoryQuery {

	public List<Place> filter(PlaceFilter filter);
}

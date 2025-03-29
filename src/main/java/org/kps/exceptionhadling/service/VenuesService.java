package org.kps.exceptionhadling.service;

import org.kps.exceptionhadling.model.Venues;
import org.kps.exceptionhadling.model.dto.RequestVenue;

import java.util.List;

public interface VenuesService {
    List<Venues> getAllVenuesWithPagination(Integer page, Integer size);

    Venues getAllVenueById(Integer id);

    Venues createVenue(RequestVenue requestVenue);

    Venues updateVenueById(Integer id, RequestVenue requestVenue);

    void deleteVenueById(Integer id);
}

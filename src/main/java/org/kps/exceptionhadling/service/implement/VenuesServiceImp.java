package org.kps.exceptionhadling.service.implement;

import lombok.RequiredArgsConstructor;

import org.kps.exceptionhadling.exception.InsertExceptionHandler;
import org.kps.exceptionhadling.exception.NotFoundExceptionHandler;
import org.kps.exceptionhadling.exception.PaginationExceptionHandler;
import org.kps.exceptionhadling.exception.UpdateExceptionHandler;
import org.kps.exceptionhadling.model.Venues;
import org.kps.exceptionhadling.model.dto.RequestVenue;
import org.kps.exceptionhadling.repository.VenuesRepository;
import org.kps.exceptionhadling.service.VenuesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenuesServiceImp implements VenuesService {
    private final VenuesRepository venuesRepository;

    @Override
    public List<Venues> getAllVenuesWithPagination(Integer page, Integer size) {
        List<Venues> venuesList = venuesRepository.findAllVenuesWithPagination(page, size);
        if (venuesList.isEmpty()) {
            throw new PaginationExceptionHandler("Error retrieving venues with pagination (page: " + page + "  size: " + size +")", true);
        }
        return venuesList;
    }

    @Override
    public Venues getAllVenueById(Integer id) {
        Venues venues = venuesRepository.findAllVenueById(id);
        if (venues == null) {
            throw new NotFoundExceptionHandler("Don't have Venue ID " + id + " in database");
        }
        return venues;
    }

    @Override
    public Venues createVenue(RequestVenue requestVenue) {
        Venues venues = venuesRepository.insertVenue(requestVenue);
        if (venues == null) {
            throw new InsertExceptionHandler("Error adding new venue");
        }
        return venues;
    }

    @Override
    public Venues updateVenueById(Integer id, RequestVenue requestVenue) {
        Venues venues = venuesRepository.findAllVenueById(id);
        if (venues == null) {
            throw new UpdateExceptionHandler("Update failed, There no ID " + id + " in database", true);
        }
        return venues;
    }

    @Override
    public void deleteVenueById(Integer id) {
        int rowAffect =  venuesRepository.deleteVenueById(id);
        if (rowAffect == 0) {
            throw new NotFoundExceptionHandler("Delete failed, Don't have ID " + id + " in  database");
        }
    }

}

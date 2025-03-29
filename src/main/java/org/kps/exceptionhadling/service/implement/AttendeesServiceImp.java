package org.kps.exceptionhadling.service.implement;

import lombok.RequiredArgsConstructor;
import org.kps.exceptionhadling.exception.InsertExceptionHandler;
import org.kps.exceptionhadling.exception.NotFoundExceptionHandler;
import org.kps.exceptionhadling.exception.PaginationExceptionHandler;
import org.kps.exceptionhadling.exception.UpdateExceptionHandler;
import org.kps.exceptionhadling.model.Attendees;
import org.kps.exceptionhadling.model.Venues;
import org.kps.exceptionhadling.model.dto.RequestAttendee;
import org.kps.exceptionhadling.repository.AttendeesRepository;
import org.kps.exceptionhadling.service.AttendeesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeesServiceImp implements AttendeesService {
    private final AttendeesRepository attendeesRepository;

    @Override
    public List<Attendees> getAllAttendeesWithPagination(Integer page, Integer size) {
        List<Attendees> attendeesList = attendeesRepository.findAllAttendeesWithPage(page, size);
        if (attendeesList.isEmpty()) {
            throw new PaginationExceptionHandler("Error retrieving Attendees with pagination (page: " + page + "  size: " + size +")", true);
        }
        return attendeesList;
    }

    @Override
    public Attendees getAllAttendeeById(Integer id) {
        Attendees attendee = attendeesRepository.findAttendeeById(id);
        if (attendee == null) {
            throw new NotFoundExceptionHandler("Don't have Venue ID " + id + " in Attendee database");
        }
        return attendee;
    }

    @Override
    public Attendees createAttendee(RequestAttendee requestAttendee) {
        Attendees attendee = attendeesRepository.insertAttendee(requestAttendee);
        if (attendee == null) {
            throw new InsertExceptionHandler("Error adding new Attendee");
        }
        return attendee;
    }

    @Override
    public Attendees updateAttendeeById(Integer id, RequestAttendee requestAttendee) {
        Attendees attendee = attendeesRepository.updateAttendeeById(id, requestAttendee);
        if (attendee == null) {
            throw new UpdateExceptionHandler("Update failed, There no ID " + id + " in Attendee database", true);
        }
        return attendee;
    }

    @Override
    public void deleteAttendeeById(Integer id) {
        int rowAffect =  attendeesRepository.deleteAttendeeById(id);
        if (rowAffect == 0) {
            throw new NotFoundExceptionHandler("Delete failed, Don't have ID " + id + " in Attendee  database");
        }
    }
}

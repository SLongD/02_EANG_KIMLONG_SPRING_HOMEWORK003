package org.kps.exceptionhadling.service.implement;

import lombok.RequiredArgsConstructor;
import org.kps.exceptionhadling.exception.InsertExceptionHandler;
import org.kps.exceptionhadling.exception.NotFoundExceptionHandler;
import org.kps.exceptionhadling.exception.PaginationExceptionHandler;
import org.kps.exceptionhadling.exception.UpdateExceptionHandler;
import org.kps.exceptionhadling.model.Events;
import org.kps.exceptionhadling.model.Venues;
import org.kps.exceptionhadling.model.dto.RequestEvent;
import org.kps.exceptionhadling.repository.EventsRepository;
import org.kps.exceptionhadling.service.EventsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventsServiceImp implements EventsService {
    private final EventsRepository eventsRepository;

    @Override
    public List<Events> getAllEventsWithPagination(Integer page, Integer size) {
        List<Events> eventsList = eventsRepository.findAllEventsWithPagination(page, size);
        if (eventsList.isEmpty()) {
            throw new PaginationExceptionHandler("Error retrieving Event with pagination (page: " + page + "  size: " + size +")", true);
        }
        return eventsList;
    }

    @Override
    public Events getAllEventById(Integer id) {
        Events events = eventsRepository.findEventById(id);
        if (events == null) {
            throw new NotFoundExceptionHandler("Don't have Event ID " + id + " in Event database");
        }
        return events;
    }

    @Override
    public Events createEvent(RequestEvent requestEvent) {
        Integer eventID = eventsRepository.insertEvent(requestEvent);
        for(Integer attendeeId: requestEvent.getAttendeeIds()) {
            eventsRepository.insertEventAttendee(eventID, attendeeId);
        }

        if (eventID == null) {
            throw new InsertExceptionHandler("Error adding new Event");
        }
        return eventsRepository.findEventById(eventID);
    }

    @Override
    public Events updateEventById(Integer id, RequestEvent requestEvent) {
        Integer updateEventId = eventsRepository.updateEventById(id, requestEvent);
        List<Integer> oldAttendeeId = eventsRepository.findAttendeeIdsByEventId(updateEventId);
        List<Integer> newAttendeeId = requestEvent.getAttendeeIds();

        Set<Integer> attendeeIdNeedRemove = new HashSet<>(oldAttendeeId);
        attendeeIdNeedRemove.removeAll(newAttendeeId);

        Set<Integer> attendeeIdNeedAdd = new HashSet<>(newAttendeeId);
        attendeeIdNeedAdd.removeAll(oldAttendeeId);

        if (!attendeeIdNeedRemove.isEmpty()) {
            eventsRepository.removeEventAttendees(updateEventId, new ArrayList<>(attendeeIdNeedRemove));
        }
        for(Integer attendeeId : requestEvent.getAttendeeIds()) {
            eventsRepository.insertNewEventAttendee(updateEventId, attendeeId);
        }
        if(updateEventId == null){
            throw new UpdateExceptionHandler("Update failed, There no ID " + id + " in Event database", true);
        }
        return eventsRepository.findEventById(updateEventId);
    }

    @Override
    public void deleteEventById(Integer id) {
        int rowAffect =  eventsRepository.deleteEventById(id);
        if (rowAffect == 0) {
            throw new NotFoundExceptionHandler("Delete failed, Don't have ID " + id + " in Event database");
        }
        ;
    }
}

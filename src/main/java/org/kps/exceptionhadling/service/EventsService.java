package org.kps.exceptionhadling.service;

import org.kps.exceptionhadling.model.Events;
import org.kps.exceptionhadling.model.dto.RequestEvent;

import java.util.List;

public interface EventsService {
    List<Events> getAllEventsWithPagination(Integer page, Integer size);

    Events getAllEventById(Integer id);

    Events createEvent(RequestEvent requestEvent);

    Events updateEventById(Integer id, RequestEvent requestEvent);

    void deleteEventById(Integer id);
}

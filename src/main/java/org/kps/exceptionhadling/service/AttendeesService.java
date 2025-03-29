package org.kps.exceptionhadling.service;

import org.kps.exceptionhadling.model.Attendees;
import org.kps.exceptionhadling.model.dto.RequestAttendee;

import java.util.List;

public interface AttendeesService {
    List<Attendees> getAllAttendeesWithPagination(Integer page, Integer size);

    Attendees getAllAttendeeById(Integer id);

    Attendees createAttendee(RequestAttendee requestAttendee);

    Attendees updateAttendeeById(Integer id, RequestAttendee requestAttendee);

    void deleteAttendeeById(Integer id);
}

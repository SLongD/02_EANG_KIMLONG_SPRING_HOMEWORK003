package org.kps.exceptionhadling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    private Integer eventId;
    private String eventName;
    private String eventDate;
    private Venues venue;
    private List<Attendees> attendees;
}

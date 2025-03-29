package org.kps.exceptionhadling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendees {
    private int attendeeId;
    private String attendeeName;
    private String attendeeEmail;
}

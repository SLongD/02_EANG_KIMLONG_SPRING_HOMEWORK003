package org.kps.exceptionhadling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venues {
    private int venueId;
    private String venueName;
    private String venueLocation;
}

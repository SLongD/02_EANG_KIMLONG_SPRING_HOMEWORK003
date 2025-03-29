package org.kps.exceptionhadling.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestVenue {
    @NotBlank(message = "Venue name is required")
    @Size(min = 10, max = 100, message = "Venue name must be between 10-100 characters")
    @Pattern(regexp = "^[\\p{L}0-9\\s\\-'.,()]+$", message = "Invalid name input")
    private String venueName;
    @NotBlank(message = "Location is Require")
    @Size(message = "Max characters is 100")
    private String venueLocation;
}

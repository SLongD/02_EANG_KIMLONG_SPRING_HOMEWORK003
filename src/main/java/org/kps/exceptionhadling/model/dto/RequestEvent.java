package org.kps.exceptionhadling.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEvent {
    @NotBlank(message = "Event name is required")
    @Size(min = 10, max = 100, message = "Event name must be 2-100 characters")
    @Pattern(regexp = "^[\\p{L}0-9\\s\\-'.,()]+$", message = "Event name contains invalid characters")
    private String eventName;

    @NotBlank(message = "Event date is required")
    private String eventDate;

    @NotNull(message = "Venue ID is required")
    @Positive(message = "Venue ID must be positive")
    private Integer venueId;

    @Valid
    private List<@Positive(message = "Attendee ID must be positive") Integer> attendeeIds;

}

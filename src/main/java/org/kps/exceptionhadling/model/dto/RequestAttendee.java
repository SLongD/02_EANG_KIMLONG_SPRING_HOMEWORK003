package org.kps.exceptionhadling.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAttendee {
    @NotBlank(message = "Attendee name is required")
    @Size(min = 10, max = 100, message = "Name must be 2-100 characters")
    @Pattern(regexp = "^[\\p{L}0-9\\s\\-'.,()]+$", message = "Name contains invalid characters")
    private String attendeeName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String attendeeEmail;
}

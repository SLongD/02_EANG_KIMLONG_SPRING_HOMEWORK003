package org.kps.exceptionhadling.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kps.exceptionhadling.model.Attendees;
import org.kps.exceptionhadling.model.Attendees;
import org.kps.exceptionhadling.model.dto.RequestAttendee;
import org.kps.exceptionhadling.model.response.ApiResponse;
import org.kps.exceptionhadling.service.AttendeesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendees")
@RequiredArgsConstructor
public class AttendeesController {
    private final AttendeesService attendeesService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendees>>> getAllAttendees(
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "4") Integer size
    ){
        ApiResponse<List<Attendees>> response = ApiResponse.<List<Attendees>>builder()
                .message("get all attendee successfully")
                .payload(attendeesService.getAllAttendeesWithPagination(page, size))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Attendees>> getAttendeeById(@PathVariable("attendeeId") Integer id) {
        Attendees attendee = attendeesService.getAllAttendeeById(id);
        ApiResponse<Attendees> response = ApiResponse.<Attendees>builder()
                .message((attendee !=null)?"get attendees by id successfully":"there is no attendee with id "+id)
                .payload((attendee !=null)?attendee:null)
                .status((attendee !=null)?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Attendees>> createAttendee(@RequestBody @Valid RequestAttendee requestAttendee) {
        ApiResponse<Attendees> response = ApiResponse.<Attendees>builder()
                .message("post attendee successfully")
                .payload(attendeesService.createAttendee(requestAttendee))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Attendees>> updateAttendeeById(@PathVariable("attendeeId") Integer id, @RequestBody @Valid RequestAttendee requestAttendee) {
        ApiResponse<Attendees> response = ApiResponse.<Attendees>builder()
                .message("update attendee by id successfully")
                .payload(attendeesService.updateAttendeeById(id, requestAttendee))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Attendees>> deleteAttendeeById(@PathVariable("attendeeId") Integer id) {
        attendeesService.deleteAttendeeById(id);
        ApiResponse<Attendees> response = ApiResponse.<Attendees>builder()
                .message("delete attendee by id successfully")
                .payload(null)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

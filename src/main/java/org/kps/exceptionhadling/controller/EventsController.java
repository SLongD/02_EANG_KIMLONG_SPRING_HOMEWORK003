package org.kps.exceptionhadling.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kps.exceptionhadling.model.Events;
import org.kps.exceptionhadling.model.dto.RequestEvent;
import org.kps.exceptionhadling.model.response.ApiResponse;
import org.kps.exceptionhadling.service.EventsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/events")
public class EventsController {
    private final EventsService eventsService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Events>>> getAllEvents(
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "4") Integer size
    ){
        ApiResponse<List<Events>> response = ApiResponse.<List<Events>>builder()
                .message("get all event successfully")
                .payload(eventsService.getAllEventsWithPagination(page, size))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Events>> getEventById(@PathVariable("eventId") Integer id) {
        Events event = eventsService.getAllEventById(id);
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .message((event !=null)?"get events by id successfully":"there is no event with id "+id)
                .payload((event !=null)?event:null)
                .status((event !=null)?HttpStatus.OK:HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Events>> createEvent(@RequestBody @Valid RequestEvent requestEvent) {
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .message("post event successfully")
                .payload(eventsService.createEvent(requestEvent))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Events>> updateEventById(@PathVariable("eventId") Integer id, @RequestBody @Valid RequestEvent requestEvent) {
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .message("update event by id successfully")
                .payload(eventsService.updateEventById(id, requestEvent))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Events>> deleteEventById(@PathVariable("eventId") Integer id) {
        eventsService.deleteEventById(id);
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .message("delete event by id successfully")
                .payload(null)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

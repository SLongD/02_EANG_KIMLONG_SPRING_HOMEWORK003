package org.kps.exceptionhadling.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kps.exceptionhadling.model.Venues;
import org.kps.exceptionhadling.model.dto.RequestVenue;
import org.kps.exceptionhadling.model.response.ApiResponse;
import org.kps.exceptionhadling.service.VenuesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/venues")
public class VenuesController {
    private final VenuesService venuesService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venues>>> getAllVenues(
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "4") Integer size
    ){
        ApiResponse<List<Venues>> response = ApiResponse.<List<Venues>>builder()
                .message("get all venues successfully")
                .payload(venuesService.getAllVenuesWithPagination(page, size))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venues>> getVenueById(@PathVariable("venueId") Integer id)  {
        ApiResponse<Venues> response = ApiResponse.<Venues>builder()
                .message("get venue by id successfully")
                .payload(venuesService.getAllVenueById(id))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Venues>> createVenue(@RequestBody @Valid RequestVenue requestVenue) {
        ApiResponse<Venues> response = ApiResponse.<Venues>builder()
                .message("post venue successfully")
                .payload(venuesService.createVenue(requestVenue))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venues>> updateVenueById(@PathVariable("venueId") Integer id, @RequestBody @Valid RequestVenue requestVenue) {
        ApiResponse<Venues> response = ApiResponse.<Venues>builder()
                .message("update venue by id successfully")
                .payload(venuesService.updateVenueById(id, requestVenue))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venues>> deleteVenueById(@PathVariable("venueId") Integer id) {
        venuesService.deleteVenueById(id);
        ApiResponse<Venues> response = ApiResponse.<Venues>builder()
                .message("delete venue by id successfully")
                .payload(null)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

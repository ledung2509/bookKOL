package com.web.bookingKol.domain.kol.rest;


import com.web.bookingKol.common.payload.ApiResponse;
import com.web.bookingKol.domain.kol.dtos.KolAvailabilityDTO;
import com.web.bookingKol.domain.kol.models.KolAvailability;
import com.web.bookingKol.domain.kol.services.KolAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/kol/availabilities")
@RequiredArgsConstructor
public class KolAvailabilityController {

    private final KolAvailabilityService availabilityService;

    @PreAuthorize("hasAuthority('KOL')")
    @GetMapping("/{kolId}/schedule")
    public ResponseEntity<ApiResponse<List<KolAvailabilityDTO>>> getKolSchedule(
            @PathVariable UUID kolId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime end
    ) {
        return ResponseEntity.ok(availabilityService.getKolSchedule(kolId, start, end));
    }

    @PreAuthorize("hasAuthority('KOL')")
    @PostMapping("/{kolId}/schedule")
    public ResponseEntity<ApiResponse<KolAvailabilityDTO>> createKolAvailability(
            @PathVariable UUID kolId,
            @RequestBody KolAvailabilityDTO availabilityDTO
    ) {

        return ResponseEntity.ok(availabilityService.createKolSchedule(kolId,availabilityDTO));
    }

    @PreAuthorize("hasAuthority('KOL')")
    @GetMapping("time-line/{availabilityId}")
    public ResponseEntity<ApiResponse<KolAvailabilityDTO>> getKolAvailabilityById(
            @PathVariable UUID availabilityId
    ) {
        return ResponseEntity.ok(availabilityService.getKolAvailabilityById(availabilityId));
    }
}



package com.radion.rest;

import com.radion.model.dao.TrackRequestDao;
import com.radion.model.dao.TrackResponseDao;
import com.radion.services.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @GetMapping("/track")
    public ResponseEntity<List<TrackResponseDao>> getAllTracks() {
        return ResponseEntity.ok(trackService.getAllTracks());
    }

    @GetMapping("/track/{id}")
    public ResponseEntity<TrackResponseDao> getTrackById(@PathVariable("id") String id) {
        return trackService.getTrackById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/track")
    public ResponseEntity<TrackResponseDao> createTrack(@RequestBody TrackRequestDao trackRequestDao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(trackService.createTrack(trackRequestDao));
    }

    @PutMapping("/track/{id}")
    public ResponseEntity<TrackResponseDao> updateTrackById(@PathVariable("id") String id,
                                                            @RequestBody TrackRequestDao trackRequestDao) {
        return trackService.updateTrackById(id, trackRequestDao)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/track/{id}")
    public ResponseEntity<TrackResponseDao> updateTrackLikeStatusById(@PathVariable("id") String id) {
        return trackService.updateTrackLikeStatusById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/track/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackById(@PathVariable("id") String id) {
        trackService.deleteTrackById(id);
    }

}
package com.radion.services;

import com.radion.model.Track;
import com.radion.model.dao.TrackRequestDao;
import com.radion.model.dao.TrackResponseDao;
import com.radion.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;

    public List<TrackResponseDao> getAllTracks() {
        return trackRepository.findAll().stream()
                .map(Track::toTrackResponseDao)
                .collect(Collectors.toList());
    }

    public Optional<TrackResponseDao> getTrackById(String id) {
        return trackRepository.findById(id)
                .map(Track::toTrackResponseDao);
    }

    public TrackResponseDao createTrack(TrackRequestDao newTrack) {
        var track = new Track(newTrack);
        var savedTrack = trackRepository.save(track);
        return savedTrack.toTrackResponseDao();
    }

    public Optional<TrackResponseDao> updateTrackById(String id,
                                                      TrackRequestDao updatedTrack) {
        var optionalTrack = trackRepository.findById(id);
        optionalTrack.ifPresent(track -> {
            var innerTrack = new Track(updatedTrack);
            innerTrack.setId(id);
            trackRepository.save(innerTrack);
        });

        return optionalTrack.map(Track::toTrackResponseDao);
    }

    public Optional<TrackResponseDao> updateTrackLikeStatusById(String id) {
        var optionalTrack = trackRepository.findById(id);
        optionalTrack.ifPresent(track -> {
            track.setLiked(!track.isLiked());
            trackRepository.save(track);
        });

        return optionalTrack.map(Track::toTrackResponseDao);
    }

    public void deleteTrackById(String id) {
        trackRepository.deleteById(id);
    }
}
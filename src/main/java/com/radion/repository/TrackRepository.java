package com.radion.repository;

import com.radion.model.Track;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, String> {
    List<Track> findAll();
}
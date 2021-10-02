package com.radion.model.dao;

import com.radion.model.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackResponseDao {
    private String id;

    private String name;
    private String author;
    private String album;
    private Genre genre;
    private double duration;
    private int year;
    private boolean liked;
}
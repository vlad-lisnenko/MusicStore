package com.radion.model.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackRequestDao {
    private String name;
    private String author;
    private String album;
    private String genre;
    private double duration;
    private int year;
    private boolean liked;
}

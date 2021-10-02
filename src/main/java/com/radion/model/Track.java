package com.radion.model;

import com.radion.model.dao.TrackRequestDao;
import com.radion.model.dao.TrackResponseDao;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("track")
public class Track {
    @Id
    private String id;

    private String name;
    private String author;
    private String album;
    private Genre genre;
    private double duration;
    private int year;
    private boolean liked;

    public Track(TrackRequestDao trackRequestDao) {
        this.name = trackRequestDao.getName();
        this.author = trackRequestDao.getAuthor();
        this.album = trackRequestDao.getAlbum();
        this.genre = Genre.valueOf(trackRequestDao.getGenre());
        this.duration = trackRequestDao.getDuration();
        this.year = trackRequestDao.getYear();
        this.liked = trackRequestDao.isLiked();
    }

    public TrackResponseDao toTrackResponseDao() {
        var trackResponse = new TrackResponseDao();
        trackResponse.setId(id);
        trackResponse.setName(name);
        trackResponse.setAuthor(author);
        trackResponse.setGenre(genre);
        trackResponse.setLiked(liked);
        trackResponse.setAlbum(album);
        trackResponse.setDuration(duration);
        trackResponse.setYear(year);

        return trackResponse;
    }
}
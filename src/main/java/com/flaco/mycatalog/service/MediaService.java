package com.flaco.mycatalog.service;

import com.flaco.mycatalog.exception.ResourceNotFoundException;
import com.flaco.mycatalog.model.MediaItem;
import com.flaco.mycatalog.model.Movie;
import com.flaco.mycatalog.model.TVShow;
import com.flaco.mycatalog.repository.MediaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MediaService {

    private final MediaItemRepository mediaItemRepository;

    @Autowired
    public MediaService(MediaItemRepository mediaItemRepository) {
        this.mediaItemRepository = mediaItemRepository;
    }

    public List<MediaItem> findAllMediaItems() {
        return mediaItemRepository.findAllByOrderByAddedDateDesc();
    }

    public MediaItem findMediaItemById(Long id) {
        return mediaItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MediaItem non trovato con id: " + id));
    }

    public Movie saveMovie(Movie movie) {
        return mediaItemRepository.save(movie);
    }

    public TVShow saveTVShow(TVShow tvShow) {
        return mediaItemRepository.save(tvShow);
    }

    public Movie updateMovie(Long id, Movie movieDetails) {
        MediaItem item = findMediaItemById(id);
        if (!(item instanceof Movie)) {
            throw new IllegalArgumentException("L'elemento con id " + id + " non è un Film.");
        }
        Movie movie = (Movie) item;
        movie.setTitle(movieDetails.getTitle());
        movie.setStatus(movieDetails.getStatus());
        movie.setPersonalNotes(movieDetails.getPersonalNotes());
        return mediaItemRepository.save(movie);
    }

    public TVShow updateTVShow(Long id, TVShow tvShowDetails) {
        MediaItem item = findMediaItemById(id);
        if (!(item instanceof TVShow)) {
            throw new IllegalArgumentException("L'elemento con id " + id + " non è una Serie TV.");
        }
        TVShow tvShow = (TVShow) item;
        tvShow.setTitle(tvShowDetails.getTitle());
        tvShow.setStatus(tvShowDetails.getStatus());
        tvShow.setPersonalNotes(tvShowDetails.getPersonalNotes());
        tvShow.setLastWatchedSeason(tvShowDetails.getLastWatchedSeason());
        tvShow.setLastWatchedEpisode(tvShowDetails.getLastWatchedEpisode());
        return mediaItemRepository.save(tvShow);
    }

    public void deleteMediaItem(Long id) {
        if (!mediaItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("MediaItem non trovato con id: " + id);
        }
        mediaItemRepository.deleteById(id);
    }
}
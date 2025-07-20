package com.flaco.mycatalog.controller;

import com.flaco.mycatalog.model.MediaItem;
import com.flaco.mycatalog.model.Movie;
import com.flaco.mycatalog.model.TVShow;
import com.flaco.mycatalog.service.MediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")

@CrossOrigin(origins = "*")
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    public List<MediaItem> getAllMediaItems() {
        return mediaService.findAllMediaItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaItem> getMediaItemById(@PathVariable Long id) {
        MediaItem item = mediaService.findMediaItemById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
        Movie createdMovie = mediaService.saveMovie(movie);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @PostMapping("/tvshows")
    public ResponseEntity<TVShow> createTVShow(@Valid @RequestBody TVShow tvShow) {
        TVShow createdTVShow = mediaService.saveTVShow(tvShow);
        return new ResponseEntity<>(createdTVShow, HttpStatus.CREATED);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @Valid @RequestBody Movie movieDetails) {
        Movie updatedMovie = mediaService.updateMovie(id, movieDetails);
        return ResponseEntity.ok(updatedMovie);
    }

    @PutMapping("/tvshows/{id}")
    public ResponseEntity<TVShow> updateTVShow(@PathVariable Long id, @Valid @RequestBody TVShow tvShowDetails) {
        TVShow updatedTVShow = mediaService.updateTVShow(id, tvShowDetails);
        return ResponseEntity.ok(updatedTVShow);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMediaItem(@PathVariable Long id) {
        mediaService.deleteMediaItem(id);
        return ResponseEntity.noContent().build();
    }
}
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

/**
 * Service layer che contiene la logica di business per la gestione dei MediaItem.
 * L'annotazione @Transactional assicura che ogni metodo pubblico sia eseguito
 * all'interno di una transazione del database.
 */
@Service
@Transactional
public class MediaService {

    private final MediaItemRepository mediaItemRepository;

    @Autowired
    public MediaService(MediaItemRepository mediaItemRepository) {
        this.mediaItemRepository = mediaItemRepository;
    }

    /**
     * Recupera tutti gli elementi multimediali, ordinati per data di aggiunta.
     * @return Lista di MediaItem.
     */
    public List<MediaItem> findAllMediaItems() {
        return mediaItemRepository.findAllByOrderByAddedDateDesc();
    }

    /**
     * Trova un elemento per ID.
     * @param id L'ID dell'elemento da cercare.
     * @return Il MediaItem trovato.
     * @throws ResourceNotFoundException se nessun elemento viene trovato con quell'ID.
     */
    public MediaItem findMediaItemById(Long id) {
        return mediaItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MediaItem non trovato con id: " + id));
    }

    /**
     * Salva un nuovo film nel database.
     * @param movie L'oggetto Movie da salvare.
     * @return Il Movie salvato (con l'ID generato).
     */
    public Movie saveMovie(Movie movie) {
        return mediaItemRepository.save(movie);
    }

    /**
     * Salva una nuova serie TV nel database.
     * @param tvShow L'oggetto TVShow da salvare.
     * @return La TVShow salvata (con l'ID generato).
     */
    public TVShow saveTVShow(TVShow tvShow) {
        return mediaItemRepository.save(tvShow);
    }

    /**
     * Aggiorna un film esistente.
     * @param id L'ID del film da aggiornare.
     * @param movieDetails L'oggetto Movie con i dati aggiornati.
     * @return Il Movie aggiornato.
     * @throws ResourceNotFoundException se nessun film viene trovato con quell'ID.
     * @throws IllegalArgumentException se l'ID non corrisponde a un film.
     */
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

    /**
     * Aggiorna una serie TV esistente.
     * @param id L'ID della serie TV da aggiornare.
     * @param tvShowDetails L'oggetto TVShow con i dati aggiornati.
     * @return La TVShow aggiornata.
     * @throws ResourceNotFoundException se nessuna serie TV viene trovata con quell'ID.
     * @throws IllegalArgumentException se l'ID non corrisponde a una serie TV.
     */
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

    /**
     * Cancella un elemento multimediale per ID.
     * @param id L'ID dell'elemento da cancellare.
     * @throws ResourceNotFoundException se nessun elemento viene trovato con quell'ID.
     */
    public void deleteMediaItem(Long id) {
        if (!mediaItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("MediaItem non trovato con id: " + id);
        }
        mediaItemRepository.deleteById(id);
    }
}
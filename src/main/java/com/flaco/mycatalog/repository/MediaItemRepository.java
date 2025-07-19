package com.flaco.mycatalog.repository;

import com.flaco.mycatalog.model.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per l'entità MediaItem.
 * Grazie all'ereditarietà di JPA, questo repository può gestire le operazioni
 * per tutte le sottoclassi di MediaItem (Movie, TVShow).
 * Estende JpaRepository, che fornisce i metodi CRUD di base (save, findById, findAll, delete).
 */
@Repository
public interface MediaItemRepository extends JpaRepository<MediaItem, Long> {

    /**
     * Trova tutti i MediaItem nel database e li ordina per data di aggiunta in ordine decrescente
     * (i più recenti prima).
     * Spring Data JPA genera automaticamente la query basandosi sul nome del metodo.
     *
     * @return una lista di MediaItem ordinati.
     */
    List<MediaItem> findAllByOrderByAddedDateDesc();
}
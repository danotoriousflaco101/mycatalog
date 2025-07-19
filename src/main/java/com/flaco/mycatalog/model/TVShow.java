package com.flaco.mycatalog.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entità che rappresenta una Serie TV.
 * Eredita da MediaItem e aggiunge campi per tracciare la stagione e l'episodio.
 * Il DiscriminatorValue "TV_SHOW" identifica questa classe nella tabella unica.
 */
@Entity
@DiscriminatorValue("TV_SHOW")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class TVShow extends MediaItem {

    @Column(name = "last_watched_season")
    private Integer lastWatchedSeason;

    @Column(name = "last_watched_episode")
    private Integer lastWatchedEpisode;

    // Costruttore per creare una serie TV con tutti i dettagli.
    public TVShow(String title, Status status, String personalNotes, Integer lastWatchedSeason, Integer lastWatchedEpisode) {
        super(title, status, personalNotes);
        this.lastWatchedSeason = lastWatchedSeason;
        this.lastWatchedEpisode = lastWatchedEpisode;
    }
}
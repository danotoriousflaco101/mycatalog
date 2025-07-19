package com.flaco.mycatalog.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entità che rappresenta un Film.
 * Eredita tutti i campi da MediaItem.
 * Il DiscriminatorValue "MOVIE" identifica questa classe nella tabella unica.
 */
@Entity
@DiscriminatorValue("MOVIE")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Movie extends MediaItem {

    // Costruttore specifico per creare un film con i dati necessari.
    public Movie(String title, Status status, String personalNotes) {
        super(title, status, personalNotes);
    }
}
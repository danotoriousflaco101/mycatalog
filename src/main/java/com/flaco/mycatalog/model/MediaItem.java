package com.flaco.mycatalog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe base astratta per tutti gli elementi multimediali (Film, Serie TV, etc.).
 * Utilizza la strategia di ereditarietà SINGLE_TABLE, che è efficiente per questo scenario.
 * Tutti gli elementi saranno salvati in un'unica tabella 'media_item' con una
 * colonna 'media_type' che distingue tra i diversi tipi.
 */
@Entity
@Table(name = "media_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "media_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@ToString
public abstract class MediaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il titolo non può essere vuoto")
    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "added_date")
    private LocalDate addedDate;

    @Column(name = "personal_notes", length = 2000)
    private String personalNotes;

    // Costruttore per i campi essenziali
    public MediaItem(String title, Status status, String personalNotes) {
        this.title = title;
        this.status = status;
        this.personalNotes = personalNotes;
    }

    // Metodo di callback di JPA.
    // Viene eseguito automaticamente prima che un nuovo MediaItem venga salvato
    // per la prima volta nel database. Imposta la data di aggiunta a oggi.
    @PrePersist
    protected void onCreate() {
        this.addedDate = LocalDate.now();
    }

    // È buona pratica sovrascrivere equals e hashCode quando si lavora con le entità JPA.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaItem mediaItem = (MediaItem) o;
        // Se l'ID è nullo, gli oggetti non sono ancora stati salvati,
        // quindi sono uguali solo se sono la stessa istanza.
        // Altrimenti, confronta per ID.
        return id != null && id.equals(mediaItem.id);
    }

    @Override
    public int hashCode() {
        // Usa un valore costante per gli oggetti non ancora persistiti,
        // e l'hash dell'ID per quelli già salvati.
        return id != null ? id.hashCode() : System.identityHashCode(this);
    }
}
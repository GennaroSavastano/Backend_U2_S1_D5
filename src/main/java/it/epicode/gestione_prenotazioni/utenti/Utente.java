package it.epicode.gestione_prenotazioni.utenti;

import it.epicode.gestione_prenotazioni.prenotazioni.Prenotazione;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utenti")

public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;

    public Utente(String email, String nomeCompleto, String username) {
        this.email = email;
        this.nomeCompleto = nomeCompleto;
        this.username = username;
    }

}

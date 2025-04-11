package it.epicode.gestione_prenotazioni.postazioni;


import it.epicode.gestione_prenotazioni.edifici.Edificio;
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
@Table(name = "postazioni")

public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPostazione tipo;

    @Column(nullable = false, unique = true)
    private String codiceUnivoco;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private Integer numeroMassimoOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> prenotazioni;


    public Postazione(TipoPostazione tipo, String codiceUnivoco, String descrizione, Integer numeroMassimoOccupanti, Edificio edificio) {
        this.tipo = tipo;
        this.codiceUnivoco = codiceUnivoco;
        this.descrizione = descrizione;
        this.numeroMassimoOccupanti = numeroMassimoOccupanti;
        this.edificio = edificio;
    }
}

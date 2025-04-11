package it.epicode.gestione_prenotazioni.postazioni;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    // metodo per trovare una postazione per codice
Postazione findByCodiceUnivoco(String codiceUnivoco);
List<Postazione> findByTipoAndEdificio_Citta(String tipo, String citta);
}

package it.epicode.gestione_prenotazioni.prenotazioni;

import it.epicode.gestione_prenotazioni.postazioni.Postazione;
import it.epicode.gestione_prenotazioni.utenti.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long>{
    // controllo se esiste una prenotazione per una postazione e un utente
    boolean existsByUtenteAndDataPrenotazione(Utente utente, LocalDate dataPrenotazione);
    // controllo se esiste una prenotazione per una postazione e una data
    boolean exsistsByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate dataPrenotazione);
    //Trova tutte le prenotazioni per una postazione
    List<Prenotazione> findByPostazione(Postazione postazione);
    //Trova tutte le prenotazioni per un utente
    List<Prenotazione> findByUtente(Utente utente);
    //Trova tutte le prenotazioni per una data
    List<Prenotazione> findByDataPrenotazione(LocalDate dataPrenotazione);
}

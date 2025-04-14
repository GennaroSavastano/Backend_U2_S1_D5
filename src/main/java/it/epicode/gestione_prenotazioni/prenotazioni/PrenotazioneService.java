package it.epicode.gestione_prenotazioni.prenotazioni;

import it.epicode.gestione_prenotazioni.postazioni.Postazione;
import it.epicode.gestione_prenotazioni.postazioni.PostazioneRepository;
import it.epicode.gestione_prenotazioni.utenti.Utente;
import it.epicode.gestione_prenotazioni.utenti.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Transactional
    public Prenotazione savePrenotazione(String username, String codiceUnivoco, LocalDate dataPrenotazione) {
        // Verifica se l'utente esiste
        Utente utente = utenteRepository.findByUsername(username);
        if (utente == null) {
            throw new IllegalArgumentException("Utente non trovato con username: " + username);
        }

        // Verifica se la postazione esiste
        Postazione postazione = postazioneRepository.findByCodiceUnivoco(codiceUnivoco);
        if (postazione == null) {
            throw new IllegalArgumentException("Postazione non trovata con codice univoco: " + codiceUnivoco);
        }

        // Valida data e verifica se la postazione è già prenotata
        if (dataPrenotazione.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data di prenotazione non può essere nel passato");
        }

        // controlla se l'utente ha già prenotato per quella data
        if (prenotazioneRepository.existsByUtenteAndDataPrenotazione(utente, dataPrenotazione)) {
            throw new IllegalArgumentException("L'utente ha già prenotato per quella data");
        }
        // controlla se la postazione è libera per quella data
        if (prenotazioneRepository.existsByPostazioneAndDataPrenotazione(postazione, dataPrenotazione)) {
            throw new IllegalArgumentException("La postazione non é libera per quella data");
        }
        Prenotazione nuovaPrenotazione = new Prenotazione(utente, postazione, dataPrenotazione);
        Prenotazione savedPrenotazione = prenotazioneRepository.save(nuovaPrenotazione);
        // sout  per verificare che la prenotazione sia stata salvata
        System.out.println("Prenotazione salvata con Successo");
        return savedPrenotazione;
    }

    public  Prenotazione findPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata con ID: " + id));
    }

    public List<Prenotazione> findAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public List<Prenotazione> findPrenotazioniByUtente(String username) {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente == null) {
            throw new IllegalArgumentException("Utente non trovato con username: " + username);
        }
        return prenotazioneRepository.findByUtente(utente);
    }

    @Transactional
    public void deletePrenotazione(Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new IllegalArgumentException("Prenotazione non trovata con ID: " + id);
        }
        prenotazioneRepository.deleteById(id);
        // sout  per verificare che la prenotazione sia stata eliminata
        System.out.println("Prenotazione eliminata con ID: " + id);
    }

}

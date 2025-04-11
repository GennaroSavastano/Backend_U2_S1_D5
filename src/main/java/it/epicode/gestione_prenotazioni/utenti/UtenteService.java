package it.epicode.gestione_prenotazioni.utenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente saveUtente(Utente utente) {
        //Verifica che i getter non siano nulli
        if (utente.getNomeCompleto() == null || utente.getUsername() == null || utente.getEmail() == null) {
            throw new IllegalArgumentException("Username,nome e email non possono essere nulli");
        }
        // verifica che l'utente non esista già
        if (utenteRepository.findByUsername(utente.getUsername()) != null) {
            throw new IllegalArgumentException("Utente già esistente");
        }
        Utente savedUtente = utenteRepository.save(utente);
        // sout  per verificare che l'utente sia stato salvato
        System.out.println("Utente salvato con ID: " + savedUtente.getId());
        return savedUtente;
    }

    // utente per id
    public Utente getUtenteById(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato con ID: " + id));
    }

    // Find utente by username
    public Utente findUtenteByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Utente con username " + username  + " non trovato");
        }
        return utenteRepository.findByUsername(username)
    }

    // ottieni tutti gli utenti
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    // elimina utente
    public void deleteUtente(Long id) {
        if (!utenteRepository.existsById(id)) {
            throw new IllegalArgumentException("Utente non trovato con ID: " + id);
        }
        utenteRepository.deleteById(id);
        // sout  per verificare che l'utente sia stato eliminato
        System.out.println("Utente eliminato con ID: " + id);
    }
}

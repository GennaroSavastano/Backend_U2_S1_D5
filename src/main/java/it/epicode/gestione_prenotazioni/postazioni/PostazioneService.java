package it.epicode.gestione_prenotazioni.postazioni;

import it.epicode.gestione_prenotazioni.edifici.EdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private EdificioService edificioService;

    public Postazione savePostazione(Postazione postazione) {
        //Verifica che i getter non siano nulli
        if (postazione.getDescrizione() == null || postazione.getTipo() == null || postazione.getEdificio() == null) {
            throw new IllegalArgumentException("Descrizione, tipo e edificio non possono essere nulli");
        }
        //Verifica che l'edificio esista
        edificioService.getEdificioById(postazione.getEdificio().getId());
        Postazione savedPostazione = postazioneRepository.save(postazione);
        // sout  per verificare che la postazione sia stata salvata
        System.out.println("Postazione salvata con ID: " + savedPostazione.getId());
        return savedPostazione;
    }

    public Postazione getPostazioneById(Long id) {
        return postazioneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Postazione non trovata con ID: " + id));
    }

    // postazione per codice
    public Postazione getPostazioneByCodiceUnivoco(String codiceUnivoco) {
        return postazioneRepository.findByCodiceUnivoco(codiceUnivoco);
    }

    // ottieni tutte le postazioni
    public List<Postazione> getAllPostazioni() {
        return postazioneRepository.findAll();
    }

    // postazione per tipo e città
    public List<Postazione> getPostazioniByTipoAndCitta(String tipo, String citta) {
        return postazioneRepository.findByTipoAndEdificio_Citta(tipo, citta);
    }

    public void deletePostazione(Long id) {
        if (!postazioneRepository.existsById(id)) {
            throw new IllegalArgumentException("Postazione non trovata con ID: " + id);
        }
        postazioneRepository.deleteById(id);
        // sout  per verificare che la postazione sia stata eliminata
        System.out.println("Postazione eliminata con ID: " + id);
    }
}

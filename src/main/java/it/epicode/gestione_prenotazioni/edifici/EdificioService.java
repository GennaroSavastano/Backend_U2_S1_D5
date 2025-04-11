package it.epicode.gestione_prenotazioni.edifici;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdificioService {

    @Autowired
    private EdificioRepository edificioRepository;

    public Edificio saveEdificio(Edificio edificio) {
        //Verifica che i getter non siano nulli
        if (edificio.getNome() == null || edificio.getIndirizzo() == null || edificio.getCitta() == null) {
            throw new IllegalArgumentException("Nome, indirizzo e citta non possono essere nulli");
        }
        Edificio savedEdificio = edificioRepository.save(edificio);
        // sout  per verificare che l'edificio sia stato salvato
        System.out.println("Edificio salvato con ID: " + savedEdificio.getId());
        return savedEdificio;
    }

    public Edificio getEdificioById(Long id) {
        return edificioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Edificio non trovato con ID: " + id));
    }

    // ottieni tutti gli edifici
    public List<Edificio> getAllEdifici() {
        return edificioRepository.findAll();
    }

    public void deleteEdificio(Long id) {
        if (!edificioRepository.existsById(id)) {
            throw new IllegalArgumentException("Edificio non trovato con ID: " + id);
        }
        edificioRepository.deleteById(id);
        // sout  per verificare che l'edificio sia stato eliminato
        System.out.println("Edificio eliminato con ID: " + id);
    }
}

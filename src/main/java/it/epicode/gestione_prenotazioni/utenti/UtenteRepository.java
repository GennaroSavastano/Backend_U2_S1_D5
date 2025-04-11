package it.epicode.gestione_prenotazioni.utenti;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    // metodo di ricerca per username
    Utente findByUsername(String username);
}

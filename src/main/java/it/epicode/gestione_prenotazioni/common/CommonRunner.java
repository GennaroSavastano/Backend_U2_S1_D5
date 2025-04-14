package it.epicode.gestione_prenotazioni.common;


import it.epicode.gestione_prenotazioni.edifici.Edificio;
import it.epicode.gestione_prenotazioni.edifici.EdificioService;
import it.epicode.gestione_prenotazioni.postazioni.Postazione;
import it.epicode.gestione_prenotazioni.postazioni.PostazioneService;
import it.epicode.gestione_prenotazioni.postazioni.TipoPostazione;
import it.epicode.gestione_prenotazioni.prenotazioni.Prenotazione;
import it.epicode.gestione_prenotazioni.prenotazioni.PrenotazioneService;
import it.epicode.gestione_prenotazioni.utenti.Utente;
import it.epicode.gestione_prenotazioni.utenti.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static it.epicode.gestione_prenotazioni.postazioni.TipoPostazione.PRIVATO;

@Component
public class CommonRunner implements CommandLineRunner {

    @Autowired
    private EdificioService edificioService;

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Override

    public void run(String... args) throws Exception {

        try {
            System.out.println("------- CREAZIONE EDIFICI -------");

            Edificio ed1 = new Edificio("Roma 1", "Via Roma 1", "Roma");
            Edificio ed2 = new Edificio("Milano Centro", "Via Fiori Chiari 21", "Milano");
            edificioService.saveEdificio(ed1);
            edificioService.saveEdificio(ed2);

            System.out.println("Edifici creati con ID " + ed1.getId() + " e " + ed2.getId());


            System.out.println("------- CREAZIONE UTENTI -------");

            Utente ut1 = new Utente("marione@test.it", "Mario Carbone", "Marione");
            Utente ut2 = new Utente("giuseppe@test.it", "Giuseppe Malone", "Giuseppone");
            utenteService.saveUtente(ut1);
            utenteService.saveUtente(ut2);

            System.out.println("Utenti creati con ID " + ut1.getId() + " e " + ut2.getId());


            System.out.println("------- CREAZIONE POSTAZIONI -------");

            if (ed1 != null && ed1.getId() != null && ed2 != null && ed2.getId() != null) {
                Postazione p1 = new Postazione(TipoPostazione.PRIVATO, "AA01", "Scrivania Privata", 1, ed1);
                Postazione p2 = new Postazione(TipoPostazione.OPENSPACE, "AA02", "Open Space", 10, ed1);
                Postazione p3 = new Postazione(TipoPostazione.SALA_RIUNIONI, "AA03", "Sala di riunione", 20, ed2);
                Postazione p4 = new Postazione(TipoPostazione.PRIVATO, "AA04", "Scrivania Privata", 1, ed2);
                Postazione p5 = new Postazione(TipoPostazione.OPENSPACE, "AA05", "Open Space", 10, ed2);
                Postazione p6 = new Postazione(TipoPostazione.SALA_RIUNIONI, "AA06", "Sala di riunione", 20, ed2);
                postazioneService.savePostazione(p1);
                postazioneService.savePostazione(p2);
                postazioneService.savePostazione(p3);
                postazioneService.savePostazione(p4);
                postazioneService.savePostazione(p5);
                postazioneService.savePostazione(p6);
                System.out.println("Postazioni create con ID " + p1.getId() + " , " + p2.getId() + " , " + p3.getId() + " , " + p4.getId() + " , " + p5.getId() + " , " + p6.getId());
            } else {
                System.out.println("Errore: Non ci sono Edifici per creare Postazioni");
            }


            System.out.println("------- CREAZIONE PRENOTAZIONI -------");

            LocalDate oggi = LocalDate.now();
            LocalDate domani = oggi.plusDays(1);

            // Prenotazione valida
            try {
                Prenotazione pr1 = prenotazioneService.savePrenotazione("Marione", "AA01", domani);


            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }


            // Prenotazione stessa postazione stesso giorno
            try {
                Prenotazione pr2 = prenotazioneService.savePrenotazione("Giuseppone", "AA01", LocalDate.now().plusDays(1));
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }

            // Prenotazione di un utente gia prenotato per quella data
            try {
                Prenotazione pr3 = prenotazioneService.savePrenotazione("Marione", "AA02", LocalDate.now().plusDays(1));
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            System.out.println("------- FINE ESECUZIONE -------");
        }

}
}

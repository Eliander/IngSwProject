package control;

import dao.DAOSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Ordine;
import model.Spedizioniere;
import model.Uscita;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_RegistraUscita;

public class Listener_AddUscitaButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_AddUscitaButton.class);
    private View_RegistraUscita frame;
    
    private static DAOSettings DAO = Main.getDAO();

    public Listener_AddUscitaButton(View_RegistraUscita frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Uscita usc = this.frame.getUscita();
        Ordine ordine = this.frame.getSelectedOrdine();
        usc.setOrdine(ordine);
        Spedizioniere spedizioniere = this.frame.getSelectedSpedizioniere();
        usc.setSpedizioniere(spedizioniere);
        if(usc.getArticoli().size()>0){
            if(usc.getOrdine()!=null && usc.getSpedizioniere()!=null){
                if(usc.checkUscita()){
                    //aggiungere da DAO l'uscita alla lista uscite
                    if(DAO.getUscitaDAO().addUscita(usc) && DAO.getOrdineDAO().setCompletato(ordine)){
                        Utente user = this.frame.getUser();
                        this.frame.dispose();
                        View_RegistraUscita view_RegistraUscita = new View_RegistraUscita(user);
                        view_RegistraUscita.setVisible(true);
                        log.info("Uscita creata");
                        Main.showPopup(view_RegistraUscita, "Uscita creata");
                    }
                }
                else{
                    log.error("ERRORE: Gli articoli dell'uscita non rispettano l'ordine!");
                    Main.showPopup(frame, "ERRORE: Gli articoli dell'uscita non rispettano l'ordine!");
                }
            }
            else{
                log.error("ERRORE: Selezionare un ordine ed uno spedizioniere!");
                Main.showPopup(frame, "ERRORE: Selezionare un ordine ed uno spedizioniere!");
            }
        }
        else{
            log.error("ERRORE: Selezionare gli articoli dell'uscita!");
            Main.showPopup(frame, "ERRORE: Selezionare gli articoli dell'uscita!");
        }
    }
}

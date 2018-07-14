package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Ordine;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_CreaOrdine;

/**
 *
 * @author Bosky
 */
public class Listener_ConfermaOrdineButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_ConfermaOrdineButton.class);
    private View_CreaOrdine frame;

    public Listener_ConfermaOrdineButton(View_CreaOrdine frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Ordine ord = this.frame.getOrdine();
        if(ord.getArticoli().size() > 0){
            //TO DO
            //aggiungere da DAO l'ordine alla lista ordini
            Utente user = this.frame.getUser();
            this.frame.dispose();
            View_CreaOrdine view_CreaOrdine = new View_CreaOrdine(user);
            view_CreaOrdine.setVisible(true);
            System.out.println("Ordine creato");
        }
    }
}
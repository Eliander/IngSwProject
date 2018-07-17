package control;

import dao.DAOSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Ordine;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_CreaOrdine;

public class Listener_ConfermaOrdineButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_ConfermaOrdineButton.class);
    private View_CreaOrdine frame;
    
    private static DAOSettings DAO = Main.getDAO();

    public Listener_ConfermaOrdineButton(View_CreaOrdine frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Ordine ord = this.frame.getOrdine();
        if(ord.getArticoli().size() > 0){
            //aggiungere da DAO l'ordine alla lista ordini
            if(DAO.getOrdineDAO().addOrdine(ord)){
                Utente user = this.frame.getUser();
                this.frame.dispose();
                View_CreaOrdine view_CreaOrdine = new View_CreaOrdine(user);
                view_CreaOrdine.setVisible(true);
                log.info("Ordine creato");
                Main.showPopup(view_CreaOrdine, "Ordine creato");
            }
        }
    }
}

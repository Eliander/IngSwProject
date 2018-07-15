package control;

import dao.DAOSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ArticoloMagazzino;
import model.Posizione;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_SpostaArticoli;

/**
 *
 * @author Bosky
 */
public class Listener_SpostaButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_SpostaButton.class);
    private View_SpostaArticoli frame;
    
    private static DAOSettings DAO = Main.getDAO();

    public Listener_SpostaButton(View_SpostaArticoli frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ArticoloMagazzino artmag = this.frame.getSelectedArticolo();
        int scaffale = this.frame.getSelectedScaffale();
        int ripiano = this.frame.getSelectedRipiano();
        if(artmag != null){
            //TO DO
            //cambiare da DAO la posizione
            //if(DAO.getArticoloMagazzinoDAO().moveArticoloMagazzino(artmag, new Posizione(scaffale,ripiano))){
                Utente user = this.frame.getUser();
                this.frame.dispose();
                View_SpostaArticoli view_SpostaArticoli = new View_SpostaArticoli(user);
                view_SpostaArticoli.setVisible(true);
                System.out.println("Articolo spostato");
            //}
        }
    }
}

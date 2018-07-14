package control;

import dao.DAOSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Articolo;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_InserisciArticolo;

/**
 *
 * @author Bosky
 */
public class Listener_AddArticoloButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_AddArticoloButton.class);
    private View_InserisciArticolo frame;
    
    private static DAOSettings DAO = Main.getDAO();

    public Listener_AddArticoloButton(View_InserisciArticolo frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Articolo art = this.frame.getArticolo();
        art.setNome(this.frame.getSelectedNome());
        art.setDescrizione(this.frame.getSelectedDesc());
        art.setSport(this.frame.getSelectedSport());
        art.setCategoria(this.frame.getSelectedCategoria());
        //materiali sono già settati
        double prezzo;
        try{
            prezzo = Double.parseDouble(this.frame.getSelectedPrezzo());
        }
        catch(Exception ex){
            prezzo = -1;
        }
        art.setPrezzo(prezzo);
        //controllo campi
        if(art.getNome().equals("") || art.getDescrizione().equals("") || art.getPrezzo()==-1){
            System.out.println("ERRORE: Completa i campi correttamente!");
        }
        else{
            //aggiungere da DAO l'articolo al catalogo
            if(DAO.getArticoloDAO().addArticolo(art)){
                Utente user = this.frame.getUser();
                this.frame.dispose();
                View_InserisciArticolo view_InserisciArticolo = new View_InserisciArticolo(user);
                view_InserisciArticolo.setVisible(true);
                System.out.println("Articolo creato");
            }
        }
        
    }
}

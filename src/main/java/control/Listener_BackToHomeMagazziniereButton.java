package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_HomeMagazziniere;
import view.View_RegistraIngresso;
import view.View_RegistraUscita;
import view.View_SpostaArticoli;

public class Listener_BackToHomeMagazziniereButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_BackToHomeMagazziniereButton.class);
    private JFrame frame;

    public Listener_BackToHomeMagazziniereButton(View_RegistraIngresso frame) {
        this.frame = frame;
    }
    
    public Listener_BackToHomeMagazziniereButton(View_RegistraUscita frame) {
        this.frame = frame;
    }
    
    public Listener_BackToHomeMagazziniereButton(View_SpostaArticoli frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user;
        if(this.frame instanceof View_RegistraIngresso){
            user = ((View_RegistraIngresso)this.frame).getUser();
        }
        else if(this.frame instanceof View_RegistraUscita){
            user = ((View_RegistraUscita)this.frame).getUser();
        }
        else{
            user = ((View_SpostaArticoli)this.frame).getUser();
        }
        this.frame.dispose();
        View_HomeMagazziniere view_HomeMagazziniere = new View_HomeMagazziniere(user);
        view_HomeMagazziniere.setVisible(true);
    }
}

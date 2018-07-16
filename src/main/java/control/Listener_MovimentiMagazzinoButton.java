package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_HomeSegretario;
import view.View_MovimentiMagazzino;

public class Listener_MovimentiMagazzinoButton implements ActionListener{
    private final static Logger log = LogManager.getLogger(Listener_MovimentiMagazzinoButton.class);
    private View_HomeSegretario frame;

    public Listener_MovimentiMagazzinoButton(View_HomeSegretario frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Utente user = this.frame.getUser();
        this.frame.dispose();
        View_MovimentiMagazzino view_MovimentiMagazzino = new View_MovimentiMagazzino(user);
        view_MovimentiMagazzino.setVisible(true);
    }
}

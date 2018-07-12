/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAOSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Magazziniere;
import model.Responsabile;
import model.Segretario;
import model.Utente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_HomeMagazziniere;
import view.View_Login;

/**
 *
 * @author Elia
 */
public class Listener_LoginButton implements ActionListener {

    private final static Logger log = LogManager.getLogger(Listener_LoginButton.class);
    private View_Login frame;

    public Listener_LoginButton(View_Login frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = frame.getTextField_username().getText();
        String password = new String(frame.getPasswordField_password().getPassword());
        DAOSettings DAO = Main.getDAO();
        //to do: prendere da db
        Utente user = Main.getDAO().getUtenteDAO().login(username, password);
        if (!(user == null)) {
            frame.dispose();
            log.info("Login effettuato con successo - username: " + username);
            if (user instanceof Segretario) {
                //segretario
            } else if (user instanceof Responsabile) {

            } else if (user instanceof Magazziniere) {
                View_HomeMagazziniere view_HomeMagazziniere = new View_HomeMagazziniere(user);
                view_HomeMagazziniere.setVisible(true);
            }
        } else {
            frame.dispose();
            this.frame = new View_Login();
            this.frame.getLabel_loginError().setVisible(true);
            frame.setVisible(true);
            log.error("Errore nel login - username: " + username);
        }
    }

}

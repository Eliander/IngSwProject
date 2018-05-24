/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
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
    
    public Listener_LoginButton(View_Login frame){
        this.frame = frame;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = frame.getTextField_username().getText();
        String password = new String(frame.getPasswordField_password().getPassword());
        if (username.equals("user") && password.equals("pw")) {
            frame.dispose();
            log.info("Hai premuto login");
            System.out.println("Hai premuto login");
            View_HomeMagazziniere view_HomeMagazziniere = new View_HomeMagazziniere(username);
            view_HomeMagazziniere.setVisible(true);
        }else{
            frame.dispose();
            this.frame = new View_Login();
            frame.setVisible(true);
            System.out.println("errore");
        }
    }

}

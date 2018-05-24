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
import view.View_Login;

/**
 *
 * @author Elia
 */
public class Listener_LogoutButton implements ActionListener{
 
    private final static Logger log = LogManager.getLogger(Listener_LogoutButton.class);
    private JFrame frame;

    public Listener_LogoutButton(JFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        frame = new View_Login();
        frame.setVisible(true);
        log.info("Hai premuto logout");
        System.out.println("Hai premuto logout");
    }
}

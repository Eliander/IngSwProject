/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Elia
 */
public class Listener_LoginButton implements ActionListener {

    private final static Logger log = LogManager.getLogger(Listener_LoginButton.class);

    @Override
    public void actionPerformed(ActionEvent e) {
        log.info("Hai premuto login");
        System.out.println("Hai premuto login");
    }

}

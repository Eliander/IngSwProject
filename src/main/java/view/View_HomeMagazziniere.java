package view;

import control.Listener_LogoutButton;
import control.Listener_RegistraIngressoButton;
import control.Listener_RegistraUscitaButton;
import control.Listener_SpostaArticoliButton;
import control.Main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import model.Utente;

public class View_HomeMagazziniere extends JFrame{
    
    private Utente user = null;
    
    private JPanel head_panel;
    private JPanel buttons_panel;
    //scritta che dice "ciao, nomeResponsabile"
    private JLabel label_nome;
    //bottone che registra degli ingressi
    private JButton button_registraIngresso;
    //bottone che da accesso alle funzioni di uscita
    private JButton button_registraUscita;
    private JButton button_spostaArticoli;
    //bottone per il logout
    private JButton button_logout;
    private Box head_box;
    
    public View_HomeMagazziniere(Utente user) {
        this.user = user;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(Main.getWindowsSize());
        setResizable(false);
        
        head_panel = new JPanel();
        buttons_panel = new JPanel();
        label_nome = new JLabel();
        button_registraIngresso = new JButton();
        button_registraUscita = new JButton();
        button_spostaArticoli = new JButton();
        button_logout = new JButton();

        label_nome.setText("Ciao " + user.getNome());

        button_registraIngresso.setText("Registra ingresso");
        button_registraIngresso.addActionListener(new Listener_RegistraIngressoButton(this));

        button_registraUscita.setText("Registra uscita");
        button_registraUscita.addActionListener(new Listener_RegistraUscitaButton(this));
        
        button_spostaArticoli.setText("Sposta articoli");
        button_spostaArticoli.addActionListener(new Listener_SpostaArticoliButton(this));

        button_logout.setText("LOGOUT");
        button_logout.addActionListener(new Listener_LogoutButton(this));

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        head_box = Box.createHorizontalBox();
        head_box.add(Box.createRigidArea(new Dimension(10,50)));
        head_box.add(label_nome);
        head_box.add(Box.createHorizontalGlue());
        head_box.add(button_logout);
        head_box.add(Box.createRigidArea(new Dimension(10,50)));
        contentPane.add(head_box, BorderLayout.NORTH);
        
        buttons_panel.setLayout(new GridLayout(3,1));
        buttons_panel.add(button_registraIngresso);
        buttons_panel.add(button_registraUscita);
        buttons_panel.add(button_spostaArticoli);
        contentPane.add(buttons_panel, BorderLayout.CENTER);
        
        this.pack();
    }
    
    public Utente getUser(){
        return this.user;
    }
}

package view;


import control.Listener_LogoutButton;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import model.Utente;

/**
 *
 * @author Eliander
 */
public class View_HomeMagazziniere extends JFrame{
    
    private JPanel panel; 
    //scritta che dice "ciao, nomeMagazziniere"
    private JLabel label_nome;
    //bottone che da accesso alle funzioni di ingresso
    private JButton button_ingresso;
    //bottone che da accesso alle funzioni di uscita
    private JButton button_uscita;
    //bottone che da accesso alle funzioni di spostamento
    private JButton button_sposta;
    //bottone per il logout
    private JButton button_logout;
    //nome preso da db
    private Utente user = null;
    
    //non posso vedere se non sono loggato
    /*
    public View_HomeMagazziniere() {
        initComponents();
    }*/
    
    public View_HomeMagazziniere(Utente user) {
        this.user = user;
        initComponents();
    }
                     
    private void initComponents() {

        panel = new JPanel();
        label_nome = new JLabel();
        button_ingresso = new JButton();
        button_uscita = new JButton();
        button_sposta = new JButton();
        button_logout = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        setResizable(false);

        label_nome.setText("Ciao " + user.getNome());

        button_ingresso.setText("Ingresso in magazzino");
        button_ingresso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        button_uscita.setText("Uscita dal magazzino");

        button_sposta.setText("Sporta articoli");

        button_logout.setText("LOGOUT");
        button_logout.addActionListener(new Listener_LogoutButton(this));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(label_nome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(button_logout)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_uscita)
                            .addComponent(button_ingresso))
                        .addGap(114, 114, 114))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(button_sposta)
                        .addGap(141, 141, 141))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_nome)
                    .addComponent(button_logout))
                .addGap(24, 24, 24)
                .addComponent(button_ingresso)
                .addGap(27, 27, 27)
                .addComponent(button_uscita)
                .addGap(33, 33, 33)
                .addComponent(button_sposta)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                      

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        
                                      
}

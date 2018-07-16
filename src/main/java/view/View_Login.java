package view;

import control.Main;
import control.Listener_CapsLock;
import control.Listener_LoginButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import static javax.swing.JComponent.WHEN_FOCUSED;
import javax.swing.KeyStroke;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class View_Login extends JFrame {

    //bottone login
    private JButton button_login;
    //label con scritto username
    private JLabel label_username;
    //label con scritto password
    private JLabel label_password;
    //label del titolo
    private JLabel label_titolo;

    private JPanel panel;
    //campo dove scrivere username
    private JPasswordField passwordField_password;
    //campo dove scrivere password
    private JTextField textField_username;
    //scritta capsLock
    private JLabel label_capsLock;
    //scritta errore nel login
    private JLabel label_loginError;

    private final Listener_CapsLock listener_CapsLock = new Listener_CapsLock();
    private final static Logger log = LogManager.getLogger(View_Login.class);

    public View_Login() {
        initComponents();
    }

    private void initComponents() {

        panel = new JPanel();
        button_login = new JButton();
        label_username = new JLabel();
        label_password = new JLabel();
        passwordField_password = new JPasswordField(10);
        textField_username = new JTextField();
        label_titolo = new JLabel();
        label_capsLock = listener_CapsLock.getCaps();
        label_loginError = new JLabel();

        label_capsLock.setVisible(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(Main.getWindowsSize());
        setResizable(false);

        panel.setPreferredSize(new Dimension(400, 400));
        //to do: mettere il focus al pannello
        panel.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_CAPS_LOCK, 0), "caps");
        panel.getActionMap().put("caps", listener_CapsLock);

        button_login.setText("Login");
        button_login.addActionListener(new Listener_LoginButton(this));

        label_username.setText("USERNAME");

        label_password.setText("PASSWORD");

        passwordField_password.setText("");

        textField_username.setText("");
        
        label_loginError.setText("Username o password non corretti");
        label_loginError.setForeground(Color.red);
        label_loginError.setVisible(false);

        label_titolo.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        label_titolo.setText("MAGAZZINO");
        label_titolo.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label_capsLock)
                                .addComponent(label_loginError)
                                .addComponent(button_login)
                                .addGap(64, 64, 64))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(91, 91, 91)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(label_username, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label_password))
                                                .addGap(30, 30, 30)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(passwordField_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(label_titolo)))
                                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(label_titolo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_username)
                                        .addComponent(textField_username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_password)
                                        .addComponent(passwordField_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addComponent(label_capsLock)
                                .addGap(21, 21, 21)
                                .addComponent(label_loginError)
                                .addGap(21, 21, 21)
                                //.addGap(41, 41, 41)
                                .addComponent(button_login)
                                .addGap(52, 52, 52))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    public JPasswordField getPasswordField_password() {
        return passwordField_password;
    }

    public void setPasswordField_password(JPasswordField passwordField_password) {
        this.passwordField_password = passwordField_password;
    }

    public JTextField getTextField_username() {
        return textField_username;
    }

    public void setTextField_username(JTextField textField_username) {
        this.textField_username = textField_username;
    }

    public JLabel getLabel_capsLock() {
        return label_capsLock;
    }

    public void setLabel_capsLock(JLabel label_capsLock) {
        this.label_capsLock = label_capsLock;
    }

    public JLabel getLabel_loginError() {
        return label_loginError;
    }

    public void setLabel_loginError(JLabel label_loginError) {
        this.label_loginError = label_loginError;
    }
    
}

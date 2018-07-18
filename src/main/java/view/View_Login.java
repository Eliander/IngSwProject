package view;

import control.Main;
import control.Listener_LoginButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class View_Login extends JFrame {

    private Box central_box;
    private JButton button_login;
    private JLabel label_username;
    private JLabel label_password;
    private JLabel label_titolo;
    private JPanel north_panel;
    private JPanel central_panel;
    private JPanel btn_panel;
    private JPanel username_panel;
    private JPanel password_panel;
    private JPanel caps_lock_panel;
    private JPanel login_error_panel;
    private JPasswordField passwordField_password;
    private JTextField textField_username;
    private JLabel label_capsLock;
    private JLabel label_loginError;

    private final static Logger log = LogManager.getLogger(View_Login.class);

    public View_Login() {
        initComponents();
    }

    private void initComponents() {
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(Main.getWindowsSize());
        setResizable(false);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        north_panel = new JPanel();
        north_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        label_titolo = new JLabel();
        label_titolo.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        label_titolo.setText("MAGAZZINO");
        label_titolo.setToolTipText("");
        north_panel.add(label_titolo);
        contentPane.add(north_panel, BorderLayout.NORTH);

        central_panel = new JPanel();
        central_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        central_box = Box.createVerticalBox();

        username_panel = new JPanel();
        username_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        label_username = new JLabel();
        label_username.setText("USERNAME");
        username_panel.add(label_username);
        textField_username = new JTextField(10);
        textField_username.setText("");
        username_panel.add(textField_username);
        central_box.add(username_panel);
        
        password_panel = new JPanel();
        password_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        label_password = new JLabel();
        label_password.setText("PASSWORD");
        password_panel.add(label_password);
        passwordField_password = new JPasswordField(10);
        passwordField_password.setText("");
        password_panel.add(passwordField_password);
        central_box.add(password_panel);
        
        caps_lock_panel = new JPanel();
        caps_lock_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        label_capsLock = new JLabel("Attenzione: Bloc Maiusc attivato");
        label_capsLock.setVisible(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
        caps_lock_panel.add(label_capsLock);
        central_box.add(caps_lock_panel);
        
        login_error_panel = new JPanel();
        login_error_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        label_loginError = new JLabel();
        label_loginError.setText("Username o password non corretti");
        label_loginError.setForeground(Color.red);
        label_loginError.setVisible(false);
        login_error_panel.add(label_loginError);
        central_box.add(login_error_panel);
        
        btn_panel = new JPanel();
        btn_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        button_login = new JButton();
        button_login.setText("Login");
        button_login.addActionListener(new Listener_LoginButton(this));
        btn_panel.add(button_login);
        central_box.add(btn_panel);
        
        central_panel.add(central_box);
        contentPane.add(central_panel, BorderLayout.CENTER);

        this.pack();
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

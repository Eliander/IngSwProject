package control;

import dao.DAOSettings;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_Login;

public class Main {

    private static Logger log = LogManager.getLogger(Main.class);
    private Properties config = new Properties();
    private static DAOSettings DAO = new DAOSettings();
    private static Dimension windows_size = Toolkit.getDefaultToolkit().getScreenSize();
    
    //variabili per il Popup dell'applicazione
    private static JLabel label = new JLabel();
    private static PopupFactory factory = PopupFactory.getSharedInstance();
    private static Popup popup = factory.getPopup(null, label, 0, 0);
    private static Timer timer = new Timer(5000, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            popup.hide();
        }
    });
    
    public static void main(String[] args) {
        
        int height = windows_size.getSize().height - 100;
        int width = windows_size.getSize().width - 100;
        windows_size.setSize(new Dimension(width, height));
        
        if(DAO.getStatisticaDAO().popolate()){
            log.info("Statistiche aggiornate");
        }
        else{
            log.error("ERRORE: Impossibile aggiornare le statistiche!");
        }
        View_Login login = new View_Login();
        login.setVisible(true);
    }

    public static DAOSettings getDAO() {
        return DAO;
    }
    
    public static Dimension getWindowsSize(){
        return windows_size;
    }

    public static void showPopup(JFrame frame, String content){
        //nascondo il popup precedente
        popup.hide();
        //setto la label da visualizzare
        label.setText(content);
        label.setForeground(Color.red);
        //assegno un nuovo popup
        popup = factory.getPopup(frame, label, frame.getWidth()/2, frame.getHeight()/2);
        //visualizzo il nuovo popup
        popup.show();
        //fermo il timer se in esecuzione, lo resetto e lo faccio ripartire
        timer.stop();
        timer.restart();
        timer.start();
    }
}

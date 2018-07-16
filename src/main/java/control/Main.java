package control;

import dao.DAOSettings;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_Login;

public class Main {

    private static Logger log = LogManager.getLogger(Main.class);
    private Properties config = new Properties();
    private static DAOSettings DAO = new DAOSettings();
    private static Dimension windows_size = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        
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

}

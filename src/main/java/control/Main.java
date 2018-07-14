package control;

import dao.DAOSettings;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import model.Articolo;
import model.ArticoloMagazzino;
import model.ArticoloOrdinato;
import model.Negozio;
import model.Ordine;
import model.Posizione;
import model.Spedizioniere;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View_Login;

/**
 *
 * @author Eliander
 */
public class Main {

    private static Logger log = LogManager.getLogger(Main.class);
    private Properties config = new Properties();
    private static DAOSettings DAO = new DAOSettings();

    public static void main(String[] args) {
        
        /*View_Login login = new View_Login();
        login.setVisible(true);*/
        Negozio negozio = DAO.getNegozioDAO().getNegozioByCodiceFiscale("NGZ001");
        ArrayList<Ordine> ordiniByNegozio = DAO.getOrdineDAO().getOrdiniByNegozio(negozio);
        System.out.println("end");
    }

    public static DAOSettings getDAO() {
        return DAO;
    }

}

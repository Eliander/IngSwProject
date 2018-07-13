package control;

import dao.DAOSettings;
import java.util.ArrayList;
import java.util.Properties;
import model.Articolo;
import model.ArticoloMagazzino;
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
        /*
        View_Login login = new View_Login();
        login.setVisible(true);
         */
        ArrayList<ArticoloMagazzino> articoliMagazzino = DAO.getArticoloMagazzinoDAO().getArticoliMagazzinoByNome("Maglia gialla ADIDAS");
        ArticoloMagazzino art = DAO.getArticoloMagazzinoDAO().getArticoliMagazzinoByCodice("ADIDASMGL002");
        System.out.println("ok");
    }

    public static DAOSettings getDAO() {
        return DAO;
    }

}

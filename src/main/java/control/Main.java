package control;

import dao.DAOSettings;
import java.util.ArrayList;
import java.util.Properties;
import model.ArticoloMagazzino;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class Main {

    private static Logger log = LogManager.getLogger(Main.class);
    private Properties config = new Properties();
    private static DAOSettings DAO = new DAOSettings();

    public static void main(String[] args) {
        ArticoloMagazzino articoliMagazzinoByCodice = DAO.getArticoloMagazzinoDAO().getArticoliMagazzinoByCodice(1);
        System.out.println(articoliMagazzinoByCodice.getNome());
        ArrayList<ArticoloMagazzino> articoliMagazzinoByCodiceIngresso = DAO.getArticoloMagazzinoDAO().getArticoliMagazzinoByCodiceIngresso(1);
        System.out.println("ok");
    }

    public static DAOSettings getDAO() {
        return DAO;
    }

}

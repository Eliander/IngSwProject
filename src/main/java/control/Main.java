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
        ArrayList<ArticoloOrdinato> articoli = new ArrayList();
        Articolo art = new Articolo("Pantaloni di prova", "prova", "Calcio", "Abbigliamento", null, 15.5);
        Articolo art2 = new Articolo("Maglia di prova", "prova", "Basket", "Abbigliamento", null, 15.5);
        articoli.add(new ArticoloOrdinato(art, 15));
        articoli.add(new ArticoloOrdinato(art2, 30));
        Ordine ordine = new Ordine(new Date(), articoli, new Negozio("NGZ001", "Masport", null, null));
        DAO.getOrdineDAO().addOrdine(ordine);
        System.out.println("ok");
    }

    public static DAOSettings getDAO() {
        return DAO;
    }

}

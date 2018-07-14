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
        /*ArrayList<ArticoloOrdinato> articoli = new ArrayList();
        Articolo art = new Articolo("Pantaloni di prova", "prova", "Calcio", "Abbigliamento", null, 15.5);
        Articolo art2 = new Articolo("Maglia di prova", "prova", "Basket", "Abbigliamento", null, 15.5);
        DAO.getArticoloDAO().addArticolo(art2);
        DAO.getArticoloDAO().addArticolo(art);
        ArticoloMagazzino a = new ArticoloMagazzino(art2, 0, new Date(), new Posizione(5, 6), 1, 0);
        
        ArticoloMagazzino articolo = DAO.getArticoloMagazzinoDAO().addArticoloMagazzino(a);
        DAO.getArticoloMagazzinoDAO().removeArticoloMagazzino(articolo);
        System.out.println("ok");*/
        
        ArticoloMagazzino art = DAO.getArticoloMagazzinoDAO().getArticoloMagazzinoByCodice(1);
        System.out.println("OLD position: " + art.getPosizione().getScaffale() + ", " + art.getPosizione().getRipiano());
        art = DAO.getArticoloMagazzinoDAO().moveArticoloMagazzino(art, new Posizione(1,9));
        System.out.println("NEW position: " + art.getPosizione().getScaffale() + ", " + art.getPosizione().getRipiano());
        System.out.println(" end ");
    }

    public static DAOSettings getDAO() {
        return DAO;
    }

}

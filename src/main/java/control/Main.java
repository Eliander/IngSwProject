package control;

import dao.DAOSettings;
import java.awt.Dimension;
import java.awt.Toolkit;
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
    private static Dimension windows_size = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        
        if(DAO.getStatisticaDAO().popolate()){
            System.out.println("Statistiche aggiornate");
        }
        else{
            System.out.println("ERRORE: Impossibile aggiornare le statistiche!");
        }
        View_Login login = new View_Login();
        login.setVisible(true);
        /*Negozio negozio = DAO.getNegozioDAO().getNegozioByCodiceFiscale("NGZ001");
        ArrayList<Ordine> ordiniByNegozio = DAO.getOrdineDAO().getOrdiniByNegozio(negozio);
        System.out.println("end");*/
        /*String[] materiali = DAO.getMaterialiDAO().getMateriali();
        String[] sport = DAO.getSportDAO().getSport();
        String[] categorie = DAO.getCategoriaDAO().getCategorie();
        for (String s : materiali){
            System.out.println(s);
        }
        for (String s : sport){
            System.out.println(s);
        }
        for (String s : categorie){
            System.out.println(s);
        }*/
    }

    public static DAOSettings getDAO() {
        return DAO;
    }
    
    public static Dimension getWindowsSize(){
        return windows_size;
    }

}

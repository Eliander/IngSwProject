package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;
import model.ArticoloMagazzino;
import model.Ingresso;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class IngressoDAO {
    
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(IngressoDAO.class);

    private final String SELECTBYID = "SELECT * FROM INGRESSO WHERE ID = ?";
    private final String SELECTLASTADDED = "SELECT * FROM INGRESSO WHERE bolla =(SELECT MAX(bolla) FROM INGRESSO)";
    private final String INSERT = "INSERT INTO INGRESSO(dataIngresso) VALUES (?)";
    
    public Ingresso getIngressoById(int id) {
        Ingresso ingresso = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYID);
            pst.setInt(1, id);
            ResultSet resultset = pst.executeQuery();
            ingresso = mapRowToIngresso(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return ingresso;
    }
        
    public boolean addIngresso(Ingresso ingresso) {
        boolean esito = true;
        try {
            //inserisco in db
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(INSERT);
            pst.setDate(1, new java.sql.Date(ingresso.getData().getTime()));
            pst.executeUpdate();
            //prendo l'oggetto appena creato con il codice
            pst = con.prepareStatement(SELECTLASTADDED);
            ResultSet resultset = pst.executeQuery();
            //estraggo il codice dell'ingresso
            resultset.next();
            int codice = resultset.getInt("bolla");
            //aggiungo gli articoli sul db
            for (ArticoloMagazzino articolo : ingresso.getArticoli()){
                Main.getDAO().getArticoloMagazzinoDAO().addArticoloMagazzino(articolo, codice);
            }
            con.close();
        } catch (Exception ex) {
            log.error(ex);
            esito = false;
        }
        return esito;
    }
    
    private Ingresso mapRowToIngresso(ResultSet resultset) {
        Ingresso ingresso = null;
        try {
            if (resultset.next()) {
                ingresso = new Ingresso(resultset.getInt("bolla"), new Date(resultset.getDate("dataIngresso").getTime()), null);
                //setto gli articoli entrati
                ingresso.setArticoli(Main.getDAO().getArticoloMagazzinoDAO().getArticoliMagazzinoByCodiceIngresso(resultset.getInt("bolla")));
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return ingresso;
    }
}

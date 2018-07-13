package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Articolo;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class ArticoloDAO {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ArticoloDAO.class);

    private final String SELECT = "SELECT * FROM ARTICOLO WHERE NOME = ?";

    public Articolo getArticolo(String nome) {
        Articolo articolo = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            pst.setString(1, nome);
            ResultSet resultset = pst.executeQuery();
            articolo = mapRowToArticolo(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articolo;
    }

    private Articolo mapRowToArticolo(ResultSet resultset) {
        Articolo articolo = null;
        try {
            if (resultset.next()) {
                //istanzio l'oggetto articolo
                articolo = new Articolo(resultset.getString("nome"), resultset.getString("descrizione"), resultset.getString("sport"), 
                        resultset.getString("categoria"), new ArrayList<String>(), resultset.getDouble("prezzo"));
                //cquery a db per materiali
                ArrayList<String> materialiFromArticolo = Main.getDAO().getMaterialiPerArticoloDAO().getMaterialiFromArticolo(articolo.getNome());
                articolo.setMateriali(materialiFromArticolo);
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return articolo;
    }
    
}

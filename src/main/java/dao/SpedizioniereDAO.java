package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Indirizzo;
import model.Spedizioniere;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class SpedizioniereDAO {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(SpedizioniereDAO.class);

    private final String SELECT = "SELECT * FROM SPEDIZIONIERE";
    private final String SELECTBYNAME = "SELECT * FROM SPEDIZIONIERE WHERE nome = ?";

    public Spedizioniere getSpedizioniereByNome(String nome) {
        Spedizioniere spedizioniere = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYNAME);
            pst.setString(1, nome);
            ResultSet resultset = pst.executeQuery();
            spedizioniere = mapRowToSpedizioniere(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return spedizioniere;
    }
    
    public ArrayList<Spedizioniere> getSpedizionieri() {
        ArrayList<Spedizioniere> spedizionieri = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            ResultSet resultset = pst.executeQuery();
            spedizionieri = mapRowToArrayListSpedizionieri(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return spedizionieri;
    }

    private Spedizioniere mapRowToSpedizioniere(ResultSet resultset) {
        Spedizioniere spedizioniere = null;
        try {
            if (resultset.next()) {
                spedizioniere = new Spedizioniere(resultset.getString("nome"),
                        new Indirizzo(resultset.getString("citta"), resultset.getString("via"), resultset.getInt("numero")),
                        resultset.getString("telefono"));
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return spedizioniere;
    }
    
    private ArrayList<Spedizioniere> mapRowToArrayListSpedizionieri(ResultSet resultset) {
        ArrayList<Spedizioniere> spedizionieri = new ArrayList();
        Spedizioniere spedizioniere = null;
        try {
            while (resultset.next()) {
                spedizioniere = new Spedizioniere(resultset.getString("nome"),
                        new Indirizzo(resultset.getString("citta"), resultset.getString("via"), resultset.getInt("numero")),
                        resultset.getString("telefono"));
                spedizionieri.add(spedizioniere);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return spedizionieri;
    }

}

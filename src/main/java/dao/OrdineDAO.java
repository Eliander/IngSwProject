package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.Articolo;
import model.ArticoloOrdinato;
import model.Negozio;
import model.Ordine;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class OrdineDAO {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(OrdineDAO.class);

    private final String SELECTBYID = "SELECT * FROM ORDINE WHERE ID = ?";
    private final String SELECTBYNEGOZIO = "SELECT * FROM ORDINE WHERE NEGOZIO = ?";

    public Ordine getOrdineByID(int id) {
        Ordine ordine = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYID);
            pst.setInt(1, id);
            ResultSet resultset = pst.executeQuery();
            ordine = mapRowToOrdine(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return ordine;
    }

    public ArrayList<Ordine> getOrdiniByNegozio(String nome) {
        ArrayList<Ordine> ordini = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYNEGOZIO);
            pst.setString(1, nome);
            ResultSet resultset = pst.executeQuery();
            ordini = mapRowToArrayListOrdine(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return ordini;
    }

    private Ordine mapRowToOrdine(ResultSet resultset) {
        Ordine ordine = null;
        try {
            if (resultset.next()) {
                ordine = new Ordine(resultset.getInt("id"), new Date(resultset.getDate("dataOrdine").getTime()), null, null);
                ArrayList<ArticoloOrdinato> articoliOrdinati = Main.getDAO().getArticoloOrdinatoDAO().getArticoliOrdinatiByOrdine(resultset.getInt("id"));
                ordine.setArticoli(articoliOrdinati);
                Negozio negozio = Main.getDAO().getNegozioDAO().getNegozioByCodiceFiscale(resultset.getString("negozio"));
                ordine.setNegozio(negozio);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return ordine;
    }

    private ArrayList<Ordine> mapRowToArrayListOrdine(ResultSet resultset) {
        ArrayList<Ordine> ordini = new ArrayList();
        Ordine ordine = null;
        ArrayList<ArticoloOrdinato> articoliOrdinati = new ArrayList();
        Negozio negozio = null;
        try {
            if (resultset.next()) {
                ordine = new Ordine(resultset.getInt("id"), new Date(resultset.getDate("dataOrdine").getTime()), null, null);
                articoliOrdinati = Main.getDAO().getArticoloOrdinatoDAO().getArticoliOrdinatiByOrdine(resultset.getInt("id"));
                ordine.setArticoli(articoliOrdinati);
                negozio = Main.getDAO().getNegozioDAO().getNegozioByCodiceFiscale(resultset.getString("negozio"));
                ordine.setNegozio(negozio);
                ordini.add(ordine);
                while (resultset.next()) {
                    ordine = new Ordine(resultset.getInt("id"), new Date(resultset.getDate("dataOrdine").getTime()), null, null);
                    articoliOrdinati = Main.getDAO().getArticoloOrdinatoDAO().getArticoliOrdinatiByOrdine(resultset.getInt("id"));
                    ordine.setArticoli(articoliOrdinati);
                    negozio = Main.getDAO().getNegozioDAO().getNegozioByCodiceFiscale(resultset.getString("negozio"));
                    ordine.setNegozio(negozio);
                    ordini.add(ordine);
                }
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return ordini;
    }
}

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
    private final String SELECTCODICE = "SELECT ID FROM ORDINE WHERE DATAORDINE = ? AND NEGOZIO = ?";
    private final String SELECT = "SELECT * FROM ORDINE";
    private final String INSERT = "INSERT INTO ORDINE(dataOrdine, negozio) values (?, ?)";
    private final String CHECKDAILY = "SELECT * FROM ORDINE WHERE DATAORDINE = ? AND NEGOZIO = ?";

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
    
    public ArrayList<Ordine> getOrdini() {
        ArrayList<Ordine> ordini = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            ResultSet resultset = pst.executeQuery();
            ordini = mapRowToArrayListOrdine(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return ordini;
    }

    public ArrayList<Ordine> getOrdiniByNegozio(Negozio negozio) {
        ArrayList<Ordine> ordini = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYNEGOZIO);
            pst.setString(1, negozio.getCodiceFiscale());
            ResultSet resultset = pst.executeQuery();
            ordini = mapRowToArrayListOrdine(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return ordini;
    }

    public boolean addOrdine(Ordine ordine) {
        //devo controllare che non esista un ordine con la stessa data, non 
        //posso farlo da db per un problema su chiavi referenziate
        boolean esito = false;
        try {
            //controllo se e il primo ordine della giornata
            //con le chiavi referenziate era necessario
            //if (checkDailyOrder(ordine)) {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(INSERT);
            pst.setDate(1, new java.sql.Date(ordine.getData().getTime()));
            pst.setString(2, ordine.getNegozio().getCodiceFiscale());
            pst.executeUpdate();
            con.close();
            //mi prendo il codice appena generato
            int codice = getNumeroOrdineByOrdine(ordine);
            //aggiungo gli articoliOrdinati nel db
            for (ArticoloOrdinato articolo : ordine.getArticoli()) {
                Main.getDAO().getArticoloOrdinatoDAO().addArticoloOrdinato(articolo, codice);
            }
            esito = true;
        } catch (Exception ex) {
            log.error(ex);
        }
        return esito;
    }

    public int getNumeroOrdineByOrdine(Ordine ordine) {
        int numeroOrdine = 0;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTCODICE);
            pst.setDate(1, new java.sql.Date(ordine.getData().getTime()));
            pst.setString(2, ordine.getNegozio().getCodiceFiscale());
            ResultSet resultset = pst.executeQuery();
            if (resultset.next()) {
                numeroOrdine = resultset.getInt("id");
            }
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return numeroOrdine;
    }

    /*
    Per gestire le chiavi, ogni negozio puo effettuare solo un ordine al giorno
     */
    private boolean checkDailyOrder(Ordine ordine) {
        boolean esito = true;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(CHECKDAILY);
            pst.setDate(1, new java.sql.Date(ordine.getData().getTime()));
            pst.setString(2, ordine.getNegozio().getCodiceFiscale());
            ResultSet resultset = pst.executeQuery();
            if (resultset.next()) {
                esito = false;
            }
            con.close();
        } catch (Exception ex) {
            log.error(ex);
            esito = false;
        }
        return esito;
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

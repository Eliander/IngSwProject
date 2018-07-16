package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.ArticoloMagazzino;
import model.Ordine;
import model.Uscita;
import org.apache.logging.log4j.LogManager;

public class UscitaDAO {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(SpedizioniereDAO.class);

    private final String SELECT = "SELECT * FROM USCITA";
    private final String SELECTBYID = "SELECT * FROM USCITA WHERE ID = ?";
    private final String SELECTBYORDINE = "SELECT * FROM USCITA WHERE IDORDINE = ?";
    private final String INSERT = "INSERT INTO USCITA(dataUscita, spedizioniere, idOrdine) VALUES (?, ?, ?)";
    private final String SELECTBOLLALASTADDED = "SELECT * FROM INGRESSO WHERE bolla =(SELECT MAX(bolla) FROM USCITA)";
    

    public ArrayList<Uscita> getUscite() {
        ArrayList<Uscita> uscita = new ArrayList();
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            ResultSet resultset = pst.executeQuery();
            uscita = mapRowToArrayListUscita(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return uscita;
    }
    public Uscita getUscitaById(int id) {
        Uscita uscita = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYID);
            pst.setInt(1, id);
            ResultSet resultset = pst.executeQuery();
            uscita = mapRowToUscita(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return uscita;
    }
    
    public Uscita getUscitaByOrdine(Ordine ordine) {
        Uscita uscita = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYORDINE);
            pst.setInt(1, ordine.getCodice());
            ResultSet resultset = pst.executeQuery();
            uscita = mapRowToUscita(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return uscita;
    }
    
    public boolean addUscita(Uscita uscita) {
        boolean esito = true;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(INSERT);
            pst.setDate(1, new java.sql.Date(uscita.getData().getTime()));
            pst.setString(2, uscita.getSpedizioniere().getNome());
            pst.setInt(3, uscita.getOrdine().getCodice());
            pst.executeUpdate();
            //prendo da db il codice uscita appena inserito
            pst = con.prepareStatement(SELECTBOLLALASTADDED);
            ResultSet resultset = pst.executeQuery();
            resultset.next();
            int codiceUscita = resultset.getInt("bolla");
            //aggiorno campo in articoloMagazzino
            for (ArticoloMagazzino articolo : uscita.getArticoli()){
                Main.getDAO().getArticoloMagazzinoDAO().updateUscita(articolo, codiceUscita);
                Main.getDAO().getPosizioneDAO().updateStatus(true, articolo.getPosizione());
            }
            con.close();
        } catch (Exception ex) {
            log.error(ex);
            esito = false;
        }
        return esito;
    }

    private Uscita mapRowToUscita(ResultSet resultset) {
        Uscita uscita = null;
        try {
            if (resultset.next()) {
                uscita = new Uscita(resultset.getInt("bolla"), new Date(resultset.getDate("dataUscita").getTime()), null, null, null);
                //setto gli articoli usciti
                uscita.setArticoli(Main.getDAO().getArticoloMagazzinoDAO().getArticoliMagazzinoByCodiceUscita(resultset.getInt("bolla")));
                //setto l'ordine
                uscita.setOrdine(Main.getDAO().getOrdineDAO().getOrdineByID(resultset.getInt("idOrdine")));
                //setto lo spedizioniere
                uscita.setSpedizioniere(Main.getDAO().getSpedizioniereDAO().getSpedizioniereByNome(resultset.getString("spedizioniere")));
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return uscita;
    }
    
    private ArrayList<Uscita> mapRowToArrayListUscita(ResultSet resultset) {
        ArrayList<Uscita> uscite = new ArrayList();
        Uscita uscita = null;
        try {
            while (resultset.next()) {
                uscita = new Uscita(resultset.getInt("bolla"), new Date(resultset.getDate("dataUscita").getTime()), null, null, null);
                //setto gli articoli usciti
                uscita.setArticoli(Main.getDAO().getArticoloMagazzinoDAO().getArticoliMagazzinoByCodiceUscita(resultset.getInt("bolla")));
                //setto l'ordine
                uscita.setOrdine(Main.getDAO().getOrdineDAO().getOrdineByID(resultset.getInt("idOrdine")));
                //setto lo spedizioniere
                uscita.setSpedizioniere(Main.getDAO().getSpedizioniereDAO().getSpedizioniereByNome(resultset.getString("spedizioniere")));
                uscite.add(uscita);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return uscite;
    }
    
}

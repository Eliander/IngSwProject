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
    private final String INSERT = "INSERT INTO ARTICOLO (nome, descrizione, prezzo, categoria, sport) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE = "DELETE * FROM ARTICOLO WHERE NOME = ?";
    private final String SELECTALL = "SELECT * FROM ARTICOLO";

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
   

    public boolean addArticolo(Articolo articolo) {
        boolean esito = true;
        boolean insertMateriali = true;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(INSERT);
            pst.setString(1, articolo.getNome());
            pst.setString(2, articolo.getDescrizione());
            pst.setDouble(3, articolo.getPrezzo());
            pst.setString(4, articolo.getCategoria());
            pst.setString(5, articolo.getSport());
            pst.executeUpdate();
            //eseguo l'inserimento dei materiali
            insertMateriali = Main.getDAO().getMaterialiPerArticoloDAO().insertMaterialiByArticolo(articolo);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
            esito = false;
        }
        return esito && insertMateriali;
    }

    public ArrayList<Articolo> getAllArticoli() {
        ArrayList<Articolo> articoli = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTALL);
            ResultSet resultset = pst.executeQuery();
            articoli = mapRowToArrayArticoli(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

    public boolean delArticolo(Articolo articolo) {
        boolean esito = false;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(DELETE);
            pst.setString(1, articolo.getNome());
            pst.executeUpdate();
            con.close();
            esito = true;
        } catch (Exception ex) {
            log.error(ex);
        }
        return esito;
    }

    private Articolo mapRowToArticolo(ResultSet resultset) {
        Articolo articolo = null;
        try {
            if (resultset.next()) {
                //istanzio l'oggetto articolo
                articolo = new Articolo(resultset.getString("nome"), resultset.getString("descrizione"), resultset.getString("sport"),
                        resultset.getString("categoria"), new ArrayList<String>(), resultset.getDouble("prezzo"));
                //cquery a db per materiali
                ArrayList<String> materialiFromArticolo = Main.getDAO().getMaterialiPerArticoloDAO().getMaterialiByArticolo(articolo.getNome());
                articolo.setMateriali(materialiFromArticolo);
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return articolo;
    }
    
    private ArrayList<Articolo> mapRowToArrayArticoli(ResultSet resultset) {
        ArrayList<Articolo> articoli = new ArrayList();
        try {
            while (resultset.next()) {
                //istanzio l'oggetto articolo
                Articolo articolo = new Articolo(resultset.getString("nome"), resultset.getString("descrizione"), resultset.getString("sport"),
                        resultset.getString("categoria"), new ArrayList<String>(), resultset.getDouble("prezzo"));
                //cquery a db per materiali
                ArrayList<String> materialiFromArticolo = Main.getDAO().getMaterialiPerArticoloDAO().getMaterialiByArticolo(articolo.getNome());
                articolo.setMateriali(materialiFromArticolo);
                articoli.add(articolo);
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

}

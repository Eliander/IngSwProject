package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.Articolo;
import model.ArticoloMagazzino;
import model.Posizione;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class ArticoloMagazzinoDAO {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ArticoloMagazzinoDAO.class);

    private final String SELECTBYNOME = "SELECT * FROM ARTICOLOMAGAZZINO WHERE NOME = ? ";
    private final String SELECTBYCODICE = "SELECT * FROM ARTICOLOMAGAZZINO WHERE CODICE = ?";
    private final String SELECTBYCODICEINGRESSO = "SELECT * FROM ARTICOLOMAGAZZINO WHERE CODICEINGRESSO = ?";
    private final String SELECTBYCODICEUSCITA = "SELECT * FROM ARTICOLOMAGAZZINO WHERE CODICEUSCITA = ?";

    public ArrayList<ArticoloMagazzino> getArticoliMagazzinoByNome(String nome) {
        ArrayList<ArticoloMagazzino> articoli = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYNOME);
            pst.setString(1, nome);
            ResultSet resultset = pst.executeQuery();
            articoli = mapRowToArrayListArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }
    
    public ArticoloMagazzino getArticoliMagazzinoByCodice(int codice) {
        ArticoloMagazzino articolo = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYCODICE);
            pst.setInt(1, codice);
            ResultSet resultset = pst.executeQuery();
            articolo = mapRowToArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articolo;
    }
    
    public ArrayList<ArticoloMagazzino> getArticoliMagazzinoByCodiceIngresso(int codiceIngresso) {
        ArrayList<ArticoloMagazzino> articoli = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYCODICEINGRESSO);
            pst.setInt(1, codiceIngresso);
            ResultSet resultset = pst.executeQuery();
            articoli = mapRowToArrayListArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

    public ArrayList<ArticoloMagazzino> getArticoliMagazzinoByCodiceUscita(int codiceUscita) {
        ArrayList<ArticoloMagazzino> articoli = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYCODICEUSCITA);
            pst.setInt(1, codiceUscita);
            ResultSet resultset = pst.executeQuery();
            articoli = mapRowToArrayListArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }
    
    private ArrayList<ArticoloMagazzino> mapRowToArrayListArticoloMagazzino(ResultSet resultset){
        ArrayList<ArticoloMagazzino> articoli = new ArrayList();
        ArticoloMagazzino artMagazzino = null;
        try {
            if (resultset.next()) {
                //mi genero l'articolo
                Articolo articolo = Main.getDAO().getArticoloDAO().getArticolo(resultset.getString("nome"));
                articoli.add(getArtMagazzino(articolo, resultset));
                while (resultset.next()) {
                    artMagazzino = getArtMagazzino(articolo, resultset);
                    articoli.add(artMagazzino);
                }
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }
    
    private ArticoloMagazzino mapRowToArticoloMagazzino(ResultSet resultset) {
        ArticoloMagazzino artMagazzino = null;
        try {
            if (resultset.next()) {
                //mi genero l'articolo
                Articolo articolo = Main.getDAO().getArticoloDAO().getArticolo(resultset.getString("nome"));
                artMagazzino = getArtMagazzino(articolo, resultset);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return artMagazzino;
    }

    private ArticoloMagazzino getArtMagazzino(Articolo articolo, ResultSet resultset) {
        ArticoloMagazzino artMagazzino = null;
        try {
            artMagazzino = new ArticoloMagazzino(articolo, resultset.getInt("codice"), new Date((resultset.getDate("dataProduzione")).getTime()), new Posizione(
                    resultset.getInt("scaffale"), resultset.getInt("livello")), resultset.getInt("codiceIngresso"), resultset.getInt("codiceUscita"));
        } catch (Exception ex) {
            log.error(ex);
        }
        return artMagazzino;
    }

}

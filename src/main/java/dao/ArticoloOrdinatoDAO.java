package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Articolo;
import model.ArticoloOrdinato;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class ArticoloOrdinatoDAO {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ArticoloOrdinatoDAO.class);

    private final String SELECTBYARTICOLO = "SELECT * FROM ARTICOLOORDINATO WHERE NOME = ?";
    private final String SELECTBYORDINE = "SELECT * FROM ARTICOLOORDINATO WHERE IDORDINE = ?";

    public Articolo getArticoloOrdinatoByNome(String nome) {
        ArticoloOrdinato articolo = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYARTICOLO);
            pst.setString(1, nome);
            ResultSet resultset = pst.executeQuery();
            articolo = mapRowToArticoloOrdinato(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articolo;
    }

    public ArrayList<ArticoloOrdinato> getArticoliOrdinatiByOrdine(int ordine) {
        ArrayList<ArticoloOrdinato> articoli = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYORDINE);
            pst.setInt(1, ordine);
            ResultSet resultset = pst.executeQuery();
            articoli = mapRowToArrayListArticoloOrdinato(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

    private ArticoloOrdinato mapRowToArticoloOrdinato(ResultSet resultset) {
        ArticoloOrdinato articolo = null;
        try {
            if (resultset.next()) {
                //istanzio l'oggetto articolo
                Articolo art = Main.getDAO().getArticoloDAO().getArticolo(resultset.getString("nome"));
                articolo = new ArticoloOrdinato(art, resultset.getInt("quantita"));
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return articolo;
    }

    private ArrayList<ArticoloOrdinato> mapRowToArrayListArticoloOrdinato(ResultSet resultset) {
        ArrayList<ArticoloOrdinato> articoli = new ArrayList();
        ArticoloOrdinato artMagazzino = null;
        try {
            if (resultset.next()) {
                //mi genero l'articolo
                Articolo articolo = Main.getDAO().getArticoloDAO().getArticolo(resultset.getString("nome"));
                articoli.add(getArticoloOrdinato(articolo, resultset));
                while (resultset.next()) {
                    artMagazzino = getArticoloOrdinato(articolo, resultset);
                    articoli.add(artMagazzino);
                }
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

    private ArticoloOrdinato getArticoloOrdinato(Articolo articolo, ResultSet resultset) {
        ArticoloOrdinato art = null;
        try {
            art = new ArticoloOrdinato(articolo, resultset.getInt("quantita"));
        } catch (Exception e) {
            log.error(e);
        }
        return art;
    }
}

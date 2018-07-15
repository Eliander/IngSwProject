package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Articolo;
import model.Statistica;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class StatisticaDAO {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(StatisticaDAO.class);

    private final String SELECTBYARTICOLO = "SELECT * FROM STATISTICA WHERE IDARTICOLO = ?";
    private final String SELECTBYANNO = "SELECT * FROM STATISTICA WHERE ANNO = ?";
    private final String SELECTBYANNOEMESE = "SELECT * FROM STATISTICA WHERE ANNO = ? AND MESE = ?";
    private final String SELECT = "SELECT * FROM STATISTICA WHERE ANNO = ? AND MESE = ? AND IDARTICOLO = ?";

    public ArrayList<Statistica> getStatisticheByArticolo(Articolo articolo) {
        ArrayList<Statistica> array = new ArrayList();
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYARTICOLO);
            pst.setString(1, articolo.getNome());
            ResultSet resultset = pst.executeQuery();
            array = mapRowToArrayListStatistiche(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return array;
    }

    public ArrayList<Statistica> getStatisticheByAnno(int anno) {
        ArrayList<Statistica> array = new ArrayList();
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYANNO);
            pst.setInt(1, anno);
            ResultSet resultset = pst.executeQuery();
            array = mapRowToArrayListStatistiche(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return array;
    }
    
    public Statistica getStatisticheByAnnoAndMese(Articolo articolo, int anno, int mese) {
        Statistica statistica = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            pst.setInt(1, anno);
            pst.setInt(2, mese);
            pst.setString(3, articolo.getNome());
            ResultSet resultset = pst.executeQuery();
            statistica = mapRowToStatistica(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return statistica;
    }

    private Statistica mapRowToStatistica(ResultSet resultset) {
        Statistica statistica = null;
        try {
            if (resultset.next()) {
                Articolo articolo = Main.getDAO().getArticoloDAO().getArticolo(resultset.getString("idArticolo"));
                statistica = new Statistica(articolo, resultset.getInt("anno"), resultset.getInt("mese"), 
                        resultset.getInt("quantitaIn"), resultset.getInt("quantitaOut"));
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return statistica;
    }
    
    private ArrayList<Statistica> mapRowToArrayListStatistiche(ResultSet resultset) {
        ArrayList<Statistica> statistiche = new ArrayList();
        try {
            while (resultset.next()) {
                Articolo articolo = Main.getDAO().getArticoloDAO().getArticolo(resultset.getString("idArticolo"));
                statistiche.add(new Statistica(articolo, resultset.getInt("anno"), resultset.getInt("mese"), 
                        resultset.getInt("quantitaIn"), resultset.getInt("quantitaOut")));
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return statistiche;
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Posizione;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class PosizioneDAO {
    
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(PosizioneDAO.class);

    private final String CHEKFREE = "SELECT LIBERA FROM POSTI WHERE RIPIANO = ? AND SCAFFALE = ?";
    //assumiamo che il magazzino sia 100 scaffali per 10 ripiani
    private final String SELECTFIRSTFREE = "SELECT * FROM POSTI WHERE LIBERA = true FETCH FIRST 1 ROWS ONLY";
    //query per metodo che restituisce un numero n di posti liberi
    private final String SELECTNFREE = "SELECT * FROM POSTI WHERE LIBERA = true FETCH FIRST ? ROWS ONLY";
    private final String UPDATESTATUS = "UPDATE POSTI SET LIBERA = ? WHERE SCAFFALE = ? AND RIPIANO = ?";
    
    
    public Posizione getPosizioneLibera() {
        Posizione posizione = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTFIRSTFREE);
            ResultSet resultset = pst.executeQuery();
            posizione = mapRowToPosizione(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return posizione;
    }
    
    public ArrayList<Posizione> getArrayPosizioniLibere(int n) {
        ArrayList<Posizione> posizioni = new ArrayList();
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTNFREE);
            pst.setInt(1, n);
            ResultSet resultset = pst.executeQuery();
            while (resultset.next()){
                posizioni.add(mapRowToPosizione(resultset));
            }
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return posizioni;
    }
    
    //to do: volendo si puo fare con un oggetto posizione
    public boolean updateStatus (boolean free, Posizione posizione){
        boolean esito = true;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(UPDATESTATUS);
            pst.setBoolean(1, free);
            pst.setInt(2, posizione.getScaffale());
            pst.setInt(3, posizione.getRipiano());
            pst.executeUpdate();
            con.close();
        } catch (Exception ex) {
            log.error(ex);
            esito = false;
        }
        return esito;
    }
    
    public boolean checkPosizioneLibera(Posizione posizione) {
        boolean isFree = true;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(CHEKFREE);
            pst.setInt(1, posizione.getRipiano());
            pst.setInt(2, posizione.getScaffale());
            ResultSet resultset = pst.executeQuery();
            if(resultset.next()){
                isFree = resultset.getBoolean("libera");
            }
            con.close();
        } catch (Exception ex) {
            isFree = false;
            log.error(ex);
        }
        return isFree;
    }
    
    private Posizione mapRowToPosizione(ResultSet resultset) {
        Posizione posizione = null;
        try {
            if (resultset.next()) {
                posizione = new Posizione(resultset.getInt("scaffale"), resultset.getInt("ripiano"));
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return posizione;
    }
    
    
}

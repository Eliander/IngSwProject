package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Indirizzo;
import model.Negozio;
import model.Responsabile;
import model.Utente;
import org.apache.logging.log4j.LogManager;

public class NegozioDAO {
    
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(NegozioDAO.class);

    private final String SELECTNEGOZIOBYRESPONSABILE = "SELECT * FROM NEGOZIO WHERE RESPONSABILE = ?";
    private final String SELECTNEGOZIOBYCF = "SELECT * FROM NEGOZIO WHERE CODICEFISCALE = ?";
    private final String SELECTCFBYNOME = "SELECT CODICEFISCALE FROM NEGOZIO WHERE NOME = ?";

    public Negozio getNegozioByResponsabile(Utente utente) {
        Negozio negozio = null;
        if(!(utente instanceof Responsabile)){
            return null;
        }
        try {
            Responsabile responsabile = (Responsabile)utente;
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTNEGOZIOBYRESPONSABILE);
            pst.setString(1, responsabile.getUsername());
            ResultSet resultset = pst.executeQuery();
            negozio = mapRowToNegozio(resultset);
            negozio.setResponsabile(responsabile);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return negozio;
    }
    
    public Negozio getNegozioByCodiceFiscale(String codicefiscale) {
        Negozio negozio = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTNEGOZIOBYCF);
            pst.setString(1, codicefiscale);
            ResultSet resultset = pst.executeQuery();
            negozio = mapRowToNegozio(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return negozio;
    }
    
    public String getCodiceFiscaleByNome(String nome) {
        String codiceFiscale = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTCFBYNOME);
            pst.setString(1, nome);
            ResultSet resultset = pst.executeQuery();
            codiceFiscale = resultset.getString("codiceFiscale");
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return codiceFiscale;
    }


    private Negozio mapRowToNegozio(ResultSet resultset) {
        Negozio negozio = null;
        try {
            if (resultset.next()) {
                negozio = new Negozio(resultset.getString("codiceFiscale"), resultset.getString("nome"), 
                        new Indirizzo(resultset.getString("citta"), resultset.getString("via"), resultset.getInt("numero")), null);
                negozio.setResponsabile(new Responsabile(Main.getDAO().getUtenteDAO().getUtenteByUsername(resultset.getString("responsabile"))));
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return negozio;
    }
    
}

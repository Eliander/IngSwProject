package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Magazziniere;
import model.Responsabile;
import model.Segretario;
import model.Utente;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class UtenteDAO {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(UtenteDAO.class);

    private final String LOGIN = "SELECT * FROM UTENTE WHERE USERNAME = ? AND PASSWORD = ?";
    private final String SELECTBYNAME = "SELECT * FROM UTENTE WHERE USERNAME = ?";

    public Utente login(String username, String password) {
        Utente user = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(LOGIN);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet resultset = pst.executeQuery();
            user = mapRowToUtente(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return user;
    }

    public Utente getUtenteByUsername(String username) {
        Utente user = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYNAME);
            pst.setString(1, username);
            ResultSet resultset = pst.executeQuery();
            user = mapRowToUtente(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return user;
    }
    
    private Utente mapRowToUtente(ResultSet resultset) {
        Utente user = null;
        try {
            if (resultset.next()) {
                int ruolo = resultset.getInt("ruolo");
                switch (ruolo) {
                    case 0:
                        user = new Segretario(resultset.getString("nome"),
                                resultset.getString("cognome"),
                                resultset.getString("username"));
                        break;
                    case 1:
                        user = new Responsabile(resultset.getString("nome"),
                                resultset.getString("cognome"),
                                resultset.getString("username"));
                        break;
                    case 2:
                        user = new Magazziniere(resultset.getString("nome"),
                                resultset.getString("cognome"),
                                resultset.getString("username"));
                        break;
                }
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return user;
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Utente;

/**
 *
 * @author Eliander
 */
public class UtenteDAO {
    
    private final String LOGIN = "SELECT * FROM UTENTI WHERE USERNAME = ? AND PASSWORD = ?";
    /*
    public Utente login(String username, String password) {
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(LOGIN);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }*/
    
    //to do: private mapRowToUtente
}

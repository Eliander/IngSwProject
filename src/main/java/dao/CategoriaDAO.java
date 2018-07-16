package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;

public class CategoriaDAO {
    
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(CategoriaDAO.class);

    private final String SELECT = "SELECT * FROM CATEGORIA";
    
    
    public String[] getCategorie(){
        String [] categorie = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            ResultSet resultset = pst.executeQuery();
            categorie = mapRowToArrayCategoria(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return categorie;
    }
    
    private String[] mapRowToArrayCategoria(ResultSet resultset) {
        String[] categorie = null;
        try {
            ArrayList<String> array = new ArrayList();
            while (resultset.next()) {
                array.add(resultset.getString("nome"));
            }
            categorie = new String[array.size()];
            array.toArray(categorie);
        } catch (Exception ex) {
            log.error(ex);
        }
        return categorie;
    }
    
    private String mapRowToCategoria(ResultSet resultset) {
        String categoria = null;
        try {
            if (resultset.next()) {
                categoria = resultset.getString("nome");
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return categoria;
    }
    
    
}

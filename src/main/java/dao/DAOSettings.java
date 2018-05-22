package dao;

/**
 *
 * @author Eliander
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DAOSettings {

    public final static String HOST = "localhost";
    public final static String NOMEDB = "DB_IngSw";
    public final static String USERNAME = "root";
    public final static String PASSWORD = "password";
    public final static String CLASSDERBY = "org.apache.derby.jdbc.ClientDriver";
    public final static String CONNDERBY = "jdbc:derby://" + HOST + ":1527/" + NOMEDB + ";user=" + USERNAME + ";password=" + PASSWORD;
    public final static String URLDERBY = "jdbc:derby://" + HOST + ":1527/" + NOMEDB;

    private static BasicDataSource ds;

    /*
    
    TO DO:
    
    per ogni tabella db creare:
        private static final OggettoDAO oggettoDAO = new OggettoDAO();
    e il rispettivo metodo:
        public OggettoDAO getOggettoDAO() {
            return oggettoDAO;
        }
     */
    private static Logger log = LogManager.getLogger(DAOSettings.class);

    public static DAOSettings DAO = new DAOSettings();

    public DAOSettings() {
        ds = new BasicDataSource();
        ds.setDriverClassName(CLASSDERBY);
        ds.setUrl(URLDERBY);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(CLASSDERBY);
            con = DriverManager.getConnection(CONNDERBY);
        } catch (ClassNotFoundException | SQLException ex) {
            log.error(ex.toString());
        }
        return con;
    }
}

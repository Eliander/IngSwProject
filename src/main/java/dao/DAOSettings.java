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
    public final static String NOMEDB = "Bhasa";
    public final static String USERNAME = "root";
    public final static String PASSWORD = "bhasaTEST";
    public final static String CLASSMYSQL = "com.mysql.jdbc.Driver";
    public final static String CLASSPOSTGRES = "org.postgresql.Driver";
    public final static String CLASSDERBY = "org.apache.derby.jdbc.ClientDriver";
    public final static String CLASSMARIADB = "org.mariadb.jdbc.Driver";
    public final static String CONNMYSQL = "jdbc:mysql://" + HOST + "/" + NOMEDB + "?user=" + USERNAME + "&password=" + PASSWORD;
    public final static String CONNPOSTGRES = "jdbc:postgresql://" + HOST + "/" + NOMEDB + "?user=" + USERNAME + "&password=" + PASSWORD;
    public final static String CONNDERBY = "jdbc:derby://" + HOST + ":1527/" + NOMEDB + ";user=" + USERNAME + ";password=" + PASSWORD;
    public final static String URLMYSQL = "jdbc:mysql://" + HOST + "/" + NOMEDB;
    public final static String URLPOSTGRES = "jdbc:postgresql://" + HOST + "/" + NOMEDB;
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
    
    public DAOSettings(){
        ds = new BasicDataSource();
        ds.setDriverClassName(CLASSDERBY);
        ds.setUrl(URLDERBY);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        log.info("Inizialize DB settings");
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            log.info("Try DB connection");
            Class.forName(CLASSDERBY);
            con = DriverManager.getConnection(CONNDERBY);
            log.info("DB connection: ok");
        } catch (ClassNotFoundException | SQLException ex) {
            log.error(ex.toString());
        }
        return con;
    }
}
package dao;

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

    private static final UtenteDAO utenteDAO = new UtenteDAO();
    private static final ArticoloDAO articoloDAO = new ArticoloDAO();
    private static final MaterialiPerArticoloDAO materialiPerArticoloDAO = new MaterialiPerArticoloDAO();
    private static final ArticoloMagazzinoDAO articoloMagazzinoDAO = new ArticoloMagazzinoDAO();
    private static final ArticoloOrdinatoDAO articoloOrdinatoDAO = new ArticoloOrdinatoDAO();
    private static final NegozioDAO negozioDAO = new NegozioDAO();
    private static final OrdineDAO ordineDAO = new OrdineDAO();
    private static final IngressoDAO ingressoDAO = new IngressoDAO();
    private static final PosizioneDAO posizioneDAO = new PosizioneDAO();
    private static final StatisticaDAO statisticaDAO = new StatisticaDAO();
    private static final UscitaDAO uscitaDAO = new UscitaDAO();
    private static final SpedizioniereDAO spedizioniereDAO = new SpedizioniereDAO();
    private static final SportDAO sportDAO = new SportDAO();
    private static final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private static final MaterialiDAO materialiDAO = new MaterialiDAO();

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

    public UtenteDAO getUtenteDAO() {
        return utenteDAO;
    }
    
    public ArticoloDAO getArticoloDAO(){
        return articoloDAO;
    }
    
    public MaterialiPerArticoloDAO getMaterialiPerArticoloDAO(){
        return materialiPerArticoloDAO;
    }
    
    public ArticoloMagazzinoDAO getArticoloMagazzinoDAO(){
        return articoloMagazzinoDAO;
    }

    public ArticoloOrdinatoDAO getArticoloOrdinatoDAO() {
        return articoloOrdinatoDAO;
    }

    public NegozioDAO getNegozioDAO() {
        return negozioDAO;
    }

    public OrdineDAO getOrdineDAO() {
        return ordineDAO;
    }  

    public IngressoDAO getIngressoeDAO() {
        return ingressoDAO;
    }
    
    public PosizioneDAO getPosizioneDAO() {
        return posizioneDAO;
    }

    public StatisticaDAO getStatisticaDAO() {
        return statisticaDAO;
    }

    public UscitaDAO getUscitaDAO() {
        return uscitaDAO;
    }

    public SpedizioniereDAO getSpedizioniereDAO() {
        return spedizioniereDAO;
    }

    public SportDAO getSportDAO() {
        return sportDAO;
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public MaterialiDAO getMaterialiDAO() {
        return materialiDAO;
    }
}

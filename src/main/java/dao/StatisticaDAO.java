package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;
import model.Articolo;
import model.ArticoloMagazzino;
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
    private final String INSERT = "INSERT INTO STATISTICA(idArticolo, anno, mese, quantitaIn, quantitaOut) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE = "DELETE FROM STATISTICA";
    private final String GETDATA = "SELECT ARTICOLOMAGAZZINO.NOME, INGRESSO.DATAINGRESSO, USCITA.DATAUSCITA "
            + "FROM ARTICOLOMAGAZZINO LEFT JOIN INGRESSO ON CODICEINGRESSO = INGRESSO.BOLLA LEFT JOIN USCITA ON CODICEUSCITA = USCITA.BOLLA";

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

    public boolean popolate() {
        //elimino tutta la tabella
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(DELETE);
            pst.executeUpdate();
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        try {
            //prendo tutte le statistiche per gli oggetti entrati
            HashMap<String, ArrayList<Statistica>> popolateStatisticaIngresso = popolateStatisticaIngresso();
            //prendo tutte le statistiche per gli oggetti usciti
            HashMap<String, ArrayList<Statistica>> mappa = popolateStatisticaUscita(popolateStatisticaIngresso);
            //inserisco le statistiche
            for (String s : mappa.keySet()) {
                ArrayList<Statistica> statistiche = mappa.get(s);
                for (Statistica stat : statistiche) {
                    addStatistica(stat);
                }
            }
        }catch (Exception ex){
            log.error(ex);
            return false;
        }
        return true;
    }

    public HashMap<String, ArrayList<Statistica>> popolateStatisticaIngresso() {
        HashMap<String, ArrayList<Statistica>> mappa = new HashMap();
        try {
            Connection con = DAOSettings.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            //PreparedStatement pst = con.prepareStatement(GETDATA);
            //ResultSet resultset = pst.executeQuery();
            ResultSet resultset = stmt.executeQuery(GETDATA);
            //scorro e riempio la mappa inserendo nome oggetto, lista statistiche
            while (resultset.next()) {
                mappa.putIfAbsent(resultset.getString("nome"), new ArrayList());
            }
            //torno all'inizio
            resultset.first();
            //stavolta parte dalla prima riga, quindi prima il do
            do {
                //prendo l'array corrsipondente all'articolo
                ArrayList<Statistica> array = mappa.get(resultset.getString("nome"));
                //prendo la data ingresso
                Date date = new java.util.Date(resultset.getDate("dataIngresso").getTime());
                //devo passsare dal calendar
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                //cerco se e gia presente una statistica con la data uguale e mi salvo la posizione
                int position = -1;
                for (int i = 0; i < array.size(); i++) {
                    if (array.get(i).getAnno() == year && array.get(i).getMese() == month) {
                        position = i;
                        break;
                    }
                }
                //se ho trovato una data uguale alla mia
                if (position != -1) {
                    Statistica stat = array.get(position);
                    //predno la vecchia quantita
                    int qta = stat.getQuantitaIn();
                    qta++;
                    //la setto di nuovo e incremento
                    stat.setQuantitaIn(qta);
                    //aggiorno il valore nell'array
                    array.set(position, stat);
                } else { //non era ancora stata aggiunta questa data
                    //creo la statistica con mese e anno e articolo e 1 come quantita in (questo giro)
                    Statistica stat = new Statistica(Main.getDAO().getArticoloDAO().getArticolo(resultset.getString("nome")), year, month, 1, 0);
                    array.add(stat);
                }
                //aggiorno l'array nella mappa
                mappa.put(resultset.getString("nome"), array);
            } while (resultset.next());
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return mappa;
    }

    public HashMap<String, ArrayList<Statistica>> popolateStatisticaUscita(HashMap<String, ArrayList<Statistica>> mappa) {
        try {
            Connection con = DAOSettings.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            //PreparedStatement pst = con.prepareStatement(GETDATA);
            //ResultSet resultset = pst.executeQuery();
            ResultSet resultset = stmt.executeQuery(GETDATA);
            //avanzo
            resultset.next();
            //stavolta parte dalla prima riga, quindi prima il do
            do {
                //prendo l'array corrsipondente all'articolo
                ArrayList<Statistica> array = mappa.get(resultset.getString("nome"));
                //prendo la data ingresso
                java.sql.Date sqlDate = resultset.getDate("dataUscita");
                //se la data non e settata, non e uscito
                if (sqlDate != null) {
                    Date date = new java.util.Date(resultset.getDate("dataUscita").getTime());
                    //devo passsare dal calendar
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    //cerco se e gia presente una statistica con la data uguale e mi salvo la posizione
                    int position = -1;
                    for (int i = 0; i < array.size(); i++) {
                        if (array.get(i).getAnno() == year && array.get(i).getMese() == month) {
                            position = i;
                            break;
                        }
                    }
                    //se ho trovato una data uguale alla mia
                    if (position != -1) {
                        Statistica stat = array.get(position);
                        //predno la vecchia quantita
                        int qta = stat.getQuantitaOut();
                        qta++;
                        //la setto di nuovo e incremento
                        stat.setQuantitaOut(qta);
                        //aggiorno il valore nell'array
                        array.set(position, stat);
                    } else { //non era ancora stata aggiunta questa data
                        //creo la statistica con mese e anno e articolo e 1 come quantita in (questo giro)
                        Statistica stat = new Statistica(Main.getDAO().getArticoloDAO().getArticolo(resultset.getString("nome")), year, month, 1, 0);
                        array.add(stat);
                    }
                    //aggiorno l'array nella mappa
                    mappa.put(resultset.getString("nome"), array);
                }
            } while (resultset.next());
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return mappa;
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

    public boolean addStatistica(Statistica statistica) {
        boolean esito = true;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(INSERT);
            pst.setString(1, statistica.getArticolo().getNome());
            pst.setInt(2, statistica.getAnno());
            pst.setInt(3, statistica.getMese());
            pst.setInt(4, statistica.getQuantitaIn());
            pst.setInt(5, statistica.getQuantitaOut());
            pst.executeUpdate();
            con.close();
        } catch (Exception ex) {
            log.error(ex);
            esito = false;
        }
        return esito;
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

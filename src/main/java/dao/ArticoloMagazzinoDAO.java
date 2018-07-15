package dao;

import control.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.Articolo;
import model.ArticoloMagazzino;
import model.Posizione;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Eliander
 */
public class ArticoloMagazzinoDAO {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(ArticoloMagazzinoDAO.class);

    private final String SELECT = "SELECT * FROM ARTICOLOMAGAZZINO WHERE CODICEUSCITA = 0";
    private final String SELECTBYNOME = "SELECT * FROM ARTICOLOMAGAZZINO WHERE NOME = ?";
    private final String SELECTBYNOMEINMAGAZZINO = "SELECT * FROM ARTICOLOMAGAZZINO WHERE NOME = ? AND CODICEUSCITA = 0";
    private final String SELECTBYCODICE = "SELECT * FROM ARTICOLOMAGAZZINO WHERE CODICE = ?";
    private final String SELECTBYCODICEINGRESSO = "SELECT * FROM ARTICOLOMAGAZZINO WHERE CODICEINGRESSO = ?";
    private final String SELECTBYCODICEUSCITA = "SELECT * FROM ARTICOLOMAGAZZINO WHERE CODICEUSCITA = ?";
    private final String SELECTLASTADDED = "SELECT * FROM ARTICOLOMAGAZZINO WHERE CODICE =(SELECT MAX(CODICE) FROM ARTICOLOMAGAZZINO)";
    //devo usare codiceuscita = 0 altrimenti prende anche quelli gia usciti
    private final String COUNT = "SELECT COUNT(CODICE) AS COUNT FROM ARTICOLOMAGAZZINO WHERE NOME = ? AND CODICEUSCITA = 0";
    private final String UPDATEUSCITA = "UPDATE ARTICOLOMAGAZZINO SET codiceUscita = ? WHERE CODICE = ?";
    private final String INSERT = "INSERT INTO ARTICOLOMAGAZZINO(nome, dataProduzione, scaffale, ripiano, codiceIngresso, codiceUscita) VALUES(?, ?, ?, ?, ?, ?)";
    private final String ISINMAGAZZINO = "SELECT * FROM ARTICOLOMAGAZZINO WHERE CODICE = ? AND CODICEUSCITA = 0";
    private final String DELETE = "DELETE FROM ARTICOLOMAGAZZINO WHERE CODICE = ?";
    private final String MOVE = "UPDATE ARTICOLOMAGAZZINO SET scaffale = ?, ripiano = ? WHERE codice = ?";

    public ArrayList<ArticoloMagazzino> getAllArticoliMagazzino() {
        ArrayList<ArticoloMagazzino> articoli = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECT);
            ResultSet resultset = pst.executeQuery();
            articoli = mapRowToArrayListArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

    public ArrayList<ArticoloMagazzino> getArticoliMagazzinoByNome(String nome) {
        ArrayList<ArticoloMagazzino> articoli = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYNOME);
            pst.setString(1, nome);
            ResultSet resultset = pst.executeQuery();
            articoli = mapRowToArrayListArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

    public ArrayList<ArticoloMagazzino> getArticoliMagazzinoByNomeInMagazzino(String nome) {
        ArrayList<ArticoloMagazzino> articoli = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYNOMEINMAGAZZINO);
            pst.setString(1, nome);
            ResultSet resultset = pst.executeQuery();
            articoli = mapRowToArrayListArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

    public ArticoloMagazzino getArticoloMagazzinoByCodice(int codice) {
        ArticoloMagazzino articolo = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYCODICE);
            pst.setInt(1, codice);
            ResultSet resultset = pst.executeQuery();
            articolo = mapRowToArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articolo;
    }

    public boolean isInMagazzinoByCodice(int codice) {
        boolean esito = false;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(ISINMAGAZZINO);
            pst.setInt(1, codice);
            ResultSet resultset = pst.executeQuery();
            //se ho trovato qualcosa
            if (resultset.next()) {
                esito = true;
            }
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return esito;
    }

    public ArrayList<ArticoloMagazzino> getArticoliMagazzinoByCodiceIngresso(int codiceIngresso) {
        ArrayList<ArticoloMagazzino> articoli = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYCODICEINGRESSO);
            pst.setInt(1, codiceIngresso);
            ResultSet resultset = pst.executeQuery();
            articoli = mapRowToArrayListArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

    public ArrayList<ArticoloMagazzino> getArticoliMagazzinoByCodiceUscita(int codiceUscita) {
        ArrayList<ArticoloMagazzino> articoli = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(SELECTBYCODICEUSCITA);
            pst.setInt(1, codiceUscita);
            ResultSet resultset = pst.executeQuery();
            articoli = mapRowToArrayListArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

    public ArticoloMagazzino addArticoloMagazzino(ArticoloMagazzino articoloMagazzino, int codiceIngresso) {
        ArticoloMagazzino articolo = null;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(INSERT);
            pst.setString(1, articoloMagazzino.getNome());
            pst.setDate(2, new java.sql.Date(articoloMagazzino.getData().getTime()));
            pst.setInt(3, articoloMagazzino.getPosizione().getScaffale());
            pst.setInt(4, articoloMagazzino.getPosizione().getRipiano());
            pst.setInt(5, codiceIngresso);
            pst.setInt(6, 0);
            pst.executeUpdate();
            //setto la posizione dell'articolo come occupata
            Main.getDAO().getPosizioneDAO().updateStatus(false, articoloMagazzino.getPosizione());
            //eseguita la query, restituisco l'oggetto salvato che ha anche l'ID
            pst = con.prepareStatement(SELECTLASTADDED);
            ResultSet resultset = pst.executeQuery();
            articolo = mapRowToArticoloMagazzino(resultset);
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return articolo;
    }

    public int countArticoloMagazzino(Articolo articolo) {
        int count = 0;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(COUNT);
            pst.setString(1, articolo.getNome());
            ResultSet resultset = pst.executeQuery();
            count = resultset.getInt("COUNT");
            con.close();
        } catch (Exception ex) {
            log.error(ex);
        }
        return count;
    }

    public boolean removeArticoloMagazzino(ArticoloMagazzino articoloMagazzino) {
        boolean esito = true;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(DELETE);
            pst.setInt(1, articoloMagazzino.getCodice());
            pst.executeUpdate();
            con.close();
        } catch (Exception ex) {
            log.error(ex);
            esito = false;
        }
        return esito;
    }

    public boolean updateUscita(ArticoloMagazzino articoloMagazzino, int codiceUscita) {
        boolean esito = true;
        try {
            Connection con = DAOSettings.getConnection();
            PreparedStatement pst = con.prepareStatement(UPDATEUSCITA);
            pst.setInt(1, codiceUscita);
            pst.setInt(2, articoloMagazzino.getCodice());
            pst.executeUpdate();
            con.close();
        } catch (Exception ex) {
            log.error(ex);
            esito = false;
        }
        return esito;
    }

    public boolean moveArticoloMagazzino(ArticoloMagazzino articoloMagazzino, Posizione nuovaPosizione) {
        boolean esito = false;
        try {
            Connection con = DAOSettings.getConnection();
            //controllo se la posizione e libera
            boolean checkPosizioneLibera = Main.getDAO().getPosizioneDAO().checkPosizioneLibera(nuovaPosizione);
            if (checkPosizioneLibera) {
                //controllo se e in magazzino
                boolean isInMagazzino = Main.getDAO().getArticoloMagazzinoDAO().isInMagazzinoByCodice(articoloMagazzino.getCodice());
                if (isInMagazzino) {
                    //salvo la vecchia posizione
                    Posizione vecchiaPosizione = articoloMagazzino.getPosizione();
                    //libera, muovo
                    PreparedStatement pst = con.prepareStatement(MOVE);
                    pst.setInt(1, nuovaPosizione.getScaffale());
                    pst.setInt(2, nuovaPosizione.getRipiano());
                    pst.setInt(3, articoloMagazzino.getCodice());
                    pst.executeUpdate();
                    //setto la vecchia posizione libera
                    Main.getDAO().getPosizioneDAO().updateStatus(true, vecchiaPosizione);
                    //setto la nuova posizione occupata
                    Main.getDAO().getPosizioneDAO().updateStatus(false, nuovaPosizione);
                    con.close();
                    esito = true;
                }
            }
        } catch (Exception ex) {
            log.error(ex);
            esito = false;
        }
        return esito;
    }

    private ArrayList<ArticoloMagazzino> mapRowToArrayListArticoloMagazzino(ResultSet resultset) {
        ArrayList<ArticoloMagazzino> articoli = new ArrayList();
        ArticoloMagazzino artMagazzino = null;
        try {
            Articolo articolo = null;
            while (resultset.next()) {
                //mi genero l'articolo
                articolo = Main.getDAO().getArticoloDAO().getArticolo(resultset.getString("nome"));
                articoli.add(getArtMagazzino(articolo, resultset));
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return articoli;
    }

    private ArticoloMagazzino mapRowToArticoloMagazzino(ResultSet resultset) {
        ArticoloMagazzino artMagazzino = null;
        try {
            if (resultset.next()) {
                //mi genero l'articolo
                Articolo articolo = Main.getDAO().getArticoloDAO().getArticolo(resultset.getString("nome"));
                artMagazzino = getArtMagazzino(articolo, resultset);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return artMagazzino;
    }

    private ArticoloMagazzino getArtMagazzino(Articolo articolo, ResultSet resultset) {
        ArticoloMagazzino artMagazzino = null;
        try {
            artMagazzino = new ArticoloMagazzino(articolo, resultset.getInt("codice"), new Date((resultset.getDate("dataProduzione")).getTime()), new Posizione(
                    resultset.getInt("scaffale"), resultset.getInt("ripiano")));
        } catch (Exception ex) {
            log.error(ex);
        }
        return artMagazzino;
    }

}

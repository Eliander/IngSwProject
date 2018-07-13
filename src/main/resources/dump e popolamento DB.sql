/* CREAZIONE DB*/

create table UTENTE(
    username varchar(20),
    nome varchar(20),
    cognome varchar(20),
    /*in MD5*/
    password varchar(32),
    /*0 segreteria, 1 responsabile, 2 magazziniere*/
    ruolo int,
    PRIMARY KEY (username)
);

create table CATEGORIA(
    nome varchar(100),
    PRIMARY KEY (nome)
);

create table SPORT(
    nome varchar(100),
    PRIMARY KEY (nome)
);

create table MATERIALE(
    nome varchar(100),
    PRIMARY KEY (nome)
);

create table ARTICOLO(
    nome varchar(100),
    descrizione varchar(200),
    prezzo double,
    /* nome categoria*/
    categoria varchar(100),
    /* nome sport*/
    sport varchar(100),
    PRIMARY KEY (nome),
    FOREIGN KEY (categoria) REFERENCES CATEGORIA(nome),
    FOREIGN KEY (sport) REFERENCES SPORT(nome)
);

/* TABELLA N a N ARTICOLO - MATERIALI */
create table MATERIALIPERARTICOLO(
    nomeArticolo varchar(100),
    nomeMateriale varchar(100),
    PRIMARY KEY (nomeArticolo, nomeMateriale),
    FOREIGN KEY (nomeArticolo) REFERENCES ARTICOLO(nome),
    FOREIGN KEY (nomeMateriale) REFERENCES MATERIALE(nome)
);

create table INGRESSO(
    bolla int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dataIngresso date,
    /* to do o mettiamo qui il coidce prodotto*/
    PRIMARY KEY (bolla)
);

create table SPEDIZIONIERE(
    nome varchar(20),
    telefono varchar(9),
    /* indirizzo */
    via varchar(20),
    nuemro int,
    citta varchar(20),
    PRIMARY KEY(nome)
);

create table USCITA(
    bolla int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dataUscita date,
    spedizioniere varchar(20),
    PRIMARY KEY(bolla),
    FOREIGN KEY (spedizioniere) REFERENCES SPEDIZIONIERE(nome)
);

create table ARTICOLOMAGAZZINO(
    nome varchar(100),
    /* UID prodotto, to do decidere quanto lungo */
    codice varchar(15),
    dataProduzione date,
    scaffale int,
    livello int,
    /* to do mettiamo qui il codice ingresso*/
    codiceIngresso int,
    codiceUscita int,
    PRIMARY KEY (codice),
    FOREIGN KEY (nome) REFERENCES ARTICOLO(nome),
    FOREIGN KEY (codiceIngresso) REFERENCES INGRESSO(bolla),
    FOREIGN KEY (codiceUscita) REFERENCES USCITA(bolla)
);

create table NEGOZIO(
    /* Lunghezza cf non persona */
    codiceFiscale varchar(11),
    nome varchar(50),
    /* indirizzo */
    via varchar(20),
    nuemro int,
    citta varchar(20), 
    responsabile varchar(20),
    PRIMARY KEY (codiceFiscale),
    FOREIGN KEY (responsabile) REFERENCES UTENTE(username)
);

create table ORDINE(
    id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dataOrdine date,
    negozio varchar(11),
    idUscita int,
    PRIMARY KEY (id),
    FOREIGN KEY (negozio) REFERENCES NEGOZIO(codiceFiscale),
    FOREIGN KEY (idUscita) REFERENCES USCITA(bolla)
);

create table ARTICOLORDINATO(
    nome varchar(100),
    quantita int,
    PRIMARY KEY (nome),
    FOREIGN KEY (nome) REFERENCES ARTICOLO(nome)
);

create table ARTICOLIPERORDINE(
    idOrdine int,
    nomeArticolo varchar(100),
    quantita int,
    PRIMARY KEY (idOrdine, nomeArticolo),
    FOREIGN KEY (nomeArticolo) REFERENCES ARTICOLO(nome),
    FOREIGN KEY (idOrdine) REFERENCES ORDINE(id)
);

create table STATISTICA(
    idArticolo varchar(100), 
    anno int, 
    mese int, 
    quantitaIn int,
    quantitOut int,
    PRIMARY KEY(idArticolo, anno, mese),
    FOREIGN KEY (idArticolo) REFERENCES ARTICOLO(nome)
);

/* POPOLAMENTO */
/* POPOLAMENTO CATEGORIE */
INSERT INTO CATEGORIA (nome) VALUES ('Scarpe');
INSERT INTO CATEGORIA (nome) VALUES ('Abbigliamento');
INSERT INTO CATEGORIA (nome) VALUES ('Accessori');
INSERT INTO CATEGORIA (nome) VALUES ('Attrezzatura');
/* POPOLAMENTO SPORT */
INSERT INTO SPORT (nome) VALUES ('Calcio');
INSERT INTO SPORT (nome) VALUES ('Pallavolo');
INSERT INTO SPORT (nome) VALUES ('Baseball');
INSERT INTO SPORT (nome) VALUES ('Basket');
INSERT INTO SPORT (nome) VALUES ('Nuoto');
INSERT INTO SPORT (nome) VALUES ('Karate');
INSERT INTO SPORT (nome) VALUES ('Running');
/* POPOLAMENTO MATERIALE */
INSERT INTO MATERIALE (nome) VALUES ('Poliestere');
INSERT INTO MATERIALE (nome) VALUES ('Legno');
INSERT INTO MATERIALE (nome) VALUES ('Gomma');
INSERT INTO MATERIALE (nome) VALUES ('Plastica');
INSERT INTO MATERIALE (nome) VALUES ('Tessuto traspirante');
INSERT INTO MATERIALE (nome) VALUES ('Acciaio');
/* POPOLAMENTO UTENTE */
INSERT INTO UTENTE (username, nome, cognome, password, ruolo) VALUES ('magazziniere1', 'Elia', 'Piacentini', 'pw', 2);
INSERT INTO UTENTE (username, nome, cognome, password, ruolo) VALUES ('responsabile2', 'Elia', 'Piacentini', 'pw', 1);
INSERT INTO UTENTE (username, nome, cognome, password, ruolo) VALUES ('segretario3', 'Elia', 'Piacentini', 'pw', 0);
/* POPOLAMENTO ARTICOLO */
INSERT INTO ARTICOLO (nome, descrizione, prezzo, categoria, sport) VALUES ('Maglia gialla ADIDAS', 'Maglia con le bande nere laterali', 35.50, 'Abbigliamento', 'Running');
INSERT INTO ARTICOLO (nome, descrizione, prezzo, categoria, sport) VALUES ('Maglia rossa NIKE', 'Maglia con le bande bianche laterali', 37.20, 'Abbigliamento', 'Basket');
INSERT INTO ARTICOLO (nome, descrizione, prezzo, categoria, sport) VALUES ('Mazza da baseball', 'Con rinforzo in acciaio', 56.50, 'Attrezzatura', 'Baseball');
/* POPOLAMENTO MATERIALIPERARTICOLO */
INSERT INTO MATERIALIPERARTICOLO (nomeArticolo, nomeMateriale) VALUES ('Maglia gialla ADIDAS', 'Poliestere');
INSERT INTO MATERIALIPERARTICOLO (nomeArticolo, nomeMateriale) VALUES ('Maglia rossa NIKE', 'Tessuto traspirante');
INSERT INTO MATERIALIPERARTICOLO (nomeArticolo, nomeMateriale) VALUES ('Mazza da baseball', 'Legno');
INSERT INTO MATERIALIPERARTICOLO (nomeArticolo, nomeMateriale) VALUES ('Mazza da baseball', 'Acciaio');
INSERT INTO MATERIALIPERARTICOLO (nomeArticolo, nomeMateriale) VALUES ('Mazza da baseball', 'Plastica');
/* POPOLAMENTO INGRESSO */
INSERT INTO INGRESSO (dataIngresso) VALUES ('2018-08-13');
INSERT INTO INGRESSO (dataIngresso) VALUES ('2018-07-13');
/* POPOLAMENTO USCITA */
/* POPOLAMENTO ARTICOLOMAGAZZINO */
INSERT INTO ARTICOLOMAGAZZINO (nome, codice, dataProduzione, scaffale, livello, codiceIngresso, codiceUscita) VALUES ('Maglia gialla ADIDAS', 'ADIDASMGL001', '2018-08-13', 1, 5, 1, null);
INSERT INTO ARTICOLOMAGAZZINO (nome, codice, dataProduzione, scaffale, livello, codiceIngresso, codiceUscita) VALUES ('Maglia gialla ADIDAS', 'ADIDASMGL002', '2018-07-13', 1, 5, 1, null);
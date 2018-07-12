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

/* TABELLA N a N ARTICOLO - MATERIALI */
create table MATERIALIPERARTICOLO(
    nomeArticolo varchar(100),
    nomeMateriale varchar(100),
    PRIMARY KEY (nomeArticolo, nomeMateriale)
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

create table INGRESSO(
    bolla int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dataIngresso timestamp,
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
    dataUscita timestamp,
    spedizioniere varchar(20),
    PRIMARY KEY(bolla),
    FOREIGN KEY (spedizioniere) REFERENCES SPEDIZIONIERE(nome)
);

create table ARTICOLOMAGAZZINO(
    nome varchar(100),
    /* UID prodotto, to do decidere quanto lungo */
    codice varchar(15),
    dataProduzione timestamp,
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
    dataOrdine timestamp,
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
/* POPOLAMENTO UTENTE */
INSERT INTO UTENTE (username, nome, cognome, password, ruolo) VALUES ('MagazziniereA', 'Elia', 'Piacentini', 'pw', 2);
INSERT INTO UTENTE (username, nome, cognome, password, ruolo) VALUES ('ResponsabileA', 'Elia', 'Piacentini', 'pw', 1);
INSERT INTO UTENTE (username, nome, cognome, password, ruolo) VALUES ('SegretarioA', 'Elia', 'Piacentini', 'pw', 0);
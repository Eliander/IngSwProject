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
    numero int,
    citta varchar(20),
    PRIMARY KEY(nome)
);

create table NEGOZIO(
    /* Lunghezza cf non persona */
    codiceFiscale varchar(11),
    nome varchar(50),
    /* indirizzo */
    via varchar(20),
    numero int,
    citta varchar(20), 
    responsabile varchar(20),
    PRIMARY KEY (codiceFiscale),
    FOREIGN KEY (responsabile) REFERENCES UTENTE(username)
);

create table ORDINE(
    id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dataOrdine date,
    negozio varchar(11),
    completato boolean,
    PRIMARY KEY (id),
    FOREIGN KEY (negozio) REFERENCES NEGOZIO(codiceFiscale) 
);

create table USCITA(
    bolla int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dataUscita date,
    spedizioniere varchar(20),
    idOrdine int,
    PRIMARY KEY(bolla),
    FOREIGN KEY (spedizioniere) REFERENCES SPEDIZIONIERE(nome)
    /*, FOREIGN KEY (idOrdine) REFERENCES ORDINE(id)*/
    /* checkDaily */
);

create table ARTICOLOMAGAZZINO(
    nome varchar(100),
    /* UID prodotto, to do decidere quanto lungo */
    codice int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dataProduzione date,
    scaffale int,
    ripiano int,
    codiceIngresso int,
    codiceUscita int,
    PRIMARY KEY (codice),
    FOREIGN KEY (nome) REFERENCES ARTICOLO(nome),
    FOREIGN KEY (codiceIngresso) REFERENCES INGRESSO(bolla)
    /* to do o lasciamo unuscita con id 0 sempre dentro, altrimenti bisogna toglierlo*/
    /*,FOREIGN KEY (codiceUscita) REFERENCES USCITA(bolla)*/
);

create table ARTICOLOORDINATO(
    nome varchar(100),
    quantita int,
    idOrdine int, 
    PRIMARY KEY (nome, idOrdine), 
    FOREIGN KEY (nome) REFERENCES ARTICOLO(nome),
    FOREIGN KEY (idOrdine) REFERENCES ORDINE(id)
    /* RICORDA: se ripristinare devi ripristinare il checkDaily*/
);

create table STATISTICA(
    idArticolo varchar(100), 
    anno int, 
    mese int, 
    quantitaIn int,
    quantitaOut int,
    PRIMARY KEY(idArticolo, anno, mese),
    FOREIGN KEY (idArticolo) REFERENCES ARTICOLO(nome)
);

create table POSTI(
    scaffale int, 
    ripiano int, 
    libera boolean
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
/* POPOLAMENTO NEGOZIO */
INSERT INTO NEGOZIO (codiceFiscale, nome, via, numero, citta, responsabile) VALUES ('NGZ001', 'Masport', 'trota', 23, 'verona', 'responsabile2');
/* POPOLAMENTO SPEDIZIONIERE */
INSERT INTO SPEDIZIONIERE (nome, telefono, via, numero, citta) VALUES ('Bartolini', '045963152', 'del pacco', 15, 'Verona');
INSERT INTO SPEDIZIONIERE (nome, telefono, via, numero, citta) VALUES ('DHL', '045781236', 'della lettera', 92, 'Milano');
/* POPOLAMENTO POSTI */
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (1, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (1, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (1, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (1, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (1, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (1, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (1, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (1, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (1, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (1, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (2, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (2, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (2, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (2, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (2, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (2, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (2, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (2, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (2, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (2, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (3, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (3, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (3, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (3, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (3, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (3, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (3, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (3, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (3, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (3, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (4, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (4, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (4, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (4, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (4, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (4, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (4, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (4, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (4, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (4, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (5, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (5, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (5, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (5, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (5, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (5, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (5, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (5, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (5, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (5, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (6, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (6, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (6, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (6, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (6, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (6, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (6, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (6, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (6, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (6, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (7, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (7, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (7, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (7, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (7, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (7, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (7, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (7, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (7, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (7, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (8, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (8, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (8, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (8, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (8, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (8, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (8, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (8, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (8, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (8, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (9, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (9, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (9, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (9, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (9, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (9, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (9, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (9, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (9, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (9, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (10, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (10, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (10, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (10, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (10, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (10, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (10, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (10, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (10, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (10, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (11, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (11, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (11, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (11, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (11, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (11, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (11, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (11, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (11, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (11, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (12, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (12, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (12, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (12, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (12, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (12, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (12, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (12, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (12, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (12, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (13, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (13, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (13, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (13, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (13, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (13, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (13, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (13, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (13, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (13, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (14, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (14, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (14, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (14, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (14, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (14, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (14, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (14, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (14, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (14, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (15, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (15, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (15, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (15, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (15, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (15, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (15, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (15, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (15, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (15, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (16, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (16, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (16, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (16, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (16, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (16, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (16, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (16, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (16, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (16, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (17, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (17, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (17, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (17, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (17, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (17, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (17, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (17, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (17, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (17, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (18, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (18, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (18, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (18, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (18, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (18, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (18, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (18, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (18, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (18, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (19, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (19, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (19, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (19, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (19, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (19, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (19, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (19, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (19, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (19, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (20, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (20, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (20, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (20, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (20, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (20, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (20, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (20, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (20, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (20, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (21, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (21, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (21, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (21, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (21, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (21, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (21, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (21, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (21, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (21, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (22, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (22, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (22, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (22, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (22, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (22, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (22, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (22, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (22, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (22, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (23, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (23, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (23, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (23, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (23, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (23, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (23, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (23, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (23, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (23, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (24, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (24, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (24, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (24, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (24, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (24, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (24, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (24, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (24, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (24, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (25, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (25, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (25, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (25, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (25, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (25, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (25, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (25, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (25, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (25, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (26, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (26, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (26, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (26, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (26, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (26, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (26, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (26, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (26, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (26, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (27, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (27, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (27, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (27, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (27, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (27, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (27, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (27, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (27, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (27, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (28, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (28, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (28, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (28, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (28, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (28, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (28, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (28, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (28, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (28, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (29, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (29, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (29, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (29, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (29, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (29, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (29, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (29, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (29, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (29, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (30, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (30, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (30, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (30, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (30, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (30, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (30, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (30, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (30, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (30, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (31, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (31, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (31, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (31, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (31, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (31, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (31, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (31, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (31, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (31, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (32, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (32, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (32, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (32, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (32, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (32, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (32, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (32, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (32, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (32, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (33, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (33, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (33, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (33, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (33, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (33, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (33, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (33, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (33, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (33, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (34, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (34, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (34, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (34, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (34, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (34, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (34, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (34, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (34, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (34, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (35, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (35, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (35, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (35, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (35, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (35, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (35, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (35, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (35, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (35, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (36, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (36, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (36, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (36, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (36, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (36, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (36, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (36, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (36, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (36, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (37, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (37, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (37, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (37, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (37, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (37, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (37, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (37, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (37, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (37, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (38, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (38, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (38, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (38, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (38, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (38, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (38, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (38, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (38, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (38, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (39, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (39, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (39, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (39, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (39, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (39, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (39, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (39, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (39, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (39, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (40, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (40, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (40, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (40, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (40, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (40, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (40, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (40, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (40, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (40, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (41, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (41, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (41, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (41, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (41, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (41, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (41, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (41, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (41, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (41, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (42, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (43, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (43, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (43, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (43, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (43, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (43, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (43, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (43, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (43, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (43, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (44, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (44, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (44, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (44, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (44, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (44, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (44, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (44, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (44, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (44, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (45, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (45, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (45, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (45, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (45, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (45, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (45, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (45, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (45, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (45, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (46, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (46, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (46, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (46, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (46, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (46, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (46, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (46, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (46, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (46, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (47, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (47, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (47, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (47, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (47, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (47, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (47, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (47, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (47, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (47, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (48, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (48, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (48, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (48, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (48, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (48, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (48, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (48, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (48, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (48, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (49, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (49, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (49, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (49, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (49, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (49, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (49, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (49, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (49, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (49, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (50, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (50, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (50, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (50, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (50, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (50, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (50, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (50, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (50, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (50, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (51, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (51, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (51, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (51, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (51, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (51, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (51, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (51, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (51, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (51, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (52, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (52, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (52, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (52, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (52, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (52, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (52, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (52, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (52, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (52, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (53, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (53, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (53, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (53, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (53, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (53, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (53, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (53, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (53, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (53, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (54, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (54, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (54, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (54, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (54, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (54, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (54, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (54, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (54, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (54, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (55, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (55, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (55, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (55, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (55, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (55, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (55, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (55, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (55, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (55, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (56, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (56, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (56, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (56, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (56, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (56, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (56, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (56, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (56, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (56, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (57, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (57, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (57, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (57, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (57, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (57, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (57, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (57, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (57, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (57, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (58, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (58, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (58, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (58, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (58, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (58, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (58, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (58, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (58, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (58, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (59, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (59, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (59, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (59, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (59, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (59, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (59, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (59, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (59, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (59, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (60, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (60, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (60, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (60, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (60, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (60, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (60, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (60, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (60, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (60, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (61, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (61, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (61, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (61, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (61, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (61, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (61, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (61, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (61, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (61, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (62, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (62, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (62, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (62, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (62, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (62, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (62, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (62, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (62, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (62, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (63, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (63, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (63, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (63, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (63, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (63, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (63, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (63, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (63, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (63, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (64, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (64, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (64, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (64, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (64, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (64, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (64, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (64, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (64, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (64, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (65, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (65, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (65, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (65, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (65, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (65, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (65, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (65, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (65, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (65, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (66, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (66, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (66, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (66, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (66, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (66, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (66, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (66, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (66, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (66, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (67, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (67, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (67, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (67, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (67, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (67, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (67, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (67, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (67, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (67, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (68, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (68, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (68, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (68, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (68, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (68, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (68, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (68, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (68, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (68, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (69, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (69, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (69, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (69, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (69, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (69, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (69, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (69, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (69, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (69, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (70, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (70, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (70, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (70, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (70, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (70, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (70, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (70, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (70, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (70, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (71, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (71, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (71, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (71, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (71, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (71, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (71, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (71, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (71, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (71, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (72, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (72, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (72, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (72, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (72, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (72, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (72, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (72, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (72, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (72, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (73, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (73, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (73, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (73, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (73, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (73, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (73, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (73, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (73, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (73, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (74, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (74, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (74, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (74, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (74, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (74, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (74, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (74, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (74, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (74, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (75, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (75, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (75, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (75, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (75, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (75, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (75, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (75, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (75, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (75, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (76, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (76, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (76, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (76, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (76, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (76, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (76, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (76, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (76, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (76, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (77, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (77, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (77, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (77, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (77, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (77, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (77, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (77, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (77, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (77, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (78, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (78, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (78, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (78, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (78, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (78, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (78, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (78, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (78, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (78, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (79, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (79, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (79, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (79, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (79, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (79, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (79, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (79, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (79, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (79, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (80, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (80, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (80, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (80, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (80, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (80, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (80, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (80, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (80, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (80, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (81, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (81, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (81, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (81, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (81, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (81, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (81, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (81, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (81, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (81, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (82, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (82, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (82, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (82, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (82, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (82, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (82, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (82, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (82, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (82, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (83, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (83, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (83, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (83, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (83, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (83, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (83, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (83, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (83, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (83, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (84, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (84, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (84, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (84, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (84, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (84, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (84, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (84, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (84, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (84, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (85, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (85, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (85, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (85, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (85, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (85, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (85, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (85, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (85, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (85, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (86, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (86, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (86, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (86, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (86, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (86, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (86, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (86, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (86, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (86, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (87, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (87, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (87, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (87, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (87, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (87, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (87, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (87, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (87, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (87, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (88, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (88, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (88, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (88, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (88, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (88, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (88, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (88, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (88, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (88, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (89, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (89, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (89, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (89, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (89, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (89, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (89, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (89, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (89, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (89, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (90, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (90, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (90, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (90, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (90, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (90, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (90, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (90, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (90, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (90, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (91, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (91, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (91, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (91, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (91, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (91, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (91, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (91, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (91, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (91, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (92, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (92, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (92, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (92, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (92, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (92, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (92, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (92, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (92, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (92, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (93, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (93, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (93, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (93, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (93, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (93, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (93, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (93, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (93, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (93, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (94, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (94, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (94, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (94, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (94, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (94, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (94, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (94, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (94, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (94, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (95, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (95, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (95, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (95, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (95, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (95, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (95, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (95, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (95, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (95, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (96, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (96, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (96, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (96, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (96, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (96, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (96, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (96, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (96, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (96, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (97, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (97, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (97, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (97, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (97, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (97, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (97, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (97, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (97, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (97, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (98, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (98, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (98, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (98, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (98, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (98, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (98, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (98, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (98, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (98, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (99, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (99, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (99, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (99, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (99, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (99, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (99, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (99, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (99, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (99, 10, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (100, 1, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (100, 2, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (100, 3, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (100, 4, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (100, 5, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (100, 6, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (100, 7, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (100, 8, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (100, 9, true);
INSERT INTO POSTI(scaffale, ripiano, libera) VALUES (100, 10, true);
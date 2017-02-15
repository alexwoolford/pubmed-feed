USE pubmed_feed;

CREATE TABLE pubmed_abstracts (
  pmid INT NOT NULL,
  title VARCHAR(1000),
  journalTitle VARCHAR(1000),
  docAbstract TEXT,
  createDate DATETIME,
  updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (pmid)
);

CREATE TABLE doctors(
  doctorName VARCHAR(100),
  updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (doctorName)
);

INSERT INTO doctors (doctorName) VALUES ("Jonathan Finnoff");
INSERT INTO doctors (doctorName) VALUES ("Steven Sampson");
INSERT INTO doctors (doctorName) VALUES ("Thomas Bond");
INSERT INTO doctors (doctorName) VALUES ("Sang-Hoon Lhee");
INSERT INTO doctors (doctorName) VALUES ("Jay Bowen");
INSERT INTO doctors (doctorName) VALUES ("Mitchell Sheinkop");
INSERT INTO doctors (doctorName) VALUES ("Robert Marx");
INSERT INTO doctors (doctorName) VALUES ("Lille Tidwell");
INSERT INTO doctors (doctorName) VALUES ("Martinelli");
INSERT INTO doctors (doctorName) VALUES ("Jose Fabio Lana");
INSERT INTO doctors (doctorName) VALUES ("Brian Cole");
INSERT INTO doctors (doctorName) VALUES ("Joseph Albano");
INSERT INTO doctors (doctorName) VALUES ("Didier Demesmin");
INSERT INTO doctors (doctorName) VALUES ("Karl Nobert");
INSERT INTO doctors (doctorName) VALUES ("Sean Colio");
INSERT INTO doctors (doctorName) VALUES ("Christopher Centeno");
INSERT INTO doctors (doctorName) VALUES ("Georgios Karnatzikos");
INSERT INTO doctors (doctorName) VALUES ("Jay Smith");
INSERT INTO doctors (doctorName) VALUES ("Tariq Awan");
INSERT INTO doctors (doctorName) VALUES ("Wayne McIlwraith");
INSERT INTO doctors (doctorName) VALUES ("Kristin Oliver");
INSERT INTO doctors (doctorName) VALUES ("Kristin Comella");
INSERT INTO doctors (doctorName) VALUES ("Matthew Pingree");
INSERT INTO doctors (doctorName) VALUES ("Stanley Lam");
INSERT INTO doctors (doctorName) VALUES ("Jonathan Kirschner");
INSERT INTO doctors (doctorName) VALUES ("Brian Shiple");
INSERT INTO doctors (doctorName) VALUES ("Philippe Hernigou");
INSERT INTO doctors (doctorName) VALUES ("Joanne Borg-Stein");
INSERT INTO doctors (doctorName) VALUES ("William Murrell");
INSERT INTO doctors (doctorName) VALUES ("Jason Snibbe");
INSERT INTO doctors (doctorName) VALUES ("Don Buford");
INSERT INTO doctors (doctorName) VALUES ("Mary Ambach");
INSERT INTO doctors (doctorName) VALUES ("Alberto Gobbi");
INSERT INTO doctors (doctorName) VALUES ("Amadeus Mason");
INSERT INTO doctors (doctorName) VALUES ("Jason Dragoo");
INSERT INTO doctors (doctorName) VALUES ("Phillipe Hernigou");
INSERT INTO doctors (doctorName) VALUES ("Evan Peck");
INSERT INTO doctors (doctorName) VALUES ("Randy Miller");
INSERT INTO doctors (doctorName) VALUES ("Jean Marc Parisaux");
INSERT INTO doctors (doctorName) VALUES ("Thomas Clanton");
INSERT INTO doctors (doctorName) VALUES ("Azarel Abinader");
INSERT INTO doctors (doctorName) VALUES ("Joseph Greco");
INSERT INTO doctors (doctorName) VALUES ("Kenneth Pettine");
INSERT INTO doctors (doctorName) VALUES ("Annie Zuzelski");
INSERT INTO doctors (doctorName) VALUES ("Andre Panagos");
INSERT INTO doctors (doctorName) VALUES ("Nicola Maffulli");
INSERT INTO doctors (doctorName) VALUES ("Halland Chen");
INSERT INTO doctors (doctorName) VALUES ("Jon Trister");
INSERT INTO doctors (doctorName) VALUES ("Timothy Davis");
INSERT INTO doctors (doctorName) VALUES ("Lisa Fortier");
INSERT INTO doctors (doctorName) VALUES ("Rahul Desai");
INSERT INTO doctors (doctorName) VALUES ("James Watson");
INSERT INTO doctors (doctorName) VALUES ("Peter Verdonk");
INSERT INTO doctors (doctorName) VALUES ("Yuji Okuno");
INSERT INTO doctors (doctorName) VALUES ("Mederic Hall");
INSERT INTO doctors (doctorName) VALUES ("Annu Navani");
INSERT INTO doctors (doctorName) VALUES ("Jamie Textor");
INSERT INTO doctors (doctorName) VALUES ("Jennifer Solomon");
INSERT INTO doctors (doctorName) VALUES ("Sadiq Haque");
INSERT INTO doctors (doctorName) VALUES ("John Schultz");
INSERT INTO doctors (doctorName) VALUES ("Imtiaz Ahmad");
INSERT INTO doctors (doctorName) VALUES ("Hollis Potter");
INSERT INTO doctors (doctorName) VALUES ("Jonathan Halperin");
INSERT INTO doctors (doctorName) VALUES ("Ramon Castellanos");
INSERT INTO doctors (doctorName) VALUES ("Gerard Malanga");
INSERT INTO doctors (doctorName) VALUES ("Jen-Li Pan");
INSERT INTO doctors (doctorName) VALUES ("Victor Flores");
INSERT INTO doctors (doctorName) VALUES ("Eduardo Anitua");
INSERT INTO doctors (doctorName) VALUES ("Kenneth Mautner");
INSERT INTO doctors (doctorName) VALUES ("Erik Brand");
INSERT INTO doctors (doctorName) VALUES ("James Wyss");
INSERT INTO doctors (doctorName) VALUES ("Debra Canapp");
INSERT INTO doctors (doctorName) VALUES ("Robert Monaco");
INSERT INTO doctors (doctorName) VALUES ("Luga Podesta");
INSERT INTO doctors (doctorName) VALUES ("Shane Shapiro");
INSERT INTO doctors (doctorName) VALUES ("Jon Jacobson");
INSERT INTO doctors (doctorName) VALUES ("John Pitts");
INSERT INTO doctors (doctorName) VALUES ("Adam Anz");
INSERT INTO doctors (doctorName) VALUES ("David Karli");
INSERT INTO doctors (doctorName) VALUES ("Beny Charchian");
INSERT INTO doctors (doctorName) VALUES ("Matthew Otten");
INSERT INTO doctors (doctorName) VALUES ("Hyun Bae");
INSERT INTO doctors (doctorName) VALUES ("Sabino Padilla");
INSERT INTO doctors (doctorName) VALUES ("David Soomekh");
INSERT INTO doctors (doctorName) VALUES ("Bob Baravarian");
INSERT INTO doctors (doctorName) VALUES ("Marko Bodor");
INSERT INTO doctors (doctorName) VALUES ("Sherman Canapp");
INSERT INTO doctors (doctorName) VALUES ("John Lyftogt");
INSERT INTO doctors (doctorName) VALUES ("Adam Pourcho");
INSERT INTO doctors (doctorName) VALUES ("Michael Fredericson");
INSERT INTO doctors (doctorName) VALUES ("Maryam Niapour");
INSERT INTO doctors (doctorName) VALUES ("John Cianca");


USE pubmed_feed;

CREATE TABLE pubmed_abstracts (
  pmid INT NOT NULL,
  title VARCHAR(1000),
  journalTitle VARCHAR(1000),
  docAbstract TEXT,
  createDate DATETIME,
  PRIMARY KEY (pmid)
);


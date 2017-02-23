USE pubmed_feed;

CREATE TABLE email_sent (
  pmid INT NOT NULL,
  email VARCHAR(100),
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (pmid)
);


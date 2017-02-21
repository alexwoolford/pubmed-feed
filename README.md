# PubMed feed

This project monitors publications on PubMed for a number of doctors. There are three modules:

1. `migration`: the `feed` application reads a list of doctors are read from a MySQL table and persist abstracts of publications returned by searching for doctors in that list to MySQL.
2. `feed`: 
3. `dev_utils`: 


The list of doctors are read from a MySQL table (see the `migration` module).


## ideas/thoughts

- if PubMed ID's are based on an incrementing integer, it would be really easy to capture/persist all the abstracts, and not just the ones that are returned by the search list of doctors.
- we do not know that the schema(s) of the JSON returned when we retrieve pmid documents is consistent. It would be helpful to know if there are variants so we can parse accordingly.

## to do

1. capture all the authors for each paper
2. FreeMarker template to generate HTML
3. expose functionality as RESTful web service
4. schedule refreshes

# PubMed feed

This project monitors publications on PubMed for a number of doctors. There are three modules:

1. `migration`: the `feed` application reads a list of doctors are read from a MySQL table and persist abstracts of publications returned by searching for doctors in that list to MySQL.
2. `feed`: application that captures recent abstracts from PubMed
3. `dev_utils`: quick & dirty Python code to prototype/test/validate.


The list of doctors are read from a MySQL table (see the `migration` module).


## ideas/thoughts

- if PubMed ID's are based on an incrementing integer, it would be really easy to capture/persist all the abstracts, and not just the ones that are returned by the search list of doctors.
- we could take a sample list of interesting articles, mix those articles with a collection of irrelevant articles returned by PubMed, and create a classification model to predict whether any given article would be relevant.

## to do

1. capture all the authors for each paper
2. FreeMarker template to generate HTML
3. use [swagger.io](http://swagger.io/) to document the RESTful services

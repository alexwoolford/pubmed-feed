# migration

PubMed Feed uses MySQL to store a list of doctors whose work is of interest, and to store the abstracts for articles returned when we search for those doctors.

The MySQL tables can be created by running the [Flyway](https://flywaydb.org/) migration:

    cd migration
    mvn clean compile flyway:migrate

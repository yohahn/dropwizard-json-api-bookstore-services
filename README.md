# dropwizard-json-api-bookstore-services

## Database Migrations

### migrating your schema

    $ java -jar target/dropwizard-json-api-bookstore-services-1.0-SNAPSHOT.jar db migrate bookstore-services.yml

### migration

Migration script is located at src/main/resources/migrations.xml.

For more information on available database refactorings, check the [Liquibase](http://www.liquibase.org/documentation/changes/index.html) documentation.

## Services Applicaiton

### building fat jar

    $ mvn clean package

### running your application

    $ java -jar target/dropwizard-json-api-bookstore-services-1.0-SNAPSHOT.jar server bookstore-services.yml

package name.yohahn.bookstore;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.katharsis.locator.JsonServiceLocator;
import io.katharsis.rs.KatharsisFeature;
import name.yohahn.bookstore.domain.repository.BookRepository;
import name.yohahn.bookstore.jdbi.BookDAO;
import org.skife.jdbi.v2.DBI;

import static io.katharsis.rs.KatharsisProperties.RESOURCE_DEFAULT_DOMAIN;
import static io.katharsis.rs.KatharsisProperties.RESOURCE_SEARCH_PACKAGE;

/**
 * Created by yohahn.kim on 10/28/15.
 */
public class BookstoreServicesApplication extends Application<BookstoreServicesConfiguration> {

    private Injector injector;

    public static void main(String[] args) throws Exception {
        new BookstoreServicesApplication().run(args);
    }

    @Override
    public String getName() {
        return "bookstore-services";
    }

    @Override
    public void initialize(
            Bootstrap<BookstoreServicesConfiguration> bootstrap) {

        bootstrap.addBundle(new MigrationsBundle<BookstoreServicesConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(
                    BookstoreServicesConfiguration configuration) {
                return configuration.database;
            }
        });

        // automatically unwrap SQLEXception or DBIException
        bootstrap.addBundle(new DBIExceptionsBundle());
    }

    @Override
    public void run(
            BookstoreServicesConfiguration configuration,
            Environment environment) throws Exception {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.database, "mysql");

        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(BookRepository.class);
            }

            @Provides
            public BookDAO bookDAO() {
                return jdbi.onDemand(BookDAO.class);
            }
        });

        environment.jersey().property(RESOURCE_DEFAULT_DOMAIN, configuration.katharsis.host);
        environment.jersey().property(RESOURCE_SEARCH_PACKAGE, configuration.katharsis.searchPackage);

        KatharsisFeature katharsisFeature = new KatharsisFeature(
                environment.getObjectMapper(),
                new JsonServiceLocator() {
                    @Override
                    public <T> T getInstance(Class<T> aClass) {
                        return injector.getInstance(aClass);
                    }
                });
        environment.jersey().register(katharsisFeature);
    }
}

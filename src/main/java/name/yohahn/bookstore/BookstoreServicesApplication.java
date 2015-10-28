package name.yohahn.bookstore;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by yohahn.kim on 10/28/15.
 */
public class BookstoreServicesApplication extends Application<BookstoreServicesConfiguration> {

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
    }

    @Override
    public void run(
            BookstoreServicesConfiguration configuration,
            Environment environment) throws Exception {

    }
}

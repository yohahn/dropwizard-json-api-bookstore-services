package name.yohahn.bookstore;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by yohahn.kim on 10/28/15.
 */
public class BookstoreServicesConfiguration extends Configuration {

    @Valid
    @NotNull
    public DataSourceFactory database = new DataSourceFactory();

}

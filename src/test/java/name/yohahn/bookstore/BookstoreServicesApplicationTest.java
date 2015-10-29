package name.yohahn.bookstore;

import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by yohahn.kim on 10/28/15.
 */
public class BookstoreServicesApplicationTest {
    private final Environment environment = Mockito.mock(Environment.class);
    private final JerseyEnvironment jersey = Mockito.mock(JerseyEnvironment.class);
    private final BookstoreServicesApplication application = new BookstoreServicesApplication();
    private final BookstoreServicesConfiguration config = new BookstoreServicesConfiguration();

    @Before
    public void setup() throws Exception {
        Mockito.when(environment.jersey()).thenReturn(jersey);
    }

    @Test
    public void buildsKatharsisFeature() throws Exception {
        // place holder
    }
}

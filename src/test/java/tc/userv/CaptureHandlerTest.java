package tc.userv;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class CaptureHandlerTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = ServerMain.startServer();
        // create the client
        Client c = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        target = c.target(ServerMain.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    @Test
    public void testExample() {
        String responseMsg = target.path("inbox/json").request()
                                 .accept(MediaType.APPLICATION_JSON)
                                 .get(String.class);
        // assertEquals("This is text.", responseMsg);
    }
}

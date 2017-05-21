package tc.userv;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * ServerMain command entry point
 *
 */
public class ServerMain {
    public static final String APP_NAME = "REST Capture";
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/restcap/v1/";
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("tc.userv");
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * ServerMain main entry point.
     * @param args command-line arguments
     * @throws IOException possibly thrown by by transport operations.
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format(
            "%s started with WADL available at %sapplication.wadl\nHit enter to stop:",
            APP_NAME, BASE_URI)
        );
        System.in.read();
        server.shutdownNow();
    }
}


package tc.userv;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("stats")
public class StatHandler {
    private StatStore stats = StatStore.getInstance();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,Integer> getStats() {
        return this.stats.dump();
    }
}

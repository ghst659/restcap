package tc.userv;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("inbox")
public class CaptureHandler {
    public final static String name = "inbox";
    private static final MessageStore sink = MessageStoreFactory.newStore("restcap");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getTags() {
        List<String> result = new LinkedList<>(sink.getTags());
        // System.err.format("getTags(): %s\n", result);
    	return result;
    }

    @GET
    @Path("{tag}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessages(@PathParam("tag") String tag) {
        List<Message> result = sink.getMessages(tag);
        //System.err.format("getMessages(%s): %s\n", tag, result);
        return result;
    }

    @POST
    @Path("stamp")
    @Produces(MediaType.APPLICATION_JSON)
    public GateStamp postStamp(GateStamp s) {
        String msg = s.toString();
        System.err.format("postStamp(%s)\n", msg);
        return s;
    }

    @POST
    @Path("stamp/{tag}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GateStamp postDummy(@PathParam("tag") String tag, GateStamp s) {
        s.setName(tag);
        String msg = s.toString();
        System.err.format("postStamp(%s)\n", msg);
        return s;
    }

    @POST
    @Path("message/{tag}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message postMessage(@PathParam("tag") String tag, Message postData) {
        // System.err.format("postMessage(%s)\n", tag);
    	Message result = new Message();
    	GateStamp stamp = new GateStamp(name);
        postData.addStamp(stamp);
        sink.putMessage(tag, postData);
        result.addStamp(stamp);
        result.setData(String.format("%s: %d", tag, sink.messageCount(tag)));
        // System.err.format("postMessage(%s): %s\n", tag, postData);
        return result;
    }
    
    @PUT
    @Path("message/{tag}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message putMessage(@PathParam("tag") String tag, Message putData) {
    	Message result = new Message();
        GateStamp stamp = new GateStamp(name);
        putData.addStamp(stamp);
        sink.clearTag(tag);
        sink.putMessage(tag, putData);
        result.addStamp(stamp);
        result.setData(String.format("%s: %d", tag, sink.messageCount(tag)));
        // System.err.format("putMessage(%s): %s\n", tag, putData);
        return result;
    }
}

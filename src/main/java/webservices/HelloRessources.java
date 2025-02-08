package webservices;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.awt.*;

public class HelloRessources {


    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello() {
        return Response.status(200).entity("Hello World").build();
    }
}

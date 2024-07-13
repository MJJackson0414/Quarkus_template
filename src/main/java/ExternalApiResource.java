import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/external")
public class ExternalApiResource {

    @Inject
    Logger logger;

    @Inject
    @RestClient
    ExternalApiClient externalApiClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExternalData() {
        logger.info("Attempting to call external API");
        try {
            String data = externalApiClient.getData();
            logger.info("Successfully received data from external API");
            return Response.ok(data).build();
        } catch (Exception e) {
            logger.error("Error calling external API", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error calling external API: " + e.getMessage() + "\"}").build();
        }
    }
}

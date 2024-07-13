import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/posts")
@RegisterRestClient(configKey = "external-api")
public interface ExternalApiClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getData();
}

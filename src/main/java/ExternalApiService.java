

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ExternalApiService {

    @Inject
    @RestClient
    ExternalApiClient externalApiClient;

    public String fetchData() {
        return externalApiClient.getData();
    }

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getExternalData() {
        logger.info("Calling external API");
        String result = externalApiClient.getData();
        logger.info("Received response: " + result);
        return result;
    }
}

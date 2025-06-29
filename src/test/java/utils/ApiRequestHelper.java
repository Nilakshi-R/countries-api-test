package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import countries.SchemaValidationTest;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Util class to generate HTTP request and response to retrieve county data
 */
public class ApiRequestHelper {

    private static final String API_URL = "https://restcountries.com/v3.1/";
    private static final Logger logger = Logger.getLogger(ApiRequestHelper.class.getName());

    /**
     * Return Country data as a Json Node.
     */
    public static JsonNode getJsonFromAPI(String filters) throws Exception {

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(API_URL.concat(filters));
            ClassicHttpResponse response = httpClient.executeOpen(null, request, null);
            String jsonResponse = EntityUtils.toString(response.getEntity());
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(jsonResponse);
        } catch (IOException ioExcp) {
            logger.severe("API Connection error, Failed to load country data " + ioExcp);
            throw new IOException("API Connection error, Failed to load country data", ioExcp);
        } catch (Exception excp) {
            logger.severe("Failed to load country data " + excp);
            throw new Exception("Failed to load country data", excp);
        }
    }
}

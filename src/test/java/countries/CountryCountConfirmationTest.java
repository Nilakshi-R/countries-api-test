package countries;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.ApiRequestHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Validate country count related information.
 */
public class CountryCountConfirmationTest {

    private static final Logger logger = Logger.getLogger(CountryCountConfirmationTest.class.getName());
    private static final Level LOG_LEVEL = Level.INFO;

    @BeforeAll
    public static void configSetup() {
        logger.setLevel(LOG_LEVEL);
        logger.info("Starting Country Count Confirmation Test");
    }

    /**
     * Validate whether total county count is 195.
     */
    @Test
    public void validateTotalCountryCountIs195() {
        try {
            logger.info("Start - Verify Total Country Count is 195");
            JsonNode responseJson = ApiRequestHelper.getJsonFromAPI("independent?status=true");
            assertNotNull(responseJson, "No Countries Found in which Independent Status is True.");
            int countryCount = responseJson.size();
            assertEquals(195, countryCount, "Expected 195 countries in the world, but returned" + countryCount);
        } catch (Exception excp) {
            logger.severe("Country Count Confirmation Test Error " + excp);
        }
    }
}


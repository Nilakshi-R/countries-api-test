package countries.languages;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.ApiRequestHelper;
import utils.CountryFinder;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Validate languages related information.
 */
public class LanguagesTest {

    private static final Logger logger = Logger.getLogger(LanguagesTest.class.getName());
    private static final Level LOG_LEVEL = Level.INFO;

    @BeforeAll
    public static void configSetup() {
        logger.setLevel(LOG_LEVEL);
        logger.info("Starting South African Languages Test");
    }

    /**
     * Verify south african sign language is in the South African language list.
     */
    @Test
    public void verifySASLIsInSouthAfricaLanguages() {
        try {
            logger.info("Start - Verify SASL In South Africa Languages");
            JsonNode responseJson = ApiRequestHelper.getJsonFromAPI("independent?status=true");
            assertNotNull(responseJson, "No Countries Found in which Independent Status is True.");
            JsonNode southAfricaNode = CountryFinder.findCountryByCountryName("South Africa", responseJson);
            assertNotNull(southAfricaNode, "Country South Africa is not found in the response.");

            JsonNode languages = southAfricaNode.get("languages");
            assertNotNull(languages, "Languages node is not found for South Africa.");
            boolean isSASLContains = false;
            for (JsonNode lang : languages) {
                String language = lang.asText().toLowerCase();
                if (language.contains("sign language") || language.contains("sasl") || language.contains("south africa sign language")) {
                    isSASLContains = true;
                    break;
                }
            }
            assertTrue(isSASLContains, "South African Sign Language (SASL) is not listed among the RSA languages.");
        } catch (Exception excp) {
            logger.severe("South African Sign Language Confirmation Test Error " + excp);
        }
    }
}

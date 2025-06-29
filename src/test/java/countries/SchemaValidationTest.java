package countries;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.ApiRequestHelper;

import java.io.File;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Validate schema related information.
 */
public class SchemaValidationTest {

    private static final String SCHEMA_PATH = "src/test/resources/restcountries_schema.json";
    private static final Logger logger = Logger.getLogger(SchemaValidationTest.class.getName());
    private static final Level LOG_LEVEL = Level.INFO;

    @BeforeAll
    public static void configSetup() {
        logger.setLevel(LOG_LEVEL);
        logger.info("Starting Schema Validation Test");
    }

    /**
     * Validate whether the schemas conform to the agreed schema.
     */
    @Test
    public void validateCountryResponseSchema() {
        try {
            logger.info("Start - validate Country Response Schema");
            JsonNode responseJson = ApiRequestHelper.getJsonFromAPI("independent?status=true");
            assertNotNull(responseJson, "No Countries Found in which Independent Status is True.");
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V6);
            JsonSchema schema = factory.getSchema(new File(SCHEMA_PATH).toURI());
            Set<ValidationMessage> validationMessages = schema.validate(responseJson);
            assertTrue(validationMessages.isEmpty(), "Schema validation failed: " + validationMessages);
        } catch (Exception excp) {
            logger.severe("Schema Validation Test Error " + excp);
        }
    }
}

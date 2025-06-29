package utils;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.logging.Logger;

/**
 * Util class for search information for countries.
 */
public class CountryFinder {

    private static final Logger logger = Logger.getLogger(CountryFinder.class.getName());

    /**
     * Find country Json Node by the country name.
     */
    public static JsonNode findCountryByCountryName(String countryName, JsonNode responseJson) throws Exception {

        JsonNode countryNode = null;
        try {
            if (countryName == null || countryName.length() == 0) {
                return countryNode;
            }
            for (JsonNode country : responseJson) {
                if (country != null) {
                    JsonNode nameNode = country.get("name");
                    if (nameNode != null && nameNode.get("common") != null && countryName.equalsIgnoreCase(nameNode.get("common").asText())) {
                        countryNode = country;
                        break;
                    }
                }
            }
        } catch (Exception excp) {
            logger.severe("Error when find country by name " + excp);
            throw new Exception("Error when find country by name", excp);
        }
        return countryNode;
    }
}

package ee.petstore.tests.config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class configurationProperties {

    private static ResourceBundle properties = getBundle("application", getDefaultLocale());

    @BeforeAll
    public static void setup() {
        // API URI configurations
        //RestAssured.port = Integer.valueOf(properties.getString("port"));
        RestAssured.baseURI = properties.getString("baseURI");
        RestAssured.basePath = properties.getString("basePath");
    }

    private static Locale getDefaultLocale() {
        return new Locale("personal", "personal");
    }
}

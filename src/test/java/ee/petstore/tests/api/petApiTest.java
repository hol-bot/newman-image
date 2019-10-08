package ee.petstore.tests.api;

import ee.petstore.tests.config.configurationProperties;
import ee.petstore.tests.model.Pet;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static io.restassured.RestAssured.given;

public class petApiTest extends configurationProperties {

    @Test
    @DisplayName("Petstore list should conform to JSON API specification")
    public void whenPetListIsRequestedShouldConformToJsonSchema() {
        given().
                    log().all().
                    when().
                    get("/v1/pet/").
                then().
                    contentType(ContentType.JSON).
                    body(matchesJsonSchemaInClasspath("petsListSchema.json")).
                    statusCode(200);
    }

    @Test
    @DisplayName("Petstore list should return 404 if no match")
    public void whenMissingDataShouldReturn404() {
        given().
                log().all().
                when().
                get("/v1/pet/0000").
                then().
                statusCode(404);
    }

    @Test
    @DisplayName("When given pet data to petstore then should add a pet")
    public void whenSendingPetDataShouldAddPet() {
        Pet testCat = new Pet();
        testCat.setName("Ronja");
        testCat.setPetType("Cat");
        testCat.setBirthDate(Instant.now());

        given().log().all().
                contentType("application/json").
                body(testCat).
                when().
                post("/api/v1/pet/").
                then().
                statusCode(200);
    }

}

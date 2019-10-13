package ee.petstore.tests.api;

import ee.petstore.tests.config.ConfigurationProperties;
import ee.petstore.tests.model.Pet;
import ee.petstore.tests.util.CleanupHelper;
import io.restassured.http.ContentType;

import static ee.petstore.tests.util.CleanupHelper.addIdFromResponseToCleanupList;
import static ee.petstore.tests.util.TestDataHelper.randomName;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import io.restassured.response.Response;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PetApiTest extends ConfigurationProperties {

    private static final List<String> CREATED_PETS = new ArrayList<>();

    @AfterAll
    static void cleanup() {
        CleanupHelper.cleanupByPetIds(CREATED_PETS);
    }

    @Test
    @DisplayName("Petstore list should conform to JSON API specification")
    public void whenPetListIsRequestedShouldConformToJsonSchema() {
        given().
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
        when().
                get("/v1/pet/0000").
        then().
                statusCode(404);
    }

    @Test
    @DisplayName("When pet data is sent to petstore then should add a new pet")
    public void whenSendingPetDataShouldAddPet() {

        Pet testCat = new Pet(randomName(),"Cat", Date.from(Instant.now()));

        Response createdPet =
        given().
            contentType("application/json").
            body(testCat).
        when().
            post("/api/v1/pet/").
        then().
            statusCode(200).
            extract().
            response();

        addIdFromResponseToCleanupList(CREATED_PETS, "petId", createdPet);
    }

    @Ignore
    @Test
    @DisplayName("When I update a non-existent pet then I cannot create a new pet")
    public void whenUpdatingNonExistentPetThenGetError() {
        //TODO add a test
    }

}

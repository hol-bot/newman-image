package ee.petstore.tests.fullflow.testdata;

import ee.petstore.tests.model.Pet;
import io.restassured.response.Response;

import java.time.Instant;

import static ee.petstore.tests.util.TestDataHelper.randomName;
import static io.restassured.RestAssured.given;

public class PetstoreTestData {

    public static Pet registerRandomPet() {
        Pet testCat = new Pet(randomName(),"Cat", Instant.now());

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

        testCat.setId(createdPet.jsonPath().getString("petId"));
        return testCat;
    }
}

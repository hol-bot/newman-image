package ee.petstore.tests.fullflow.helpers;

import ee.petstore.tests.config.ConfigurationProperties;
import ee.petstore.tests.model.Pet;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetApiResponses extends ConfigurationProperties {

    public static List<Pet> getPetsList() {
        Response petsResponse = given().
                when().
                    get("/api/v1/pet/").
                then().
                    statusCode(200).
                    extract().
                response();

        List<Pet> Pets = petsResponse.jsonPath().getList(".", Pet.class);
        return Pets;
    }

}

package ee.petstore.tests.util;

import ee.petstore.tests.config.ConfigurationProperties;
import ee.petstore.tests.model.Pet;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Collection;
import java.util.List;

public class CleanupHelper extends ConfigurationProperties {

    public static void addIdFromResponseToCleanupList(List<String> cleanupList, String id, Response response)  {
        JsonPath jsonPathEvaluator = response.jsonPath();
        cleanupList.add(jsonPathEvaluator.get(id).toString());
    }

    public static void addPetToCleanupList(List<String> cleanupList, Pet pet)  {
        cleanupList.add(pet.getId());
    }

    public static void cleanupByPetIds(Collection<String> petIds) {
        deletePetsFromPetstore(petIds);
    }

    private static void deletePetsFromPetstore(Collection<String> petIds) {
        petIds.forEach(petId -> {
            RestAssured.
                    given().
                        pathParam("petId", petId).
                    when().
                        delete("/api/v1/pet/{petId}").
                    then().
                        statusCode(200);
            });
    }
}

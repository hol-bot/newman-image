package ee.petstore.tests.fullflow;

import com.sun.xml.bind.v2.TODO;
import ee.petstore.tests.config.ConfigurationProperties;
import ee.petstore.tests.model.Pet;
import ee.petstore.tests.util.CleanupHelper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static ee.petstore.tests.fullflow.helpers.PetApiAsserts.assertContainsPet;
import static ee.petstore.tests.fullflow.helpers.PetApiResponses.getPetsList;
import static ee.petstore.tests.fullflow.helpers.PetApiResponses.updatePetName;
import static ee.petstore.tests.fullflow.testdata.PetstoreTestData.*;
import static ee.petstore.tests.util.CleanupHelper.addPetToCleanupList;
import static ee.petstore.tests.util.TestDataHelper.randomName;

public class PetFullTest extends ConfigurationProperties {

    private static final List<String> CREATED_PETS = new ArrayList<>();

    @AfterAll
    static void cleanup() {
        CleanupHelper.cleanupByPetIds(CREATED_PETS);
    }

    @Test
    @DisplayName("Given pet is registered to petstore " +
            "when we update the pet's information " +
            "then we can find the updated pet's data from pets list")
    public void whenPetIsRegisteredThenCanReadAndUpdateData() {

        // Given pet is registered to petstore
        Pet randomPet = registerRandomPet();

        // When we update the pet's information
        randomPet.setName(randomName());
        updatePetName(randomPet);

        // Then we can find the updated pet's data from pets list
        assertContainsPet(getPetsList(),randomPet);

        addPetToCleanupList(CREATED_PETS, randomPet);
    }

    @Ignore
    @Test
    @DisplayName("Given pet is registered to petstore " +
            "when we delete the pet from the petstore " +
            "then we cannot find the pet's data from the pets list")
    public void whenPetIsDeletedThenNoDataFromPetsList() {
        //TODO add test
    }

}

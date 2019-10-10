package ee.petstore.tests.fullflow.helpers;

import ee.petstore.tests.model.Pet;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PetApiAsserts {

    public static void assertContainsPet(List<Pet> petsList, Pet comparedPet) {
        assertThat(petsList).anySatisfy(petInList -> assertThat(petInList.getId()).isEqualTo(comparedPet.getId()));
    }

}

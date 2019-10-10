package ee.petstore.tests.util;

import com.github.javafaker.Faker;

public class TestDataHelper {

    public static String randomName()  {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        return firstName;
    }

}

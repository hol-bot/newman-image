package ee.petstore.tests.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Pet {

    private String petId;
    private String name;
    private String petType;
    private String birthDate;

    public Pet(String name, String petType, Instant birthDate) {
        this.name = name;
        this.petType = petType;
        this.birthDate = birthDate.toString();
    }

    @JsonCreator
    public Pet(@JsonProperty("petId") String petId, @JsonProperty("name") String name, @JsonProperty("petType") String petType, @JsonProperty("birthDate") String birthDate) {
        this.petId = petId;
        this.name = name;
        this.petType = petType;
        this.birthDate = birthDate;
    }

    public String getId() {
        return petId;
    }

    public void setId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate.toString();
    }

}

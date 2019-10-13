package ee.petstore.tests.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Pet {

    private String petId;
    private String name;
    private String petType;
    private Date birthDate;;

    public Pet(String name, String petType, Date birthDate) {
        this.name = name;
        this.petType = petType;
        this.birthDate = birthDate;
    }

    @JsonCreator
    public Pet(@JsonProperty("petId") String petId, @JsonProperty("name") String name, @JsonProperty("petType") String petType, @JsonProperty("birthDate") Date birthDate) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}

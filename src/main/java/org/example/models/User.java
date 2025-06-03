package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("personalLastName")
    private String applicantLastName;
    @JsonProperty("personalFirstName")
    private String applicantFirstName;
    @JsonProperty("personalMiddleName")
    private String applicantMiddleName;
    @JsonProperty("personalPhoneNumber")
    private String applicantPhoneNumber;
    @JsonProperty("personalNumberOfPassport")
    private String applicantPassportNumber;
    @JsonProperty("personalAddress")
    private String applicantAddress;
    @JsonProperty("citizenLastName")
    private String citizenLastName;
    @JsonProperty("citizenFirstName")
    private String citizenFirstName;
    @JsonProperty("citizenMiddleName")
    private String citizenMiddleName;
    @JsonProperty("citizenBirthDate")
    private String citizenBirthDate;
    @JsonProperty("citizenNumberOfPassport")
    private String citizenPassportNumber;
    @JsonProperty("citizenGender")
    private String citizenGender;
    @JsonProperty("citizenAddress")
    private String citizenAddress;
    @JsonProperty("dateOfMarriage")
    private String marriageRegistrationDate;
    @JsonProperty("newLastName")
    private String newLastName;
    @JsonProperty("anotherPersonLastName")
    private String spouseLastName;
    @JsonProperty("anotherPersonFirstName")
    private String spouseFirstName;
    @JsonProperty("anotherPersonMiddleName")
    private String spouseMiddleName;
    @JsonProperty("birth_of_anotoherPerson")
    private String spouseBirthDate;
    @JsonProperty("anotherPersonPassport")
    private String spousePassportNumber;
    @JsonProperty("birth_place")
    private String birthPlace;
    @JsonProperty("birth_mother")
    private String motherInfo;
    @JsonProperty("birth_father")
    private String fatherInfo;
    @JsonProperty("birth_grandma")
    private String grandmotherInfo;
    @JsonProperty("birth_grandpa")
    private String grandfatherInfo;
    @JsonProperty("death_dateOfDeath")
    private String deathDate;
    @JsonProperty("death_placeOfDeath")
    private String deathPlace;
}

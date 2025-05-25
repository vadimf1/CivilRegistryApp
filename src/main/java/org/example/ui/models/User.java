package org.example.ui.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String applicantLastName;
    private String applicantFirstName;
    private String applicantMiddleName;
    private String applicantPhoneNumber;
    private String applicantPassportNumber;
    private String applicantAddress;
    private String citizenLastName;
    private String citizenFirstName;
    private String citizenMiddleName;
    private String citizenBirthDate;
    private String citizenPassportNumber;
    private String citizenGender;
    private String citizenAddress;
    private String marriageRegistrationDate;
    private String newLastName;
    private String spouseLastName;
    private String spouseFirstName;
    private String spouseMiddleName;
    private String spouseBirthDate;
    private String spousePassportNumber;
    private String birthPlace;
    private String motherInfo;
    private String fatherInfo;
    private String grandmotherInfo;
    private String grandfatherInfo;
    private String deathDate;
    private String deathPlace;
}

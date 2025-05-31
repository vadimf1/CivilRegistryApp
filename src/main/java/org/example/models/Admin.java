package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Admin {

    @JsonProperty("personalLastName")
    private String lastName;

    @JsonProperty("personalFirstName")
    private String firstName;

    @JsonProperty("personalMiddleName")
    private String middleName;

    @JsonProperty("personalPhoneNumber")
    private String phoneNumber;

    @JsonProperty("personalNumberOfPassport")
    private String passportNumber;

    @JsonProperty("dateofbirth")
    private String birthDate;
}

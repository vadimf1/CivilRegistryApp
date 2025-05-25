package org.example.ui.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Admin {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phoneNumber;
    private String passportNumber;
    private String birthDate;
}

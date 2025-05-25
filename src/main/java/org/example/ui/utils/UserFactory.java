package org.example.ui.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import org.example.ui.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@UtilityClass
public class UserFactory {
    private Faker faker = new Faker(new Locale("ru"));
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private User.UserBuilder createBaseUser() {
        return User.builder()
                .applicantLastName(faker.name().lastName())
                .applicantFirstName(faker.name().firstName())
                .applicantMiddleName(faker.name().firstName() + "овна")
                .applicantPhoneNumber(faker.phoneNumber().cellPhone())
                .applicantPassportNumber(faker.number().digits(10))
                .applicantAddress(faker.address().fullAddress())

                .citizenLastName(faker.name().lastName())
                .citizenFirstName(faker.name().firstName())
                .citizenMiddleName(faker.name().firstName() + "ович")
                .citizenBirthDate(dateFormat.format(faker.date().birthday(20, 80)))
                .citizenPassportNumber(faker.number().digits(10))
                .citizenGender(faker.demographic().sex())
                .citizenAddress(faker.address().fullAddress());
    }

    public User createUserForDeathRegistration() {
        return createBaseUser()
                .deathDate(dateFormat.format(new Date()))
                .deathPlace(faker.address().cityName())
                .build();
    }

    public User createUserForBirthRegistration() {
        return createBaseUser()
                .birthPlace(faker.address().cityName())
                .motherInfo(faker.name().fullName())
                .fatherInfo(faker.name().fullName())
                .grandmotherInfo(faker.name().fullName())
                .grandfatherInfo(faker.name().fullName())
                .build();
    }

    public User createUserForMarriageRegistration() {
        return createBaseUser()
                .marriageRegistrationDate(dateFormat.format(new Date()))
                .newLastName(faker.name().lastName())
                .spouseLastName(faker.name().lastName())
                .spouseFirstName(faker.name().firstName())
                .spouseMiddleName(faker.name().firstName() + "овна")
                .spouseBirthDate(dateFormat.format(faker.date().birthday(20, 40)))
                .spousePassportNumber(faker.number().digits(10))
                .build();
    }
}

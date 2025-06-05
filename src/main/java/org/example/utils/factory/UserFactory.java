package org.example.utils.factory;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import org.example.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@UtilityClass
public class UserFactory {
    private Faker faker = new Faker(new Locale("ru"));
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String gender = "Male";

    private User.UserBuilder createBaseUser() {
        return User.builder()
                .applicantLastName(faker.name().lastName())
                .applicantFirstName(faker.name().firstName())
                .applicantMiddleName(faker.name().firstName() + "овна")
                .applicantPhoneNumber(faker.number().digits(11))
                .applicantPassportNumber(faker.number().digits(8))
                .applicantAddress(faker.address().cityName())

                .citizenLastName(faker.name().lastName())
                .citizenFirstName(faker.name().firstName())
                .citizenMiddleName(faker.name().firstName() + "ович")
                .citizenBirthDate(dateFormat.format(faker.date().birthday(20, 80)))
                .citizenPassportNumber(faker.number().digits(8))
                .citizenGender(gender)
                .citizenAddress(faker.address().cityName());
    }

    public User createUserForDeathRegistration() {
        return createBaseUser()
                .mode("death")
                .deathDate(dateFormat.format(new Date()))
                .deathPlace(faker.address().cityName())
                .build();
    }

    public User createUserForBirthRegistration() {
        return createBaseUser()
                .mode("birth")
                .birthPlace(faker.address().cityName())
                .motherInfo(faker.name().lastName())
                .fatherInfo(faker.name().lastName())
                .grandmotherInfo(faker.name().lastName())
                .grandfatherInfo(faker.name().lastName())
                .build();
    }

    public User createUserForMarriageRegistration() {
        return createBaseUser()
                .mode("wedding")
                .marriageRegistrationDate(dateFormat.format(new Date()))
                .newLastName(faker.name().lastName())
                .spouseLastName(faker.name().lastName())
                .spouseFirstName(faker.name().firstName())
                .spouseMiddleName(faker.name().firstName() + "овна")
                .spouseBirthDate(dateFormat.format(faker.date().birthday(20, 40)))
                .spousePassportNumber(faker.number().digits(8))
                .build();
    }
}

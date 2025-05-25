package org.example.ui.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import org.example.ui.models.Admin;
import org.example.ui.models.User;

import java.text.SimpleDateFormat;
import java.util.Locale;

@UtilityClass
public class AdminFactory {
    private Faker faker = new Faker(new Locale("ru"));
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Admin createAdmin() {
        return Admin.builder()
                .lastName(faker.name().lastName())
                .firstName(faker.name().firstName())
                .middleName(faker.name().firstName() + "овна")
                .phoneNumber(faker.phoneNumber().cellPhone())
                .passportNumber(faker.number().digits(10))
                .birthDate(dateFormat.format(faker.date().birthday(20, 80)))
                .build();
    }
}

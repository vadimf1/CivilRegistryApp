package org.example.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import org.example.models.Admin;

import java.text.SimpleDateFormat;
import java.util.Locale;

@UtilityClass
public class AdminFactory {
    private Faker faker = new Faker(new Locale("ru"));
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Admin createAdmin() {
        return Admin.builder()
                .lastName(faker.name().lastName())
                .firstName(faker.name().firstName())
                .middleName(faker.name().firstName() + "овна")
                .phoneNumber(faker.number().digits(11))
                .passportNumber(faker.number().digits(8))
                .birthDate(dateFormat.format(faker.date().birthday(20, 80)))
                .build();
    }
}

package org.example.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    public static RequestSpecification authenticatedJsonSpec() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Basic dXNlcjpzZW5sYXRlc3Q=")
                .build();
    }
}


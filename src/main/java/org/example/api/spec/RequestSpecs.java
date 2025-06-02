package org.example.api.spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.utils.PropertyUtil;

public class RequestSpecs {

    private static final String BASE_URI_KEY = "baseUri";
    private static final String LOGIN_KEY = "login";
    private static final String PASSWORD_KEY = "password";

    public static RequestSpecification authenticatedJsonSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(PropertyUtil.getProperty(BASE_URI_KEY))
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .setAuth(RestAssured.basic(System.getProperty(LOGIN_KEY), System.getProperty(PASSWORD_KEY)))
                .log(LogDetail.ALL)
                .build();
    }
}

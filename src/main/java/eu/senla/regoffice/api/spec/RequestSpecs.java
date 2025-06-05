package eu.senla.regoffice.api.spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import eu.senla.regoffice.utils.PropertyUtil;

public class RequestSpecs {
    public static RequestSpecification authenticatedJsonSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(PropertyUtil.getProperty("baseUri"))
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .setAuth(RestAssured.basic(System.getProperty("app.login"), System.getProperty("app.password")))
                .log(LogDetail.ALL)
                .build();
    }
}

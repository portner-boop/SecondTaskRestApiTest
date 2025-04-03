package org.example.springboot.secondtaskapitest.helpers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.springboot.secondtaskapitest.config.URLConfig;

import static io.restassured.RestAssured.given;

public class BaseRequests {

    private static final String BASE_URL = URLConfig.BASE_URL;
    private static final String DELETE_ENTITY = "/api/delete/{id}";

    @Step("Initialize request specification")
    public static RequestSpecification initRequestSpecification() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .setAccept(ContentType.JSON);
        return requestSpecBuilder.build();
    }

    @Step("Delete entity by id: {id}")
    public static void deleteEntity(Long id){
        given()
                .when()
                .delete(DELETE_ENTITY,id)
                .then()
                .statusCode(204);
    }
}

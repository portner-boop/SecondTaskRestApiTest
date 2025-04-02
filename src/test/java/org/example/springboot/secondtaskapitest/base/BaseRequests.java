package org.example.springboot.secondtaskapitest.base;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.springboot.secondtaskapitest.config.URLConfig;
import org.example.springboot.secondtaskapitest.models.Response.EntityListResponse;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class BaseRequests {

    private static final String BASE_URL = URLConfig.BASE_URL;
    private static final String GET_ENTITY = "/api/get/{id}";

    @Step("Initialize request specification")
    public static RequestSpecification initRequestSpecification() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .setAccept(ContentType.JSON);
        return requestSpecBuilder.build();
    }

    @Step("Validate response entity")
    public static void getEntityResponseAndCheck(
            Long idForEntity, String title, boolean verified,
            List<Integer> importantNumbers,String additionalInfo,int additionalNumber){
        given()
                .pathParam("id", idForEntity)
                .get(GET_ENTITY)
                .then()
                .statusCode(200)
                .body("id", equalTo(idForEntity.intValue()))
                .body("title", equalTo(title))
                .body("verified", equalTo(verified))
                .body("important_numbers", equalTo(importantNumbers))
                .body("addition.additional_info", equalTo(additionalInfo))
                .body("addition.additional_number", equalTo(additionalNumber));

    }

    @Step("Validate list of entities")
    public static void validateListOfEntitiesResponse(EntityListResponse entityListResponseResponse) {
        for (int i = 0; i < entityListResponseResponse.getEntities().size(); i++) {
            try {
                given()
                        .spec(initRequestSpecification())
                        .when()
                        .get("/api/get/{id}", entityListResponseResponse.getEntities().get(i).getId())
                        .then()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("entity-schema.json"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Step("Delete entity by id: {id}")
    public static void deleteEntity(Long id){
        given()
                .when()
                .delete("/api/delete/{id}",id)
                .then()
                .statusCode(204);
    }
}

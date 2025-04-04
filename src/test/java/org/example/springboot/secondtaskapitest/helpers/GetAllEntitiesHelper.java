package org.example.springboot.secondtaskapitest.helpers;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import org.example.springboot.secondtaskapitest.models.Response.EntityListResponse;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetAllEntitiesHelper {
    private final String GET_ENTITY = "/api/get/{id}";
    private  final String GET_ALL_ENTITIES= "/api/getAll";
    private RequestSpecification requestSpecification;

    public GetAllEntitiesHelper(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    @Step("Getting all entities")
    public  EntityListResponse getAllEntities(){
        return  given()
                .spec(requestSpecification)
                .when()
                .get(GET_ALL_ENTITIES)
                .then()
                .statusCode(200)
                .extract()
                .as(EntityListResponse.class);
    }

    @Step("Validation of entity")
    public void validateEntityWithRequest(int index,EntityListResponse response){
        given()
                .spec(requestSpecification)
                .when()
                .get(GET_ENTITY, response.getEntities().get(index).getId())
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("entity-schema.json"));
    }

}

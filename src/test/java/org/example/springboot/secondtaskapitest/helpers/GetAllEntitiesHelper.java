package org.example.springboot.secondtaskapitest.helpers;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import org.example.springboot.secondtaskapitest.models.Response.EntityListResponse;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.AssertJUnit.assertNotNull;

public class GetAllEntitiesHelper {
    private final String GET_ENTITY = "/api/get/{id}";
    private  final String GET_ALL_ENTITIES= "/api/getAll";
    private EntityListResponse entityListResponse;
    private RequestSpecification requestSpecification;

    public GetAllEntitiesHelper(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    @Step("Getting all entities")
    public  void getAllEntities(){
        entityListResponse=  given()
                .spec(requestSpecification)
                .when()
                .get(GET_ALL_ENTITIES)
                .then()
                .statusCode(200)
                .extract()
                .as(EntityListResponse.class);
    }

    @Step("Validation of the entities")
    public void validateEntities(){
        assertNotNull("Response should not be null", entityListResponse);
        assertNotNull("Entities list should not be null", entityListResponse.getEntities());
        validateListOfEntitiesResponse();
    }

    @Step("Validate list of entities")
    public  void validateListOfEntitiesResponse() {
        for (int i = 0; i < entityListResponse.getEntities().size(); i++) {
            given()
                    .spec(requestSpecification)
                    .when()
                    .get(GET_ENTITY, entityListResponse.getEntities().get(i).getId())
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("entity-schema.json"));
        }
    }

    @Step("Getting list of entities and validate it")
    public  void getAllEntitiesAndValidateIt(){
        getAllEntities();
        validateEntities();
    }
}

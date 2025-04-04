package org.example.springboot.secondtaskapitest.helpers;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import org.example.springboot.secondtaskapitest.models.Request.AdditionRequest;
import org.example.springboot.secondtaskapitest.models.Request.EntityRequest;

import static io.restassured.RestAssured.given;

public class CreateEntityHelper {
    private  final String CREATE_ENTITY = "/api/create";
    private EntityRequest entityRequest;
    private RequestSpecification requestSpecification;

    public CreateEntityHelper(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;

    }

    @Step("Creating remote entity")
    public String createRemoteEntity(){
        return  given()
                .spec(requestSpecification)
                .body(entityRequest)
                .when()
                .post(CREATE_ENTITY)
                .then()
                .statusCode(200)
                .extract()
                .asString();
    }

    @Step("Creating local entity")
    public EntityRequest createEntityLocally(){
        return EntityRequest.builder()
                .addition(AdditionRequest.builder().build())
                .build();
    }

    @Step("Creating entity")
    public String createEntity(){
        entityRequest = createEntityLocally();
        return  createRemoteEntity();
    }

    public EntityRequest getEntityRequest() {
        return entityRequest;
    }
}

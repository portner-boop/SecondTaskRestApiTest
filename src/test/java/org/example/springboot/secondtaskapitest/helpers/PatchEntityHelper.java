package org.example.springboot.secondtaskapitest.helpers;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import org.example.springboot.secondtaskapitest.models.Request.AdditionRequest;
import org.example.springboot.secondtaskapitest.models.Request.EntityRequest;

import static io.restassured.RestAssured.given;

public class PatchEntityHelper {
    private  final String PATCH_ENTITY = "/api/patch/{id}";
    private EntityRequest entityRequest;
    private Long entityId;
    private RequestSpecification requestSpecification;

    public PatchEntityHelper(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
        entityId = 1L;
    }

    @Step("Create local entity for update it remote")
    public void createEntityLocally() {
        entityRequest = EntityRequest.builder()
                .addition(AdditionRequest
                        .builder()
                        .build())
                .build();
    }

    @Step("Update entity")
    public void updateEntity(){
        given()
                .spec(requestSpecification)
                .body(entityRequest)
                .when()
                .patch(PATCH_ENTITY,entityId)
                .then()
                .statusCode(204);
    }

    @Step("Updating remote entity")
    public void updateEntityWithLocalEntity(){
        createEntityLocally();
        updateEntity();
    }
}
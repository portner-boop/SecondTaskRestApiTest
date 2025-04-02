package org.example.springboot.secondtaskapitest.tests;

import io.qameta.allure.Step;
import org.example.springboot.secondtaskapitest.models.Request.AdditionRequest;
import org.example.springboot.secondtaskapitest.models.Request.EntityRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;


public class PatchEntityTest extends BaseTest {

    private final String PATCH_ENTITY = "/api/patch/{id}";
    private Long entityId;

    @BeforeClass
    public void setUp() throws IOException {
        super.setUp();
        entityId = 1L;
    }

    @Test
    @Step("Updating entity")
    public void patchEntityTest(){
        EntityRequest entityRequest =  EntityRequest.builder()
                .addition(AdditionRequest
                        .builder()
                        .build())
                .build();
                given()
                .spec(requestSpecification)
                .body(entityRequest)
                .when()
                .patch(PATCH_ENTITY,entityId)
                .then()
                .statusCode(204);
    }
}

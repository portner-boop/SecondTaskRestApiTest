package org.example.springboot.secondtaskapitest.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.example.springboot.secondtaskapitest.base.BaseRequests;
import org.example.springboot.secondtaskapitest.models.Request.AdditionRequest;
import org.example.springboot.secondtaskapitest.models.Request.EntityRequest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;



import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class CreateEntityTest  extends BaseTest{
    private static final String CREATE_ENTITY = "/api/create";
    private Long userId ;
    private EntityRequest entityRequest;
    private String response;

    @BeforeClass
    public void setUp() throws IOException {
        super.setUp();
    }

    @Test
    @Description("Test creating an entity and verifying its response")
    public void testCreateEntity(){
        createEntityLocally();
        createRemoteEntity();
        validateResponse(response);

    }

    @Step("Creating remote user")
    public void createRemoteEntity(){
        response = given()
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
    public void createEntityLocally(){
        entityRequest  = EntityRequest.builder()
                .addition(AdditionRequest.builder().build())
                .build();
    }

    @Step("Validate response of creating entity")
    public void validateResponse(String responseBody){
        assertNotNull("Response body should not be null", responseBody);
        assertTrue("Response body should be a number", responseBody.matches("\\d+"));
        userId = Long.parseLong(responseBody);
        BaseRequests.getEntityResponseAndCheck(
                userId,
                entityRequest.getTitle(),
                entityRequest.isVerified(),
                entityRequest.getImportantNumbers(),
                entityRequest.getAddition().getAdditionalInfo(),
                entityRequest.getAddition().getAdditionalNumber());
    }

    @AfterMethod
    public void deleteEntityAfterCreation(){
        BaseRequests.deleteEntity(userId);
    }
}

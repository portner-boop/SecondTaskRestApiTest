package org.example.springboot.secondtaskapitest.helpers;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import org.example.springboot.secondtaskapitest.models.Request.AdditionRequest;
import org.example.springboot.secondtaskapitest.models.Request.EntityRequest;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

public class CreateEntityHelper {
    private  final String CREATE_ENTITY = "/api/create";
    private Long userId ;
    private EntityRequest entityRequest;
    private String responseBody;
    private GetEntityHelper getEntityHelper;
    private RequestSpecification requestSpecification;

    public CreateEntityHelper(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
        this.getEntityHelper = new GetEntityHelper();
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

    @Step("Validate response of creating entity")
    public void validateResponse(){
        assertNotNull("Response body should not be null", responseBody);
        assertTrue("Response body should be a number", responseBody.matches("\\d+"));
        userId = Long.parseLong(responseBody);
        getEntityHelper.getEntityResponseAndCheck(
                userId,
                entityRequest.getTitle(),
                entityRequest.isVerified(),
                entityRequest.getImportantNumbers(),
                entityRequest.getAddition().getAdditionalInfo(),
                entityRequest.getAddition().getAdditionalNumber());

    }

    @Step("Creating local entity")
    public EntityRequest createEntityLocally(){
        return EntityRequest.builder()
                .addition(AdditionRequest.builder().build())
                .build();
    }

    @Step("Creating entity and validate it")
    public Long createEntityAndValidateIt(){
        entityRequest = createEntityLocally();
        responseBody = createRemoteEntity();
        validateResponse();
        return userId;
    }
}

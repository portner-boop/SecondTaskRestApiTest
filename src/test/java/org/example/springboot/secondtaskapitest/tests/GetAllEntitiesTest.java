package org.example.springboot.secondtaskapitest.tests;


import io.qameta.allure.Step;
import org.example.springboot.secondtaskapitest.base.BaseRequests;
import org.example.springboot.secondtaskapitest.models.Response.EntityListResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;

public class GetAllEntitiesTest extends BaseTest {

    private final String GET_ALL_ENTITIES= "/api/getAll";
    private EntityListResponse entityListResponse;

    @BeforeClass
    public void setUp() throws IOException {
        super.setUp();
    }

    @Test
    @Step("Getting all entities and validate")
    public void getAllEntitiesAndCheck() throws IOException {
        getAllEntities();
        validateEntities();
    }

    @Step("Getting all entities")
    public void getAllEntities() {
         entityListResponse = given()
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
        BaseRequests.validateListOfEntitiesResponse(entityListResponse);
    }
}

package org.example.springboot.secondtaskapitest.tests;


import io.qameta.allure.Description;
import org.example.springboot.secondtaskapitest.helpers.GetAllEntitiesHelper;
import org.example.springboot.secondtaskapitest.models.Response.EntityListResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;

public class GetAllEntitiesTest extends BaseTest {

    private GetAllEntitiesHelper getAllEntitiesHelper;
    private EntityListResponse entityListResponse;

    @BeforeClass
    public void setUp() {
        super.setUp();
        getAllEntitiesHelper = new GetAllEntitiesHelper(requestSpecification);
    }

    @Test
    @Description(" Test for getting list of entities and validate it")
    public void getAllEntitiesAndCheck() {
        entityListResponse = getAllEntitiesHelper.getAllEntities();
        assertNotNull("Response should not be null", entityListResponse);
        assertNotNull("Entities list should not be null", entityListResponse.getEntities());
        for (int i = 0; i < entityListResponse.getEntities().size(); i++) {
            getAllEntitiesHelper.validateEntityWithRequest(i,entityListResponse);
        }
    }
}

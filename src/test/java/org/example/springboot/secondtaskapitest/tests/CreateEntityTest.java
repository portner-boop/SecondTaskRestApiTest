package org.example.springboot.secondtaskapitest.tests;

import io.qameta.allure.Description;
import org.example.springboot.secondtaskapitest.helpers.BaseRequests;
import org.example.springboot.secondtaskapitest.helpers.CreateEntityHelper;
import org.example.springboot.secondtaskapitest.helpers.GetEntityHelper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;


public class CreateEntityTest  extends BaseTest{
    private CreateEntityHelper createEntityHelper;
    private GetEntityHelper getEntityHelper;
    private String responseBody;
    private Long userId;

    @BeforeClass
    public void setUp() {
        super.setUp();
        createEntityHelper = new CreateEntityHelper(requestSpecification);
        getEntityHelper = new GetEntityHelper();
    }

    @Test
    @Description("Test for creating entity and verifying its response")
    public void testCreateEntity(){
        responseBody = createEntityHelper.createEntity();
        assertNotNull("Response body should not be null", responseBody);
        assertTrue("Response body should be a number", responseBody.matches("\\d+"));
        userId = Long.parseLong(responseBody);
        getEntityHelper.getEntityResponseAndCheck(
                userId,
                createEntityHelper.getEntityRequest().getTitle(),
                createEntityHelper.getEntityRequest().isVerified(),
                createEntityHelper.getEntityRequest().getImportantNumbers(),
                createEntityHelper.getEntityRequest().getAddition().getAdditionalInfo(),
                createEntityHelper.getEntityRequest().getAddition().getAdditionalNumber());
    }

    @AfterClass
    public void deleteEntityAfterCreation(){
        BaseRequests.deleteEntity(userId);
    }
}

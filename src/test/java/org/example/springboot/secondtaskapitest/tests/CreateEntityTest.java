package org.example.springboot.secondtaskapitest.tests;

import io.qameta.allure.Description;
import org.example.springboot.secondtaskapitest.helpers.BaseRequests;
import org.example.springboot.secondtaskapitest.helpers.CreateEntityHelper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CreateEntityTest  extends BaseTest{

    private CreateEntityHelper createEntityHelper;
    private Long userId;

    @BeforeClass
    public void setUp() {
        super.setUp();
        createEntityHelper = new CreateEntityHelper(requestSpecification);
    }

    @Test
    @Description("Test for creating entity and verifying its response")
    public void testCreateEntity(){
        userId = createEntityHelper.createEntityAndValidateIt();
    }

    @AfterClass
    public void deleteEntityAfterCreation(){
        BaseRequests.deleteEntity(userId);
    }
}

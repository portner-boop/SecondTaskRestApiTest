package org.example.springboot.secondtaskapitest.tests;


import io.qameta.allure.Description;
import org.example.springboot.secondtaskapitest.helpers.GetAllEntitiesHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetAllEntitiesTest extends BaseTest {

    private GetAllEntitiesHelper getAllEntitiesHelper;

    @BeforeClass
    public void setUp() {
        super.setUp();
        getAllEntitiesHelper = new GetAllEntitiesHelper(requestSpecification);
    }

    @Test
    @Description(" Test for getting list of entities and validate it")
    public void getAllEntitiesAndCheck() {
        getAllEntitiesHelper.getAllEntitiesAndValidateIt();
    }
}

package org.example.springboot.secondtaskapitest.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import org.example.springboot.secondtaskapitest.helpers.BaseRequests;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void setUp() {
        requestSpecification = BaseRequests.initRequestSpecification()
                .filter(new AllureRestAssured());
    }
}

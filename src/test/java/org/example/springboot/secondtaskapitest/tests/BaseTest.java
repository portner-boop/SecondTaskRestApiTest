package org.example.springboot.secondtaskapitest.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import org.example.springboot.secondtaskapitest.base.BaseRequests;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void setUp() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification()
                .filter(new AllureRestAssured());
    }
}

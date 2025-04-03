package org.example.springboot.secondtaskapitest.tests;

import io.qameta.allure.Description;
import org.example.springboot.secondtaskapitest.helpers.GetEntityHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class GetEntityTest{

    private GetEntityHelper getEntityHelper;

    @BeforeClass
    public void setUp(){
        getEntityHelper = new GetEntityHelper();
    }

    @Test
    @Description("Test for getting remote entity by id")
    public void getEntityWithCorrectResponse(){
        getEntityHelper.getEntityResponseAndCheck(
                1L,"Заголовок сущности",
                true,new ArrayList<>(Arrays.asList(42, 87, 15)),
                "Дополнительные сведения",123);
    }
}

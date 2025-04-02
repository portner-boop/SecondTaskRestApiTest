package org.example.springboot.secondtaskapitest.tests;

import io.qameta.allure.Step;
import org.example.springboot.secondtaskapitest.base.BaseRequests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class GetEntityTest{

    @Test
    @Step("Getting entity")
    public void getEntityWithCorrectResponse(){
        BaseRequests.getEntityResponseAndCheck(1L,"Заголовок сущности",
                true,new ArrayList<>(Arrays.asList(42, 87, 15)),
                "Дополнительные сведения",123);
    }
}

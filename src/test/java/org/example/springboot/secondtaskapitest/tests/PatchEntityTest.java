package org.example.springboot.secondtaskapitest.tests;

import io.qameta.allure.Description;
import org.example.springboot.secondtaskapitest.helpers.PatchEntityHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class PatchEntityTest extends BaseTest {
    private PatchEntityHelper patchEntityHelper;

    @BeforeClass
    public void setUp()  {
        super.setUp();
        patchEntityHelper = new PatchEntityHelper(requestSpecification);
    }

    @Test
    @Description("Test for creating update-entity and updating remote entity")
    public void patchEntityTest(){
        patchEntityHelper.updateEntityWithLocalEntity();
    }
}

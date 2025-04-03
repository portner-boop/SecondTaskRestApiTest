package org.example.springboot.secondtaskapitest.helpers;

import io.qameta.allure.Step;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetEntityHelper {
    private final String GET_ENTITY = "/api/get/{id}";

    @Step("Validate response entity")
    public  void getEntityResponseAndCheck(
            Long idForEntity, String title, boolean verified,
            List<Integer> importantNumbers, String additionalInfo, int additionalNumber){
        given()
                .pathParam("id", idForEntity)
                .get(GET_ENTITY)
                .then()
                .statusCode(200)
                .body("id", equalTo(idForEntity.intValue()))
                .body("title", equalTo(title))
                .body("verified", equalTo(verified))
                .body("important_numbers", equalTo(importantNumbers))
                .body("addition.additional_info", equalTo(additionalInfo))
                .body("addition.additional_number", equalTo(additionalNumber));
    }
}

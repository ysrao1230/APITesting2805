package com.api.test;

import com.api.basePackage.FileConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class POSTAPIUsiingFIle {

    @Test
    public void postAPIUsingFile() {
        try {
            String postData = FileUtils.readFileToString(new File(FileConstants.PAYLOAD_TXT_FILE), "UTF-8");
            //System.out.println(postData);
            Response res =
                    RestAssured
                            .given()
                            .contentType(ContentType.JSON)
                            .body(postData)
                            .baseUri("https://restful-booker.herokuapp.com/booking")
                            .when()
                            .post()
                            .then()
                            .assertThat()
                            .statusCode(200)
                            .body("booking.firstname", Matchers.equalTo("Josh"))
                            .extract()
                            .response();
            JsonPath jp = new JsonPath(res.asString());
            System.out.println("Response Body is:");
            System.out.println(res.asString());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package com.api.test;

import com.api.basePackage.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class POSTAPIRequest extends BaseTest {

    @Test
    public void postRequest() {
        //Preparing the Request body

        JSONObject object = new JSONObject();
        JSONObject object2 = new JSONObject();
        object.put("firstname", "book1");
        object.put("lastname", "book lastname");
        object.put("totalprice", 100);
        object.put("depositpaid", true);
        object.put("additionalneeds", "Testing additional");
        object.put("bookingdates", object2);

        object2.put("checkin", "2023-05-23");
        object2.put("checkout", "2023-05-30");

        Response response =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(object.toString())
                        .baseUri("https://restful-booker.herokuapp.com/booking")
                        .when()
                        .post()
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .body("booking.firstname", Matchers.equalTo("book1"))
                        .extract()
                        .response();

        int booking_id = response.path("bookingid");
        System.out.println(booking_id);

        Response res =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .pathParam("bookingId", 2336)
                        .baseUri("https://restful-booker.herokuapp.com/booking")
                        .when()
                        .get("{bookingId}")
                        .then()
                        .extract()
                        .response();
        JsonPath jp = new JsonPath(res.asString());
        System.out.println(jp.getString("firstname"));
    }
}

package com.otelrezervasyonu.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class updateBookingTest extends BaseTest {

    @Test
    public void updateBookingTest(){







        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token="+ createToken())
                .body(bookingObject("Elif","Başaran",250,false))
                .put("/booking/" + createBookingId());




        String firstName =  response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        int totalPrice = response.jsonPath().getJsonObject("totalprice");
        boolean depositPaid = response.jsonPath().getJsonObject("depositpaid");


        Assertions.assertEquals("Elif",firstName);
        Assertions.assertEquals("Başaran",lastName);
        Assertions.assertEquals(250,totalPrice);
        Assertions.assertEquals(false,response.jsonPath().getJsonObject("depositpaid"));

    }

}

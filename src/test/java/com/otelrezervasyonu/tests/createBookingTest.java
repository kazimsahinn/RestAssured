package com.otelrezervasyonu.tests;

import com.otelrezervasyonu.models.Booking;
import com.otelrezervasyonu.models.Bookingdates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class createBookingTest extends BaseTest{

    @Test
    public void createBookingTest() {

        // Cagriyi gerçeklestir

        Response response = createBooking();


        Assertions.assertEquals("Kazım",response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Şahin",response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(150,(Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,response.jsonPath().getJsonObject("booking.depositpaid"));


    }

    @Test
    public void createBookingWihtPojo(){
        Bookingdates bookingdates = new Bookingdates("2023-05-01","2023-07-07");
        Booking booking = new Booking("Hasan","Şahin",200,false,bookingdates,"Kahvaltı");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        response
                .then()
                .statusCode(200);
    }
}

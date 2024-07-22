package com.otelrezervasyonu.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class getBookingTest extends BaseTest{


    @Test
    public void getBookingTest(){

        given(spec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200);

    }

    @Test
    public void getBookings_with_firstname_filter_test(){
        // yeni rezervasyon olustur
        int bookingID = createBookingId();

        // Cagriya Query Parametresi ekle
        spec.queryParam("firstname","Kazım");
        spec.queryParam("lastname","Şahin");

        // Cagriyi gerceklestir

        Response response = given(spec)
                .when()
                .get("/booking");


        // Assertionları yaz

        response
                .then()
                .statusCode(200);

        List<Integer> filtrelenenRezervasyon = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(filtrelenenRezervasyon.contains(bookingID));
    }
}

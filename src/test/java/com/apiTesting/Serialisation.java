package com.apiTesting;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
public class Serialisation {

	public static void main(String[] args) {
		
		
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		
		LocationDetail lc = new LocationDetail();
		Addplace ap = new Addplace();
		ap.setAccuracy(50);
		
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		
		ap.setLocation(lc);
		ap.setName("Frontline house");
		
		List<String> li = new ArrayList<String>();
		li.add("shoe park");
		li.add("shop");
		
		ap.setTypes(li);
		ap.setAddress("29, side layout, cohen 09");
		
		
		
		
				
		
		given().log().all().queryParam("key", "qaclick123").header("content-type","application/json")
		.body(ap).when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
		
	}
	
}

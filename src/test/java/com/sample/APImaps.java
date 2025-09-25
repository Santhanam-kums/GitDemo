package com.sample;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
public class APImaps {
   
	
	public static void main(String[] args) {
		
	
		
		//Add place
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		
	String response =	given().log().all().queryParam("key", "quclick123").header("content-type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "").when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).extract().response().asString();
		
	JsonPath js = new JsonPath(response);
	        String placeId =    js.getString("place_id");  
	        System.out.println(placeId);
	        
	
	
		//updateplace
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String address =given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json").
		body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(address);
		
		//getPlace
		
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		
		String getAddress = given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
		.when().get("/maps/api/place/get/json").then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(getAddress);
//		JsonPath js1 = new JsonPath(getAddress);
//		String newAddress =js1.getString("address");
//		System.out.println(newAddress);
		
	}
	
	
	@Test
     public void name() {
		
		
	}
	
	
	

	
}

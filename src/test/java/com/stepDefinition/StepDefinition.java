package com.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import baseClass.APIRescourses;
import baseClass.AppPlacePayLoadSerialisation;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition {

	RequestSpecification res;
	ResponseSpecification response;
	Response exactResponse;
	static String place_id;
	
	AppPlacePayLoadSerialisation ap = new AppPlacePayLoadSerialisation();
	
	

	@Given("User addPlace Payload {string} {string} {string}")
	public void user_add_place_payload(String name, String phone, String address) throws IOException {
	   
		res = given().spec(ap.requestSpecfication()).body(ap.AddPlacePayload(name,phone,address));

	}

	

	@When("User calls {string} with {string} Https request")
	public void user_calls_with_https_request(String Rescource, String Method) {
	   
	
	APIRescourses rescourceValue  =	APIRescourses.valueOf(Rescource);
	System.out.println(rescourceValue.getRescource());
			
			 response = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			 
			 if (Method.equalsIgnoreCase("POST")) {
				 exactResponse =	res.when().post(rescourceValue.getRescource()).then().spec(response).extract()
	                     .response();
			}else if (Method.equalsIgnoreCase("GET")) {
				 exactResponse =	res.when().get(rescourceValue.getRescource()).then().spec(response).extract()
	                     .response();
			}
			 
	}



	
	@Then("The API call got success with static code {int}")
	public void the_api_call_got_success_with_static_code(Integer int1) throws InterruptedException {
	   
		Thread.sleep(5000);
		
		Assert.assertEquals(exactResponse.getStatusCode(),200);
		
	}
	
	

	@Then("User check {string} in response body is {string}")
	public void user_check_in_response_body_is(String keyValue, String expectedValue) {
	    
		
		Assert.assertEquals(ap.getJsonResponse(exactResponse, keyValue), expectedValue);
		
		
		
	}
	



	@Then("User Verify place_id created maps to {string} using {string}")
	public void user_verify_place_id_created_maps_to_using(String expectName, String Rescourse) throws IOException {
	  
		
		  place_id = ap.getJsonResponse(exactResponse,"place_id");

		res = given().spec(ap.requestSpecfication()).queryParam("place_id", place_id );
		
		user_calls_with_https_request(Rescourse,"GET");
	   String actualName =	ap.getJsonResponse(exactResponse,"name");
	   System.out.println(actualName);
	   Assert.assertEquals(actualName, expectName);
		
	}


	

	@Given("User DeletePlace payload")
	public void user_delete_place_payload() throws IOException {
	    
		res = given().spec(ap.requestSpecfication()).body(ap.deletePlacePayload(place_id));	
	}











}

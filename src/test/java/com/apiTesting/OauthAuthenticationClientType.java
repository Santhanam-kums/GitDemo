package com.apiTesting;
import static io.restassured.RestAssured.*;

import java.util.List;

import io.restassured.path.json.JsonPath;
public class OauthAuthenticationClientType {

	
	
	public static void main(String[] args) {
		
		String getResponse =given().log().all().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type","client_credentials")
		.formParam("scope","trust")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.asString();
		
		JsonPath js = new JsonPath(getResponse);
		String AccessToken = js.getString("access_token");
		System.out.println(AccessToken);
		
		Getcourse gc  = given().log().all().queryParam("access_token",AccessToken)
		.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.as(Getcourse.class);
		
		System.out.println(gc.getInstructor());
		System.out.println(gc.getUrl());
		
		List<Api> course = gc.getCourses().getApi();
		for (int i = 0; i < course.size(); i++) {
			
			String titles = course.get(i).getCourseTitle();
			System.out.println(titles);
			System.out.println("never");
			System.out.println("ever");
			
		}
		
		
		
		
		
		
		
	}
}

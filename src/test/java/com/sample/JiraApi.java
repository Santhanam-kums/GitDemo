package com.sample;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.File;
public class JiraApi {

	
	
	public static void main(String[] args) {
		
		
		//createbug
		
		RestAssured.baseURI ="https://santhanakumar3172.atlassian.net";
		
	String createIssueResponse =	given().log().all().header("Content-Type","application/json").header("Authorization","Basic c2FudGhhbmFrdW1hcjMxNzJAZ21haWwuY29tOkFUQVRUM3hGZkdGMDRxOVplazBJTnNhVFp0ZmRNcGpES0h4clVIU1B3eUZHMWxVdE1kZzRhTG1QRUF1c3pEUXB1NXZBd2haQWNHb2swTktPU3I1UzA3SE93Tno0RU0xdWUxNC1BUjNnX2xXQy1fRUlvZmxBaEFNYndGQ2Faenl6S2EyX2dremVUNzZkTTBhTFZPVDNBQmJpS2gyaHZpV1dmekJ4OWN0SUVURGVqeHhRU0ZtVW8xbz02MkYwMEI1Qg==")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"FirstPagelinks are not working.\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
	
	JsonPath js = new JsonPath(createIssueResponse);
	String issueId = js.getString("id");
	System.out.println(issueId);
	
	//Add Attachment
	
	RestAssured.baseURI ="https://santhanakumar3172.atlassian.net";
	
	given().relaxedHTTPSValidation().log().all().pathParam("key", issueId).header("Content-Type","multipart/form-data")
	
	.header("X-Atlassian-Token","no-check")
	.header("Authorization","Basic c2FudGhhbmFrdW1hcjMxNzJAZ21haWwuY29tOkFUQVRUM3hGZkdGMDRxOVplazBJTnNhVFp0ZmRNcGpES0h4clVIU1B3eUZHMWxVdE1kZzRhTG1QRUF1c3pEUXB1NXZBd2haQWNHb2swTktPU3I1UzA3SE93Tno0RU0xdWUxNC1BUjNnX2xXQy1fRUlvZmxBaEFNYndGQ2Faenl6S2EyX2dremVUNzZkTTBhTFZPVDNBQmJpS2gyaHZpV1dmekJ4OWN0SUVURGVqeHhRU0ZtVW8xbz02MkYwMEI1Qg==")
	.multiPart("file",new File("C:\\Users\\SANTHANAKUMAR\\Documents\\Chilambarasan Resume.docx"))
	 .when().post("api/3/issue/{key}/attachments").then()
	 .log().all().assertThat().statusCode(200);
	}
}

package com.sample;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PassDatatoPayload {

	
	
	@Test(dataProvider = "BookData")
	
	public void addBook(String isbn,String aisle) {

		RestAssured.baseURI ="http://216.10.245.166";
		
		String bookAdded = given().log().all().body(PayloadClass.dynamicPayload(isbn,aisle))
		.when().post("Library/Addbook.php").then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(bookAdded);
		String bookId = js.get("ID");
		System.out.println(bookId);
	}
	
	@DataProvider(name ="BookData")
	
	public Object[][] getData() {

         Object[][] obj =    new Object[][] {{"cccc","1111"},{"aaaa","2222"},{"bbbb","5555"}};
         
         return obj;
	}
	
	
}

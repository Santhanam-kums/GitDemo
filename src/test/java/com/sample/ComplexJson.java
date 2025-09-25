package com.sample;

import java.util.Iterator;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		
		int sum =0;
		
		JsonPath js = new JsonPath(PayloadClass.coursePayload());
		
		//print no of courses
	int count =	js.getInt("courses.size()");
	System.out.println(count);
	
	//print purchaseAmt
	int purchaseAmt = js.getInt("dashboard.purchaseAmount");
	System.out.println(purchaseAmt);
	
	//Print Title of the first course
	String firstTitle = js.getString("courses[0].title");
	System.out.println(firstTitle);
	
	// Print All course titles and their respective Prices
	for (int i = 0; i < count; i++) {
		
		String courseTitles = js.getString("courses["+i+"].title");
		System.out.println(courseTitles);
		
	System.out.println(js.get("courses["+i+"].price").toString());
		
	}
	
	//Print no of copies sold by RPA Course
	
	for (int i = 0; i < count; i++) {
		
		String titles = js.getString("courses["+i+"].title");
		
		if (titles.equalsIgnoreCase("RPA")) {
			
			int copies = js.get("courses["+i+"].copies");
			System.out.println(copies);
			break;
		}
	}
	
	// Verify if Sum of all Course prices matches with Purchase Amount
	
	for (int i = 0; i < count; i++) {
		
		int allprice = js.get("courses["+i+"].price");
		
		int allcopies = js.get("courses["+i+"].copies");
		
		int amount = allprice * allcopies;
		
		sum = sum + amount;
		
	}
	
	System.out.println(sum);
	
	int purchaseamt = js.get("dashboard.purchaseAmount");
	
	Assert.assertEquals(sum, purchaseamt);
	
	}
	
	
	
}

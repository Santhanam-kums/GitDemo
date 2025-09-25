package com.sample;

import org.apache.http.client.methods.RequestBuilder;

import baseClass.LoginDetail;
import baseClass.LoginResponse;
import baseClass.OrderRequest;
import baseClass.orderDetails;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ecommerce {

	
	public static void main(String[] args) {
		
		//login
		
	RequestSpecification req =	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
	
	LoginDetail ld = new LoginDetail();
	ld.setUserEmail("jordan@gmail.com");
	ld.setUserPassword("Jordan@123");
	
	LoginResponse lr = given().log().all().spec(req).body(ld).when().post("/api/ecom/auth/login")
	.then().log().all().assertThat().statusCode(200).extract().response().as(LoginResponse.class);
	
	String token = lr.getToken();
	String userId = lr.getUserId();
	
	//CreateProduct
	
	RequestSpecification rs = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
	.addHeader("Authorization", token).build();
	
	
	String createProductRespone = given().log().all().spec(rs).param("productName", "Samsung").
	param("productAddedBy", userId).param("productCategory", "fashion")
	.param("productSubCategory", "shirts").param("productPrice", "11500")
	.param("productDescription", "phone")
	.multiPart("productImage",new File("C:\\Users\\SANTHANAKUMAR\\OneDrive\\Desktop\\phonefolder\\Phone.png"))
	.when().post("/api/ecom/product/add-product")
	.then().log().all().assertThat().statusCode(201).extract().response().asString();
	
	JsonPath js = new JsonPath(createProductRespone);
	String productId = js.getString("productId");
	
	//Create product order
	RequestSpecification rc = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
	OrderRequest or = new OrderRequest();
	orderDetails od = new orderDetails();
	od.setCountry("India");
	od.setProductOrderedId(productId);
	
	List<orderDetails> li = new ArrayList<orderDetails>();
	li.add(od);
	
	or.setOrders(li);
	
	
	
	given().log().all().spec(rc).body(li).when().post("/api/ecom/order/create-order")
	.then().log().all().assertThat().statusCode(201);
	
	//deleteProduct
	
//	given().log().all().spec(rs).pathParam("productId",productId).when().delete("/api/ecom/product/delete-product/{productId}")
//	.then().log().all().assertThat().statusCode(200);
	
	
	
	
		
	}
	
}

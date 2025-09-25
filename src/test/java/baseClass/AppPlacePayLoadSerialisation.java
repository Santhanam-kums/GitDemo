package baseClass;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.apiTesting.Addplace;
import com.apiTesting.LocationDetail;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AppPlacePayLoadSerialisation extends Addplace {

	public static RequestSpecification req;
		
		public baseClass.Addplace AddPlacePayload(String name, String phone,String address) {
			// TODO Auto-generated method stub

		
			
		

		
			
		

		
		
		baseClass.LocationDetail lc = new baseClass.LocationDetail();
		baseClass.Addplace ap = new baseClass.Addplace();
		ap.setAccuracy(50);
		
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		
		ap.setLocation(lc);
		ap.setName(name);
		
		List<String> li = new ArrayList<String>();
		li.add("shoe park");
		li.add("shop");
		ap.setLanguage("fench");
		ap.setPhone_number(phone);
		
		ap.setTypes(li);
		ap.setAddress(address);
		
		return ap;
		

		
		

	}
		
		public RequestSpecification requestSpecfication() throws IOException {

			if (req==null) {
				
			
			PrintStream log = new PrintStream(new FileOutputStream("logging.text"));
			 req = new RequestSpecBuilder().setBaseUri(baseUri("BaseURL")).
					 addFilter(RequestLoggingFilter.logRequestTo(log)).
					 addFilter(ResponseLoggingFilter.logResponseTo(log)).
                    addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
			return req;
		}
			return req;
		}	
		public String baseUri(String uri) throws IOException {

			Properties prop = new Properties();
			FileInputStream file = new FileInputStream("C:\\Users\\SANTHANAKUMAR\\eclipse-workspace2.0\\DemoAPI\\src\\test\\resources\\golbalProperties.properties");
			prop.load(file);
		return	prop.getProperty(uri);
			
		}
		
		public String getJsonResponse(Response exactResponse,String keyValue) {
			
			String stringstatus = exactResponse.asString();
			 JsonPath js = new JsonPath(stringstatus);
			
        		return	js.get(keyValue);
		}
		
		public String deletePlacePayload(String place_id) {

           return   "{\r\n"
              + "    \"place_id\":\""+place_id+"\"\r\n"
              + "}\r\n"
              + "";
		}
}
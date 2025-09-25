package com.stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class HooksClass {

	
	@Before
	public void getPlaceIDbeforeAddAPI() throws IOException {

		StepDefinition sd = new StepDefinition();
		
		if (StepDefinition.place_id==null) {
			
		
		sd.user_add_place_payload("vijay", "777777777", "c3/30");
		sd.user_calls_with_https_request("AddPlaceAPI", "Post");
		sd.user_verify_place_id_created_maps_to_using("vijay", "GetPlaceAPI");
		
		}
	}
}

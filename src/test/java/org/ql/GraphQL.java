package org.ql;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

public class GraphQL {

	public static void main(String[] args) {
	
		//PostQuery
	String response =	given().log().all().header("Content-Type","application/json").
		body("{\"query\":\"query ($locationId: Int!, $characterId: Int!, $episodeId: Int!) {\\"
				+ "n  location(locationId: $locationId) {\\n    name\\n    dimension\\n  }\\"
				+ "n  character(characterId: $characterId) {\\n    name\\n    type\\n    status\\n  }\\"
				+ "n  episode(episodeId: $episodeId) {\\n    name\\n    air_date\\n    episode\\"
				+ "n    characters {\\n      id\\n      name\\n    }\\n  }\\"
				+ "n  locations(filters: {name: \\\"spain\\\"}) {\\n    info {\\n      count\\n    }\\n  }\\n  \\n}"
				+ "\\n\",\"variables\":{\"locationId\":24654,\"characterId\":17763,\"episodeId\":16630}}").
		when().post("https://rahulshettyacademy.com/gq/graphql").then()
		.log().all().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js = new JsonPath(response);
	String nameValue = js.get("data.location.name");
	System.out.println(nameValue);
	
	//Mutation
	String mutationresponse =	given().log().all().header("Content-Type","application/json").
			body("{\"query\":\"mutation ($LocationName: String!, $characterName: String!, $episodeName: String!,$dimension:String!) {\\"
					+ "n  createLocation(location: {name: $LocationName, type: \\\"southzone\\\", dimension: $dimension}) {\\n    id\\n  }\\"
					+ "n  createCharacter(character: {name: $characterName, type: \\\"handsome\\\", status: \\\"alive\\\", species: \\\"human/alian\\\", gender: \\\"male\\\", image: \\\"png\\\", originId: 24434, locationId: 24434}) {\\n    id\\n  }\\"
					+ "n  createEpisode(episode: {name: $episodeName, air_date: \\\"2013\\\", episode: \\\"prime\\\"}) {\\n    id\\n  }\\"
					+ "n  deleteLocations(locationIds:[8]){\\n    locationsDeleted\\n  }\\n}\\"
					+ "n\",\"variables\":{\"LocationName\":\""+nameValue+"\",\"dimension\":\"8527\",\"characterName\":\"Jon snow\",\"episodeName\":\"Game of throne\"}}").
			when().post("https://rahulshettyacademy.com/gq/graphql").then()
			.log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(mutationresponse);
		int idnum = js1.get("data.createCharacter.id");
		System.out.println(idnum);
		
	}
}

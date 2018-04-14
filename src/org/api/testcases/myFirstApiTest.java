package org.api.testcases;

import org.api.base.EndPoint;
import org.api.base.TestConfig;
import static io.restassured.RestAssured.*;
//import org.testng.annotations.Test;
import org.junit.Test;

public class myFirstApiTest extends TestConfig{

	@Test
	public void myFirstTest()
	{
		given().
		when().get("videogames/1").
		then().statusCode(200);
	}

	// log examples
	@Test
	public void addingLogsToTestCases()
	{
		given().
			log().all().
		when().get("videogames/1").
		then().
			log().all().
			statusCode(200);
	}
	
	@Test
	public void addingRequestLogsOnTestFail()
	{
		given().
			log().ifValidationFails().
		when().get("videogames/1").
		then().statusCode(202);
	}
	
	
	@Test
	public void addingResponseLogsonTestFail()
	{
		given().
		when().get("videogames/1").
		then().
			log().ifValidationFails().
			statusCode(20);
	}
	
	
	//request and response specifications example
	
	@Test
	public void usingRequestResponseSpecFromTestconfig()
	{
		given().log().all().
		when().get("videogames/1").
		then().log().all();
		
	}
	
	
		
	//Get all video games by defining endpoints in an Interface and accessing them
	@Test
	public void getAllgames()
	{
		String responseBody = given().log().all().
								when().get(EndPoint.Get_Videogames).asString(); //writing absolute minimum code to call the api
		System.out.println(responseBody);
		
	}
	
	
	//Get single video game using path parameter
	
	@Test
	public void getSingleGame()
	{
		String single_game= given().
			pathParam("GameId", 5).
		when().
			get("/videogames/{GameId}").asString();
		
		System.out.println(single_game);
	}
	
	//Getting single video game by reading the endpoint from interface
	
	
	@Test
	public void getSingleGameFromInterface()
	{
		//int id =2;
		String single_game= given().
				log().
				all().
				pathParam("GameId", 2).
		when().
				get(EndPoint.single_videogame).
				asString();
		
		System.out.println(single_game);
	}
	
}

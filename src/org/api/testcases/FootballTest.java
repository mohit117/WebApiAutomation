package org.api.testcases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.api.base.TestConfig;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.javascript.host.speech.SpeechSynthesisUtterance;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FootballTest extends TestConfig {
	
	
	// Returns all competitions from year 2016
	@Test
	public void getAllCompetiotionsOneSeason()
	{
		String response = given().spec(football_requestSpec).
									log().all().
									queryParam("season", 2016).
						  when().
						  			get("competitions").asString();
		
		System.out.println(response);
	}
	
	
	//Getting team count from a team
	@Test
	public void getTeamCount_OneComp()
	{
		given().
				spec(football_requestSpec).
				log().
				all().
		when().
				get("competitions/426/teams").
		then().
				body("count", equalTo(20));
	}
	
	
	//Getting first team name
	
	@Test
	public void getFirstTeamName()
	{
		given().
			spec(football_requestSpec).
			log().
			all().
		when().
			get("competitions/426/teams").
		then().
			body("teams.name[0]", equalTo("Hull City FC"));
	}

	
	//extract all response as string without doing any validaiton first
	
	@Test
	public void extractAllResponseWithoutValidation()
	{
		String res = given().
						spec(football_requestSpec).
						log().
						all().
					when().
						get("competitions/426/teams").asString();
		
		System.out.println(res);
	}
	
	
	//extracting complete response using Response class
	@Test
	public void extractAllResponseAsStringAfterFewValidations()
	{
		Response res = given().
							spec(football_requestSpec).
							log().
							all().
						when().
							get("competitions/426/teams").
						then().
							contentType(ContentType.JSON).
							extract().
							response();
		
		String jsonResponseAsString = res.asString();
		System.out.println(jsonResponseAsString);
	}
	
	
	//Extracting specific information from Json response
	@Test
	public void extractFirstTeamName()
	{
		String firstTeamName = given().spec(football_requestSpec).when().
								get("competitions/426/teams").jsonPath().getString("teams.name[0]");
		
		System.out.println(firstTeamName);
	}
	
	
	//Extracting all team names only 
	@Test
	public void extractingAllTeamNamesOnly()
	{
		Response res = given().
				spec(football_requestSpec).
				log().
				all().
			when().
				get("competitions/426/teams").
			then().
				contentType(ContentType.JSON).
				extract().
				response();
		
		List<String> teamnames = res.path("teams.name");
		
		for(String teamname : teamnames)
		{
			System.out.println(teamname);
		}
		
	}
	
	
}

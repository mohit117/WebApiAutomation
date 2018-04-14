package org.api.testcases;
import static io.restassured.RestAssured.*;

import java.util.Map;

import org.api.base.TestConfig;
import org.junit.Test;

import io.restassured.response.Response;

public class GpathJsonTest extends TestConfig {
	
	//Finding details with find method of Gpath
	@Test
	public void extractMapOfElementsWithFind()
	{
		Response res = get("competitions/426/teams");
		
		Map<String,?> allTeamDataForSingleTeam = res.path("teams.find{ it.name == 'Leicester City FC' }");
		
		System.out.println(allTeamDataForSingleTeam);
	}
	
	
	@Test
	public void extractSingleValueWithFind()
	{
		
		Response res = get("teams/66/players");
		String certainPlayer = res.path("players.find{ it.jerseyNumber == 20 }.name");
		System.out.println(certainPlayer);
	}
	

}

package org.api.testcases;

import static io.restassured.RestAssured.given;

import org.api.base.TestConfig;
import org.junit.Test;
//import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;


public class solrTest extends TestConfig{

	@Test
	public void myFirstSOLRTest()
	{
		given().spec(solr_requestSpec).log().all().
		when().get("/g/womens-activewear/N-bwo3xD1noph0").
		then().log().all().statusCode(200);
	}
	
	
	@Test
	public void gettingResponseAsStringTest()
	{
		String responseBody = given().spec(solr_requestSpec).log().all().
		when().get("/g/womens-activewear/N-bwo3xD1noph0").asString();
		
		System.out.println(responseBody);
		
	}
	
}


//   /g/womens-activewear/N-bwo3xD1noph0 ///g/womens-dresses/N-bwo3xD1nnujc
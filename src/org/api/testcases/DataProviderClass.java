package org.api.testcases;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
//import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.*;
public class DataProviderClass {
	 
	/*
	 For this example, I am using TestNG to use DataProvider 
	 */

	/*Note that we are passing only 1 value(only search term), still we need to create data provider
	with 2 dimentional array object. We cant create array data as Object[] data = new Object[]
	*/
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = new Object[4][1];
		data[0][0] = "pants";
		data[1][0] = "shirts";
		data[2][0] = "jeans";
		data[3][0] = "sdsdfsdfsdfsdfsdfsdf";
				
		return data;
	}
	
	
	/*Below test will execute using test data provided by data provider and will print the response for all requests
	 We are passing 3 values hence it will execute 3 times
	 */
	
	@Test(dataProvider = "getData")
	public void hitSolrAPIwithDifferetData(String searchTermFromDataProvider)
	{
		System.out.println("Getting response for " + searchTermFromDataProvider);
		
		String responseBody = given().
				log().all().
				pathParam("searchTerm", searchTermFromDataProvider).
				queryParam("Ntt", searchTermFromDataProvider).									
				when().
				get("https://search-api.jcpenney.com/v1/s/{searchTerm}").
				asString();
		
		System.out.println(responseBody);
	}
	
	//validate status codes for all the requests sent for each test data
	
	@Test(dataProvider = "getData")
	public void validateStatusCode(String searchTermFromDataProvider)
	{
		given().
		log().all().
		pathParam("searchTerm", searchTermFromDataProvider).
		queryParam("Ntt", searchTermFromDataProvider).									
		when().
		get("https://search-api.jcpenney.com/v1/s/{searchTerm}").
		then().statusCode(200);
	}
	
	/*
	 Validating below points
	 status code
	 content type
	 If above passes then extracting list of pps for all search term from data provider
	 */
	@Test(dataProvider = "getData")
	public void getPPNames(String searchTermFromDataProvider)
	{
		Response res = given().
				log().all().
				pathParam("searchTerm", searchTermFromDataProvider).
				queryParam("Ntt", searchTermFromDataProvider).									
				when().
				get("https://search-api.jcpenney.com/v1/s/{searchTerm}").
				then().
				statusCode(200).
				contentType(ContentType.JSON).
				extract().
				response();

		List<String> ppIds = res.path("organicZoneInfo.products.ppId");
		//Assert.assertNull(ppIds); //not failing for invalid searchterm where ppid list is empty
		
		if(ppIds.isEmpty())
		{
			System.out.println("List of PPs is empty for search term " + searchTermFromDataProvider);
		}
		else
		{
			System.out.println("List of pps for " +searchTermFromDataProvider);
			for(String ppid : ppIds)
			{
				System.out.println(ppid);
			}
		}
	}
	
	//Failing this test cases purposely for reporting
	@Test
	public void testToFail()
	{
		given().
		log().all().
		pathParam("searchTerm", "pants").
		queryParam("Ntt", "pants").									
		when().
		get("https://search-api.jcpenney.com/v1/s/{searchTerm}").
		then().statusCode(400);
	}
	
	@Test
	public void AnothertestToFail()
	{
		given().
		log().all().
		pathParam("searchTerm", "pants").
		queryParam("Ntt", "pants").									
		when().
		get("https://search-api.jcpenney.com/v1/s/{searchTerm}").
		then().statusCode(200).contentType(ContentType.XML);
	}
	
}

package org.api.base;

//import org.testng.annotations.BeforeClass;

import org.junit.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



public class TestConfig {

	public static RequestSpecification videoGame_requestSpec;
	public static RequestSpecification football_requestSpec;
	public static RequestSpecification solr_requestSpec; 
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void setup()
	{
		
		//request spec for video game api
		videoGame_requestSpec = new RequestSpecBuilder().
								setBaseUri("http://localhost").
								setPort(8080).
								setBasePath("/app/").addHeader("Content-Type", "application/json").
								addHeader("Accept", "application/json").
								build();
		
			
		
		//request spec for football api
		football_requestSpec = new RequestSpecBuilder().
								setBaseUri("https://api.football-data.org/").
								setBasePath("/v1/").
								addHeader("X-Auth-Token", "").
								addHeader("X-Response-Control", "minified").
								build();
		
		//request spec for SOLR api
		solr_requestSpec = new RequestSpecBuilder().
							setBaseUri("https://search-api.jcpenney.com/").
							setBasePath("v1/search-service").
							addHeader("Content-Type", "application/json").
							addHeader("Accept", "application/json").build();
		
		//https://services.loadtest1a.dp-dev.jcpcloud2.net/
		//https://services.integration3a.dp-dev.jcpcloud2.net/
		//
		
		//Assigning request spec for video game api so that the specified configuration applied to all video game test
		RestAssured.requestSpecification = football_requestSpec;
		
		
		responseSpec = new ResponseSpecBuilder().
										expectStatusCode(200).
										expectContentType(ContentType.JSON).
										build();
		RestAssured.responseSpecification = responseSpec;
		
		
		/*
		
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8080;
		RestAssured.basePath="/app/";
		
		
		//Adding a request specifications which will be applicable to all the test cases
		RequestSpecification requestspec = new RequestSpecBuilder().
				addHeader("Content-Type", "application/json").
				addHeader("Accept", "application/json").
				build();
	
	    //Below line of code will set the restassured to use above request specifications for all the test cases.
		
		RestAssured.requestSpecification = requestspec;
		
		
		
		//Adding a response specifications which will be applicable to all the test cases
		ResponseSpecification responsespec = new ResponseSpecBuilder().
												expectStatusCode(200).
												build();
		
		 //Below line of code will set the restassured to use above response specifications for all the test cases.
		RestAssured.responseSpecification = responsespec;
		
		*/
	}
	
}

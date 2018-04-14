package org.api.testcases;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Map;

import org.api.base.TestConfig;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
//import org.testng.annotations.Test;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class SolrXGNSTest extends TestConfig{

	//Hiiting first seaech api url using path and query parameters

	@Test
	public void gettingResponseAsStringTestForXGNs()
	{
		String responseBody = given().
				spec(solr_requestSpec).log().all().
				pathParam("searchTerm", "shirts").
				queryParam("Ntt", "shirts").									
				when().
				get("/s/{searchTerm}").
				asString();

		System.out.println(responseBody);
	}

	//extracting all pps from the response
	@Test
	public void extractingAllPPs()
	{
		Response res = given().
				spec(solr_requestSpec).log().all().
				pathParam("searchTerm", "shirts").
				queryParam("Ntt", "shirts").									
				when().
				get("/s/{searchTerm}").
				then().
				contentType(ContentType.JSON).
				extract().
				response();

		List<String> ppIds = res.path("organicZoneInfo.products.ppId");

		for(String ppid : ppIds)
		{
			System.out.println(ppid);
		}

	}


	//extracting all facetIds 

	@Test
	public void extractingAllFacetIds()
	{
		Response res = given().
				spec(solr_requestSpec).log().all().
				pathParam("searchTerm", "shirts").
				queryParam("Ntt", "shirts").									
				when().
				get("/s/{searchTerm}").
				then().
				contentType(ContentType.JSON).
				extract().
				response();

		List<Integer> facetIds = res.path("facets.facetList.facetId");

		for(int facetId : facetIds)
		{
			System.out.println(facetId);
		}

	}

	//extracting all Facet Types 

	@Test
	public void extractingAllFacetType()
	{
		Response res = given().
				spec(solr_requestSpec).log().all().
				pathParam("searchTerm", "shirts").
				queryParam("Ntt", "shirts").									
				when().
				get("/s/{searchTerm}").
				then().
				contentType(ContentType.JSON).
				extract().
				response();

		List<String> facetTypes = res.path("facets.facetList.facetType");

		for(String facetType : facetTypes)
		{
			System.out.println(facetType);
		}

	}


	//Way for Checking if first facet name is store availablility of not
	@Test
	public void validateFirstFacetNameUsingJava()
	{
		Response res = given().
				spec(solr_requestSpec).log().all().
				pathParam("searchTerm", "shirts").
				queryParam("Ntt", "shirts").									
				when().
				get("/s/{searchTerm}");

		List<String> facetnames = res.path("facets.facetList.facetName");
		//System.out.println(facetnames);
		if(facetnames.get(0).equals("Store Availability"))
		{
			System.out.println("First facet is store availability");
		}
		else
		{
			System.out.println("First facet is not store availability");
		}

	}

	//another way********using HAMECREST ASSERTIONS******** for Checking if first facet name is store availablility of not
	@Test
	public void validateFirstFacetNameUsingHemcrest()
	{
		given().
		spec(solr_requestSpec).log().all().
		pathParam("searchTerm", "shirts").
		queryParam("Ntt", "shirts").									
		when().
		get("/s/{searchTerm}").
		then().
		body("facets.facetList[0].facetName", equalTo("Store Availability"));

	}

	//another way********using JUNIT ASSERTIONS******** for Checking if first facet name is store availablility of not
	@Test
	public void validateFirstFacetNameUsingJunitAssert()
	{
		Response res = given().
				spec(solr_requestSpec).log().all().
				pathParam("searchTerm", "shirts").
				queryParam("Ntt", "shirts").									
				when().
				get("/s/{searchTerm}");

		List<String> facetnames = res.path("facets.facetList.facetName");
		//System.out.println(facetnames);
		Assert.assertEquals(facetnames.get(0), "Store Availability");


	}


	//validating imageinfo for a single pp
	@Test
	public void validateImageInfoForSinglePP()
	{
		Response res = given().spec(solr_requestSpec).log().all().
				pathParam("searchTerm", "shirts").
				queryParam("Ntt", "shirts").									
				when().
				get("/s/{searchTerm}");

		String imageInfo = res.path("organicZoneInfo.products.find{it.ppId = 'ppr5007250974'}.imagesInfo").toString();
		if(imageInfo.isEmpty())
		{
			System.out.println("ImageInfo is blank");

		}
		else
		{
			System.out.println(imageInfo);
		}
	}


	//*****To get all the imageinfo details of all the PPs using Java List and Maps**********************

	@Test
	public void getImageInfoAttributeForAllPPs()
	{
		Response res = given().
				spec(solr_requestSpec).log().all().
				pathParam("searchTerm", "shirts").
				queryParam("Ntt", "shirts").									
				when().
				get("/s/{searchTerm}");

		List<Map<String,Object>> allimageinfo = res.path("organicZoneInfo.products");
		System.out.println(allimageinfo.size());

		for(Map<String, Object> map : allimageinfo)
		{
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if(key.equalsIgnoreCase("imagesInfo"))
				{
					System.out.println(key);
					System.out.println(value);
				}


			}
		}

	}

	//*****To get all the imageinfo details of all the PPs using Hemcrest Matchres**********************
	@Test
	public void validateImageInfoUsingHemcrest()
	{

	}

	//********Validate the channel name of the response using JAVA****************
	@Test
	public void validateChannelNameUsingJava()
	{
		Response res = given().spec(solr_requestSpec).
				pathParam("searchTerm", "shirts").
				queryParam("Ntt", "shirts").
				when().
				get("/s/{searchTerm}");

		String channelName = res.path("channelName");
		if(channelName.equalsIgnoreCase("SOLR-API"))
		{
			System.out.println("Channel Name is matching and value is "+channelName);
		}
		else
		{
			System.out.println("Channel name is not matching" );
		}
	}

	//********Validate the channel name of the response using HEMCREST MATCHERS****************
	@Test
	public void validateChannelNameUsingHamcrest()
	{
		given().spec(solr_requestSpec).
		pathParam("searchTerm", "shirts").
		queryParam("Ntt", "shirts").
		when().
		get("/s/{searchTerm}").
		then().
		body("channelName", equalTo("SOLR-API"));
	}
	
	

	//********Validate the channel name of the response using JUNIT ASSERT****************
	@Test
	public void validateChannelNameUsingJunitAssert()
	{
		Response res = given().spec(solr_requestSpec).
				pathParam("searchTerm", "shirts").
				queryParam("Ntt", "shirts").
				when().
				get("/s/{searchTerm}");

		String channelName = res.path("channelName");
		Assert.assertEquals("SOLR-API", channelName);

	}


	//**************validate if a specific sort option is present inside a Sort list or not 
	//using USE OF hasItem() METHOD ********************************
	@Test
	public void validateSortNameInsideListUsingHamcrest()
	{
		given().spec(solr_requestSpec).log().all().
		pathParam("searchTerm", "shirts").
		queryParam("Ntt", "shirts").
		when().
		get("/s/{searchTerm}").
		then().
		body("sortOptions.name", hasItem("price low - high"));
	}

	
	
	
	//**************validate if a specific sort option is present inside a Sort list or not 
		//using USE OF hasItem() METHOD ********************************
		@Test
		public void validateSortNameInsideListUsingJunitAssert()
		{
			Response res = given().spec(solr_requestSpec).log().all().
			pathParam("searchTerm", "shirts").
			queryParam("Ntt", "shirts").
			when().
			get("/s/{searchTerm}");
			
			List<String> sortOptions = res.path("sortOptions.name");
			Assert.assertNotNull(sortOptions); //to make sure that list is not null
			//Assert.assertNull(sortOptions);  // to make sure that list is null. If not null then test will fail
			Assert.assertEquals(sortOptions.get(3), "price low - high");
		}

	
		
		
	/*	In the above method, the probelem is, we should know already the index of the sort option we want to check.
		To overcome this issue, we should use hasItem method of hemcrest as below*/
		
		@Test
		public void validateSortNameInsideListUsingHemcreast()
		{
			Response res = given().spec(solr_requestSpec).log().all().
			pathParam("searchTerm", "shirts").
			queryParam("Ntt", "shirts").
			when().
			get("/s/{searchTerm}");
			
			List<String> sortOptions = res.path("sortOptions.name");
			assertThat(sortOptions, hasItem("price low - high"));
		}
		
	//**************validate if a specific sort option is present inside a Sort list or not 
	//using JAVA ****************************************************
		@Test
		public void validateInfoPresentInsideListUsingJava()
		{
			Response res = given().spec(solr_requestSpec).log().all().
			pathParam("searchTerm", "shirts").
			queryParam("Ntt", "shirts").
			when().
			get("/s/{searchTerm}");
			
			List<String> sortOptions = res.path("sortOptions.name");
			System.out.println(sortOptions);
			
			for(String soption : sortOptions)
			{
				
				if(soption.equalsIgnoreCase("price low - high"))
				{
					System.out.println("Price low to high sort option is available");
				}
				
			}
			
		}
	
	//Print sort option where order of sort option is 2 using Gpath find method

	@Test

	public void getAllSortDetailsBasedOnKeyOrOrderNumber()
	{
		Response res= given().spec(solr_requestSpec).log().all().
				pathParam("searchTerm", "pants").queryParam("Ntt", "pants").
				when().get("/s/{searchTerm}");


		//Map<String,?> allSortDetailsForSingleSort = res.path("sortOptions.find{ it.order == 3 }");
		Map<String,?> allSortDetailsForSingleSort = res.path("sortOptions.find{ it.key == 'NA' }");		
		System.out.println(allSortDetailsForSingleSort);


	}

	//Validate the order of all the sort options
	@Test
	public void validateOrderOfSortOptions()
	{
		
	}
	
	//Print product details for single pp where oppid is given using Gpath find method

	@Test
	public void validateProductsWithPPID()
	{
		Response res= given().spec(solr_requestSpec).log().all().
				pathParam("searchTerm", "pants").queryParam("Ntt", "pants").
				when().get("/s/{searchTerm}");


		Map<String,?> allSortDetailsForSinglePP = res.path("organicZoneInfo.products.find { it.ppId == 'pp5007530807' }");	
		Assert.assertNotNull(allSortDetailsForSinglePP);
		System.out.println(allSortDetailsForSinglePP);



	}


	@Test
	public void actualEndpoint() {

		Response response = get("https://services.integration3a.dp-dev.jcpcloud2.net/v1/search-service/s/pants?Ntt=pants");

		Map<String, ?> allDetailsForSinglePP = response.path("organicZoneInfo.products.find { it.ppId == 'pp5007530807' }");
		System.out.println(allDetailsForSinglePP);
	}


	//Getting the name and minipdp status of the single pp whoes brand is white mark
	//It will return first pp which matches with the brand when more than 1 items are available with the same brand

	@Test
	public void validateEnableMiniPDP()
	{
		Response res = given().spec(solr_requestSpec).log().all().
				pathParameter("searchTerm", "pants").
				queryParam("Ntt", "pants").
				when().
				get("/s/{searchTerm}");

		String ppname = res.path("organicZoneInfo.products.find {it.brand == 'white mark'}.name");
		Boolean minipdpStatus = res.path("organicZoneInfo.products.find {it.brand == 'white mark'}.enableMiniPDP");
		//getting value of thumbnailImageId of a pp which is present inside imagesInfo array of a products array
		String imagesInfo = res.path("organicZoneInfo.products.find {it.brand == 'white mark'}.imagesInfo.thumbnailImageId");
		//NOT WORKING BELOW
		String availabilityStatus = res.path("organiczoneInfo.products.find {it.brand == 'white mark'}.availabilityStatus.message");

		System.out.println(ppname);
		System.out.println(minipdpStatus);
		System.out.println(imagesInfo);
		System.out.println(availabilityStatus);

	}


	//Getting all pps for which minipdp is true
	@Test
	public void getListOfAllPPWhereMiniPDPisTrue()
	{
		Response res = given().spec(solr_requestSpec).log().all().pathParam("searchTerm", "pants").
				queryParam("Ntt", "pants").
				when().get("/s/{searchTerm}");

		List<String> listofPPs= res.path("organicZoneInfo.products.findAll{ it.enableMiniPDP == true}.ppId");

		if(listofPPs.isEmpty())
		{
			System.out.println("No items found with mini pdp as true");
		}
		else
		{
			for(String pp : listofPPs )
			{
				System.out.println(pp);
			}
		}
	}


	//Getting list of pps for which minipdp is true

	@Test
	public void validateSinglePDPForMiniPDP()
	{
		Response res = given().spec(solr_requestSpec).log().all().pathParam("searchTerm", "pants").
				queryParam("Ntt", "pants").
				when().get("/s/{searchTerm}");

		String pdpurl = res.path("organicZoneInfo.products.find {it.enableMiniPDP == true}.pdpUrl");
		System.out.println(pdpurl);
		if(pdpurl.contains("pTmplType=regular") || pdpurl.contains("pTmplType=sephora"))
		{
			System.out.println("This PP is haivng mini PDP as true");
		}


		//String str = pdpurl.substring(pdpurl.lastIndexOf("/") + 1, url.indexOf("?"));

		/*List<String> listofPPs= res.path("organicZoneInfo.products.findAll{ it.currentMin > 50}.ppId");

			for(String pp : listofPPs )
			{
				System.out.println(pp);
			}*/
	}

	//NEED TO WORK MORE ON THIS -------------------------Validating the template type of pps for which mini pdp is set to true
	//and if it is false then get the pp id of the pp from pdp url
	// need to work on how to get the pdp ids from the pdp url
	@Test
	public void getListOfPPsWithMiniPdpAsTrue()
	{

		Response res = given().spec(solr_requestSpec).log().all().pathParam("searchTerm", "pants").
				queryParam("Ntt", "pants").
				when().get("/s/{searchTerm}");

		List<String> listofPDPURLs= res.path("organicZoneInfo.products.findAll {it.enableMiniPDP == true}.pdpUrl");

		for(String pdpurl : listofPDPURLs)
		{
			if(pdpurl.contains("pTmplType=regular") || pdpurl.contains("pTmplType=sephora"))
			{
				System.out.println("This PP is haivng mini PDP as true");
			}


		}

	}

	//getting all the pps for which price is greater than 50

	@Test
	public void getListOfPPsForPriceMoreThan50()
	{
		Response res = given().spec(solr_requestSpec).log().all().pathParam("searchTerm", "pants").
				queryParam("Ntt", "pants").
				when().get("/s/{searchTerm}");

		List<String> listofPPs= res.path("organicZoneInfo.products.findAll{ it.currentMin > 50}.ppId");

		for(String pp : listofPPs )
		{
			System.out.println(pp);
		}
	}


	//Get names of all pps for which price is greater than 20 and brand is worthington
	@Test
	public void fetListOfPPsForPriceMoreThan20AndBrandIsWorthington()
	{
		Response res = given().spec(solr_requestSpec).log().all().pathParam("searchTerm", "pants").
				queryParam("Ntt", "pants").
				when().get("/s/{searchTerm}");
		
		List<String> listofPPs= res.path(
							"organicZoneInfo.products.findAll{ it.currentMin > 20}.findAll{it.brand == 'worthington'}.name");
		
		if(listofPPs.isEmpty())
		{
			System.out.println("There are no items mathing with the given criteria");
		}
		else
		{

			for(String pp : listofPPs )
			{
				System.out.println(pp);
			}
		}
	}
	
	
	//Get names of all pps for which price is greater than 20 and brand is worthington
		@Test
		public void fetListOfSlaePPsAndBrandIsWorthington()
		{
			Response res = given().spec(solr_requestSpec).log().all().pathParam("searchTerm", "pants").
					queryParam("Ntt", "pants").
					when().get("/s/{searchTerm}");

			List<String> listofPPs= res.path(
					"organicZoneInfo.products.findAll{ it.currentPriceLabel == 'sale'}.findAll{it.brand == 'worthington'}.name");

			if(listofPPs.isEmpty())
			{
				System.out.println("There are no items mathing with the given criteria");
			}
			else
			{

				for(String pp : listofPPs )
				{
					System.out.println(pp);
				}
				
			}
			
		}
		
		
		//************Asserting a List using assertThat method to validate if currentprice label contains original************
		
		@Test
		public void assertAList()
		{
			Response res = given().spec(solr_requestSpec).log().all().pathParam("searchTerm", "pants").
					queryParam("Ntt", "pants").
					when().get("/s/{searchTerm}");
			
			List<String> pricelabels = res.path("organicZoneInfo.products.currentPriceLabel");
			assertThat(pricelabels, hasItem("original"));
			
			
		}
		
		//************Asserting a List using body to validate if currentprice label contains original*************************************
		
		@Test
		public void assertAListUsingBodyMethod()
		{
			given().spec(solr_requestSpec).log().all().pathParam("searchTerm", "pants").
			queryParam("Ntt", "pants").
			when().get("/s/{searchTerm}").
			then().body("organicZoneInfo.products.currentPriceLabel", hasItem("original"));


		}
		
		//Asseting a List using assertThat method to validate multiple currentprice labels
		@Test
		public void assertMultipleValuesPresentInList()
		{
			Response res = given().spec(solr_requestSpec).log().all().pathParam("searchTerm", "pants").
					queryParam("Ntt", "pants").
					when().get("/s/{searchTerm}");
			
			List<String> pricelabels = res.path("organicZoneInfo.products.currentPriceLabel");
			assertThat(pricelabels, hasItems("original", "sale"));
		}
		
		//Asseting a List using body method to validate multiple currentprice labels
		@Test
		public void assertAListForMultipleValuesUsingBodyMethod()
		{
			given().spec(solr_requestSpec).log().all().pathParam("searchTerm", "pants").
			queryParam("Ntt", "pants").
			when().get("/s/{searchTerm}").
			then().body("organicZoneInfo.products.currentPriceLabel", hasItems("original", "sale"));


		}
}



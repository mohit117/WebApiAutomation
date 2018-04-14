package org.web.testcases;

import static com.jayway.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Api {

	static String  output;
	
	@Test
	public void getResponse()
	{
		output="";
		try {

			URL url = new URL("https://search-api.jcpenney.com/v1/search-service-autosuggest/predictivesearch?q=small&&channel=desktop&responseGroup=products&shipToCountry=US");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			
			StringBuffer mohitString = new StringBuffer();
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				
				mohitString.append(output);
			}
			
			System.out.println("the mohit string is " + mohitString);
			output = mohitString.toString();
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

	}
	
	
		
	@Test(dependsOnMethods = { "getResponse" })
	public void validatePricePromoAutoSuggest() {
		
		JSONArray jArr1 = new JSONArray(output);
		JSONObject jObj1 = jArr1.getJSONObject(0);
		JSONArray jArr2 = jObj1.getJSONArray("termsuggestions");
		for (int i = 0; i < jArr2.length(); i++) {
			JSONObject product = jArr2.getJSONObject(i);
			JSONArray proArray = product.getJSONArray("products");
			for (int j = 0; j < proArray.length(); j++) {
				JSONObject jObj2 = proArray.getJSONObject(j);
				//Assert.assertEquals(jObj2.has("totalColorCount"), true);
				Assert.assertEquals(jObj2.has("totalColorCount"), false, "Attribute is not present");
			}

		}
	}

}

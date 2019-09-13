import static org.hamcrest.CoreMatchers.equalTo;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import files.Payload;
import files.Base;
import files.resources;

public class AddANDDeleteAPI {

	@Test
		public void createPlaceAndDeleteAPI() throws IOException
		{
			
			FileInputStream fis=new FileInputStream("/Users/pragya.bharti/Documents/JSWorkSpace/RestAPI/src/files/env.properties");
			Properties prop =new Properties();
			prop.load(fis);		
			RestAssured.baseURI=prop.getProperty("HOST");			
			
			// Create a place =response (place id)
			Response res=given().
			
			queryParam("key","qaclick123").
			body(Payload.getPayload()).
			when().
			post(resources.getResources()).
			then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			body("status",equalTo("OK")).extract().response();
			
			JsonPath js=Base.rawToJSON(res);
			String place=js.get("place_id");
			
									
			// delete Place = (Request - Place id)	
			
			given().queryParam("key", "qaclick123").body("{"+
					  "\"place_id\": \""+place+"\""+
					  "}")
			.when().post("/maps/api/place/delete/json").then().assertThat().contentType(ContentType.JSON).and()
			.statusCode(200)
			.body("status", equalTo("OK"));                                                                                                                                               
			
			
			
			
			
		}
}

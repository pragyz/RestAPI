import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import files.Base;
import files.Payload;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAPI {

	@Test
	public void getAPI() throws IOException
	{
		
		FileInputStream fis=new FileInputStream("/Users/pragya.bharti/Documents/JSWorkSpace/RestAPI/src/files/env.properties");
		Properties prop =new Properties();
		prop.load(fis);		
		RestAssured.baseURI=prop.getProperty("GoogleHOST");	
			
		//get response from google api
		Response res=given().
				param("query","restaurants+in+Sydney").
				param("key", prop.getProperty("MYAPI")).
				when().get("maps/api/place/textsearch/json").
				then().assertThat().statusCode(200).contentType(ContentType.JSON).and().
				extract().response();
		
		JsonPath js=Base.rawToJSON(res);
		String name=js.get("results[0].name");
		System.out.println(name);
		
		
		
}
}

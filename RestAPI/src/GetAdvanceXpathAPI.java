import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import files.Base;

public class GetAdvanceXpathAPI {

	//https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Sydney&key=AIzaSyCDHBz-AMIXcZU8fpiDlg9hBPfmzbpIR70
	@Test
	public void GetAlltheObjectArrayAPI() throws IOException {
	
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream("/Users/pragya.bharti/Documents/JSWorkSpace/RestAPI/src/files/env.properties");
		Properties prop =new Properties();
		prop.load(fis);		
				
		RestAssured.baseURI="https://maps.googleapis.com";
		
		Response res=given().log().all().
				param("query","restaurants+in+Sydney").
				param("key", prop.getProperty("MYAPI")).
				when().get("maps/api/place/textsearch/json").
				then().assertThat().statusCode(200).contentType(ContentType.JSON).and().
				body("results[0].name", equalTo("Bea Restaurant")).extract().response();
		
		//System.out.println(res.asString());
		JsonPath js=Base.rawToJSON(res);
		int count=js.get("results.size()");
		for(int i=0;i<count;i++) {
		String name=js.get("results["+i+"].name");
		System.out.println(name);
		}
		

	}
	
	//Path parameter - .param()  - passing data as a parameter in end point
	//query parameter - .queryparam() - passing data as a segment directly to the URL
	

}

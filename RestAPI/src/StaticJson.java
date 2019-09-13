import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.Base;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StaticJson {
	
	
	@Test
	public void AddBookTest() throws IOException {
			
		RestAssured.baseURI="http://216.10.245.166";
			
		Response res=given().header("Content-Type","application/json").
		body(generateStringFromResource("/Users/pragya.bharti/Desktop/StaticPayload.json.rtf")).
		when().
		post("Library/Addbook.php").
		then().
		assertThat().statusCode(200).extract().response();
		
		JsonPath js=Base.rawToJSON(res);
		String id=js.get("ID");
		System.out.println(id);		
		
		System.out.println("payload: "+Payload.DeleteBook(id));
		
		given().body(Payload.DeleteBook(id)).when().post("/Library/DeleteBook.php").then().
				assertThat().statusCode(200).extract().response();		
		
		}
	
	public static String generateStringFromResource(String path) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	

}

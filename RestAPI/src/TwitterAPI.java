import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;

public class TwitterAPI {
	String id;
	
	String c_Key="8KxqCAK5iDnRQRNLU7aUhaarm";
	
	String c_Secret="RpJCECei90gCQr0OQCuLmQ8RhUBk0md5KPSWELXnI6JSHchf2H";
	
	String access_tok="1162246329080225794-2IDHvsEhVz9VLO3G6sjmJrpiyLcJOl";
	
	String access_Secret="gXuD9j0CKFKxaDhLpRw0eC4obXyRRcIsGBchXjjAsfGFr";	

	
	@Test	
	public void GetTweetAPI() {
		
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		Response res=given().auth().oauth(c_Key, c_Secret, access_tok, access_Secret).queryParam("count", "4")
				.when().get("/home_timeline.json").then().extract().response();	
		
		String response=res.asString();
		JsonPath jspath=new JsonPath(response);		

		List status=jspath.get("text");
		System.out.println("GetAPI: "+status);	
	}
	
	@Test	
	public void createTweet() {
		
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		Response res=given().auth().oauth(c_Key, c_Secret, access_tok, access_Secret).queryParam("status", "Hi Pragya121 Aug")
				.when().post("/update.json").then().extract().response();	
		
		String response=res.asString();
		JsonPath jspath=new JsonPath(response);
		System.out.println(response);
		System.out.println("Tweet which is craeted with automation is below");
		 id=jspath.get("id").toString();
		System.out.println("CreateAPI: "+id);
		
		
	}
		
	@Test
	public void E2E()
	{
		createTweet();
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		Response res=given().auth().oauth(c_Key, c_Secret, access_tok, access_Secret)
		.when().post("/destroy/"+id+".json").then().extract().response();
	
	String response =res.asString();	
	JsonPath js= new JsonPath(response);
	System.out.println(response);
	System.out.println("Tweet which got deleted with automation is below");
	String text2=js.get("text");	
	System.out.println("DeleteAPI:" +text2);
	
	Boolean tr=js.get("truncated");
	System.out.println(tr);
	
	
	}
	
	
	
}

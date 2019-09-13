import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Base;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

//Parameterisation using data provider
public class DynamicJson {
	String id;
@Test(dataProvider = "bookData")
public void AddBookTest(String isbn, String aisle) {
		
	RestAssured.baseURI="http://216.10.245.166";
		
	Response res=given().
	body(Payload.AddBook(isbn,aisle)).
	when().
	post("Library/Addbook.php").
	then().
	assertThat().statusCode(200).extract().response();
	
	JsonPath js=Base.rawToJSON(res);
	id=js.get("ID");
	System.out.println(id);		
	
	System.out.println("payload: "+Payload.DeleteBook(id));
	
	given().body(Payload.DeleteBook(id)).when().post("/Library/DeleteBook.php").then().
			assertThat().statusCode(200).extract().response();
	
	
	}

@DataProvider(name="bookData")
public Object[][] getData() {	
	return new Object[][]{{"Pragya1","1001"},{"Akshay1","1002"},{"Mona1","1003"}};
}


	
	
	
}






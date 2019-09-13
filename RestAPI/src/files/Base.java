package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Base {

		public static JsonPath rawToJSON(Response r) {
			
			String resp=r.asString();
			JsonPath js=new JsonPath(resp);
			
			return js;
		}
		
		public static XmlPath rawToXML(Response r) {
			
			String resp=r.asString();
			XmlPath x=new XmlPath(resp);
			
			return x;
		}
		
	}



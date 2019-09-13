package files;

public class Payload {

	public static String getPayload() {
		String data="{"+
				  "\"location\": {"+
				    "\"lat\": -33.8669710,"+
				    "\"lng\": 151.1958750"+
				  "},"+
				  "\"accuracy\": 50,"+
				  "\"name\": \"Google Shoes!\","+
				  "\"phone_number\": \"(02) 9374 4000\","+
				  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				  "\"types\": [\"shoe_store\"],"+
				  "\"website\": \"http://www.google.com.au/\","+
				  "\"language\": \"en-AU\""+
				"}";
		
		return data;
	}
	
	
	public static String AddBook(String isbn, String aisle) {
		String pload="{\n" + 
				"\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\n" + 
				"\"isbn\":\""+isbn+"\",\n" + 
				"\"aisle\":\""+aisle+"\",\n" + 
				"\"author\":\"John foe\"\n" + 
				"}\n" + 
				" \n" + 
				"";
		
		return pload;
		
	}
	
	public static String DeleteBook(String id) {
		
		String payLoad="{\n" + 
						" \n" + 
						"\"ID\" : \""+id+"\"\n" + 
						" \n" + 
						"} \n" + 
						"";
		return payLoad;

	}

	}



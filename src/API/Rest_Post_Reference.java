package API;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Rest_Post_Reference {

	public static void main(String[] args) {
		// Declare the base URL
		RestAssured.baseURI = "https://reqres.in/";

		// Declare the request body string variable
		String requestBody = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"leader\"\r\n" + "}";

		// Declare the expected result
		JsonPath jspRequest = new JsonPath(requestBody);
		String req_name = jspRequest.getString("name");
		String req_job = jspRequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 11);

		// Declare given, when and then method
		String responseBody = given().header("Content-Type", "application/json").body(requestBody).when()
				.post("api/users").then().extract().response().asString();

		// Create of an Object of JSON path to parse response body
		JsonPath jspResponse = new JsonPath(responseBody);
		//System.out.println(responseBody);

		String res_name = jspResponse.getString("name");
		String res_job = jspResponse.getString("job");
		System.out.println(res_name);
		System.out.println(res_job);
		String res_createdAt = jspResponse.getString("createdAt");
		res_createdAt = res_createdAt.substring(0, 11);
		System.out.println(res_createdAt);

		// Validate the response body parameter
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_createdAt, expecteddate);

	}

}

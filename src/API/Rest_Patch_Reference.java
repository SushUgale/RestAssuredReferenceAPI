package API;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Rest_Patch_Reference {

	public static void main(String[] args) {
		// Declare the base URL
		RestAssured.baseURI = "https://reqres.in/";

		// Declare the request body string variable
		String requestBody = "{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"zion resident\"\r\n" + "}";
		// Declare the expected result
		JsonPath jspRequest = new JsonPath(requestBody);
		String req_name = jspRequest.getString("name");
		String req_job = jspRequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 11);

		// Declare given, when and then method
		String responseBody = given().header("Content-Type", "application/json").body(requestBody).when()
				.patch("api/users/2").then().extract().response().asString();
		// System.out.println(responseBody);

		// Create of an Object of JSON path to parse response body
		JsonPath jspResponse = new JsonPath(responseBody);

		String res_name = jspResponse.getString("name");
		String res_job = jspResponse.getString("job");
		System.out.println(res_name);
		System.out.println(res_job);
		String res_updatedAt = jspResponse.getString("updatedAt");
		res_updatedAt = res_updatedAt.substring(0, 11);
		System.out.println(res_updatedAt);

		// Validate the response body parameter
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_updatedAt, expecteddate);

	}

}

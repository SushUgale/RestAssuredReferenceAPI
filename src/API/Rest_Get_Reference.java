package API;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Rest_Get_Reference {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://reqres.in/";

		String responseBody = given().header("Content-Type", "application/json").body("requestBody").when()
				.get("api/users/2").then().extract().response().asString();
		System.out.println(responseBody);

	}

}

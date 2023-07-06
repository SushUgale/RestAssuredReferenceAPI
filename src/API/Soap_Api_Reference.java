package API;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;

public class Soap_Api_Reference {

	public static void main(String[] args) {
		// Declare the base URl
		RestAssured.baseURI = "https://www.dataaccess.com/";

		// Declare requestBody
		String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n" + "    </NumberToWords>\r\n" + "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n";

		// Extract Response Body

		String ResponseBody = given().header("Content-Type", "text/xml; charset=utf-8").body(requestBody).when()
				.post("webservicesserver/NumberConversion.wso").then().extract().response().asString();

		System.out.println(ResponseBody);

		// Parse the response body
		XmlPath xmlResponse = new XmlPath(ResponseBody);
		String responseParameter = xmlResponse.getString("NumberToWordsResult");
		System.out.println(responseParameter);

		// Validate response body
		Assert.assertEquals(responseParameter, "one hundred ");
	}

}

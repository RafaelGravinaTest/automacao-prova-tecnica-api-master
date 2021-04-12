package Utils;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static io.restassured.RestAssured.given;


public class RestMethods{
	private URL url;
	private String requestBody;
	private Map<String, String> headers;
	public Report reports = TestRule.getReport();
	private Response response;
	private ExtentTest reportRequest;
	private ExtentTest reportResponse;
	
	public RestMethods() {
		headers = new HashMap<String, String>();
		reportRequest = reports.createChildStart("Request");
		reportResponse = reports.createChildStart("Response");
	}
	
	public void setUrl(String endpoint) {
		URL url = null;
		try {
			url = new URL(endpoint);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		this.url = url;
	}
	
	public void setBody(String body) {
		this.requestBody = body;
	}
	
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
		StringBuilder builder = new StringBuilder();
		for(Entry<String, String> header : headers.entrySet()) {
			builder.append(header.getKey()+" = "+header.getValue()+"<br>"); 
		}
	}
	
	private void logPrintRequest(String requestMethod) {
		StringBuilder builder = new StringBuilder();
		builder.append("<br>Metodo Requisicao: <br>"+requestMethod);
		builder.append("<br>URL: <br>"+this.url);
		builder.append("<br>Headers: <br>");
		for(Entry<String, String> headers : this.headers.entrySet()) {
			builder.append(headers.getKey()+" = "+headers.getValue()+"<br>"); 
		}
		builder.append("Body:<p>" + this.requestBody + "</p>");
		reports.childLogInfo(reportRequest, "Request:"+builder.toString());
	}
	
	private void logPrintResponse(int statusResponse) {
		StringBuilder builder = new StringBuilder();
		builder.append("Codigo Resposta: <p>" + response.statusCode() + "</p>");
		builder.append("Body: <p>"+this.response.getBody().asString()+"</p>");
		reports.childLogInfo(reportResponse, "Response: <br>"+builder.toString());
		if(response.statusCode() == statusResponse) {
			reports.childLogPass(reportResponse, "Status esperado "+statusResponse+" retornado com sucesso");
		} else {
			reports.childLogFail(reportResponse, "Status esperado: "+statusResponse+" <br> status retornado: "+response.statusCode());
		}
	}
	
	public Response get(int statusResponse) {
		logPrintRequest("GET");
		response = given()
		.headers(this.headers)
		.body(this.requestBody)
		.when()
		.get(this.url);
		logPrintResponse(statusResponse);
		return response;
	}
	
	public Response post(int statusResponse) {
		logPrintRequest("POST");
		response = given()
		.headers(this.headers)
		.body(this.requestBody)
		.when()
		.post(this.url);
		logPrintResponse(statusResponse);
		return response;
	}
	
	public Response patch(int statusResponse) {
		logPrintRequest("PATCH");
		response = given()
		.headers(this.headers)
		.body(this.requestBody)
		.when()
		.patch(this.url); 
		logPrintResponse(statusResponse);
		return response;
	}
	
	public Response put(int statusResponse) {
		logPrintRequest("PUT");
		response = given()
		.headers(this.headers)
		.body(this.requestBody)
		.when()
		.put(this.url);
		logPrintResponse(statusResponse);
		return response;
	}
	
	public Response delete(int statusResponse) {
		logPrintRequest("DELETE");
		response = given()
		.headers(this.headers)
		.body(this.requestBody)
		.when()
		.delete(this.url); 
		logPrintResponse(statusResponse);
		return response;
	}
}